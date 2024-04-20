package com.question.service;

import com.question.domain.Document;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author wanghao
* @description 针对表【document(文件表)】的数据库操作Service
* @createDate 2024-04-12 10:00:16
*/
public interface DocumentService extends IService<Document> {

    String uploadFile(MultipartFile file, String documentDescribe, HttpServletRequest request, Model model);

    void downloadFile(String documentId, HttpServletRequest request, HttpServletResponse response);

    String documentPage(Integer pageNum, String queryContent, HttpServletRequest request, Model model);
}
