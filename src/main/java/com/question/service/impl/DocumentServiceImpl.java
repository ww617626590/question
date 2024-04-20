package com.question.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.Document;
import com.question.domain.User;
import com.question.pojo.result.DocumentPageResult;
import com.question.service.DocumentService;
import com.question.mapper.DocumentMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
* @author wanghao
* @description 针对表【document(文件表)】的数据库操作Service实现
* @createDate 2024-04-12 10:00:16
*/
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document>
    implements DocumentService{

    /**
     * 上传文件到服务器的方法。
     *
     * @param file             需要上传的文件，类型为MultipartFile。
     * @param documentDescribe 对上传文件的描述。
     * @param request
     * @param model
     * @return 根据上传结果返回不同的Tip对象，成功返回SuccessTip，失败返回ErrorTip。
     */
    @Override
    public String uploadFile(MultipartFile file, String documentDescribe, HttpServletRequest request, Model model) {
        if (file == null) {
            model.addAttribute("error", "文件不能为空");
            return "shareResource";
        }
        // 检查文件是否为空
        if (file.isEmpty()) {
            model.addAttribute("error", "文件不能为空");
            return "shareResource";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "未登录，请先登录");
            return "shareResource";
        }
        // 定义文件保存路径
        String filePath = "D:\\upload\\";
        // filePath 判断文件路径是否存在，如果不存在则创建
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        // 获取文件原名
        String fileName = file.getOriginalFilename();
        // 定义文件在服务器上的完整路径
        String fileLocation = filePath + fileName;
        try {
            // 将上传的文件保存到服务器指定路径
            file.transferTo(new File(fileLocation));
            // 创建Document对象并设置文件相关信息
            Document document = new Document();
            document.setDocumentLocation(fileLocation);
            document.setDocumentName(fileName);
            document.setCreatedBy(user.getId());
            document.setCreatedTime(new Date());
            document.setDocumentDescribe(documentDescribe);
            // 保存Document对象到数据库
            this.save(document);
            // 返回上传成功的提示信息
            return "redirect:/document/page";
        } catch (IOException e) {
            // 文件上传过程出现异常，抛出运行时异常
            throw new RuntimeException(e);
        }

    }

    @Override
    public void downloadFile(String documentId, HttpServletRequest request, HttpServletResponse response) {
        // 根据文件ID获取文件信息
        Document document = this.getById(documentId);
        if (document == null) {
            throw new RuntimeException("文件不存在");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        // 增加文件下载次数
        document.setDownloadCount(document.getDownloadCount() + 1);
        this.updateById(document);

        // 获取文件路径
        String filePath = document.getDocumentLocation();
        try {
            // 创建File对象并设置文件路径
            File file = new File(filePath);
            // 防止中文名乱码
            String filename = URLEncoder.encode(file.getName(), "utf-8");
            // 设置响应头信息
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ filename);
            response.setCharacterEncoding("utf-8");

            // 读取文件内容并写入响应
            OutputStream out = response.getOutputStream();
            try (InputStream in = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush(); // 确保所有数据都已写入
            }
        } catch (IOException e) {
            // 文件下载过程出现异常，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成文档页面
     *
     * @param pageNum      请求的页码，如果为null则默认为第1页
     * @param queryContent
     * @param request      HttpServletRequest对象，用于传递请求信息
     * @param model        Model对象，用于在视图和控制器之间传递数据
     * @return 返回页面视图名称 "resourcePage"
     */
    @Override
    public String documentPage(Integer pageNum, String queryContent, HttpServletRequest request, Model model) {
        // 如果页码为null，设置为默认页码：第1页
        if (pageNum == null) {
            pageNum = 1;
        }
        // 创建Page对象，用于分页查询
        Page page = new Page(pageNum, 5);
        // 执行文档页面查询
        List<DocumentPageResult> results = this.baseMapper.documentPage(page,queryContent);
        // 设置查询结果到Page对象
        page.setRecords(results);

        // 查询热门资源
        List<Document> hotResults = this.baseMapper.hotDocument();
        // 将热门资源和分页信息添加到Model中，供视图使用
        model.addAttribute("hotResults", hotResults);
        model.addAttribute("page", page);

        // 返回页面视图名称
        return "resourcePage";
    }

}




