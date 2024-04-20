package com.question.service;

import com.question.domain.Questions;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
* @author wanghao
* @description 针对表【questions(问题表)】的数据库操作Service
* @createDate 2024-04-14 21:32:41
*/
public interface QuestionsService extends IService<Questions> {

    String add(Questions questions, HttpServletRequest request, Model model);

    String questionPage(Integer pageNum, String questionTitle, HttpServletRequest request, Model model);

    String questionDetail(Long questionId, Model model);

    void addBrowseCount(Long questionId);


}
