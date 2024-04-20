package com.question.controller;

import com.question.aop.SystemCrmlog;
import com.question.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 文件控制器
 */
@Controller()
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * 上传文件到服务器。
     *
     * @param file 需要上传的文件对象，通过multipart/form-data形式提交。
     * @param documentDescribe 文件描述信息，用于记录或展示文件的简要说明。
     * @param request 用户的请求对象，可用于获取请求相关的信息。
     * @param model Spring模型对象，用于在上传过程中传递数据和视图之间的交互。
     * @return 返回一个字符串表示上传操作的结果，通常用于页面跳转或提示信息。
     */
    @RequestMapping("/upload")
    @SystemCrmlog(description="上传资源")
    public String uploadFile(MultipartFile file, String documentDescribe, HttpServletRequest request, Model model) {
        // 调用documentService的服务方法，实现文件上传逻辑
        return documentService.uploadFile(file, documentDescribe, request, model);
    }


    /**
     * 文件下载功能。
     * 通过给定的文档ID，下载相应的文件。
     *
     * @param documentId 文档的唯一标识符，用于定位要下载的文件。
     * @param request HttpServletRequest对象，用于获取请求相关信息。
     * @param response HttpServletResponse对象，用于设置响应头信息以及输出文件。
     */
    @RequestMapping("/download")
    @SystemCrmlog(description="下载资源")
    public void downloadFile(String documentId, HttpServletRequest request, HttpServletResponse response) {
        // 调用文档服务的下载文件方法，实现文件的下载
        documentService.downloadFile(documentId, request, response);
    }


    /**
     * 处理文档列表的分页请求。
     *
     * @param pageNum      请求的页码，用于获取指定页的文档列表。
     * @param queryContent
     * @param request      用户的请求对象，可用于获取更多的请求信息。
     * @param model        用于在视图和控制器之间传递数据的模型对象。
     * @return 返回文档列表页面的视图名称。
     */
    @RequestMapping("/page")
    public String page(Integer pageNum,String queryContent,HttpServletRequest request, Model model) {
        // 调用documentService的documentPage方法处理文档列表的分页展示
        return documentService.documentPage(pageNum,queryContent,request,model);
    }




}
