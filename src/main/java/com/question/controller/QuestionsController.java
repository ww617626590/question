package com.question.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.question.aop.SystemCrmlog;
import com.question.domain.Questions;
import com.question.service.CommentService;
import com.question.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private CommentService commentService;

    /**
     * 添加问题
     *
     * @param questions 问题对象，包含问题的详细信息。
     * @param request HttpServletRequest对象，用于获取请求相关信息。
     * @param model Model对象，用于在视图和控制器之间传递数据。
     * @return 返回一个字符串，表示操作的结果，通常用于页面跳转。
     */
    @RequestMapping("/add")
    @SystemCrmlog(description = "发布问题")
    public String add(Questions questions, HttpServletRequest request, Model model) {
        // 调用服务层方法，实现问题的添加
        return questionsService.add(questions, request, model);
    }

    /**
     * 请求问题列表页面。
     *
     *
     * @param questionTitle 问题标题，用于筛选问题列表。
     * @param request 用户的请求对象，用于获取请求信息。
     * @param model 用于在视图和控制器之间传递数据的模型对象。
     * @return 返回问题列表页面的名称。
     */
    @RequestMapping("/page")
    public String questionPage( Integer pageNum,
                               String questionTitle,
                               HttpServletRequest request,
                               Model model) {
        // 调用服务层方法，获取问题列表并返回对应的页面
        return questionsService.questionPage(pageNum, questionTitle, request, model);
    }

    /**
     * 根据问题ID获取问题详情。
     *
     * @param questionId 问题的唯一标识符。
     * @param model 用于在视图和控制器之间传递数据的对象。
     * @return 返回问题详情页面的路径。
     */
    @RequestMapping("/detail")
    public String questionDetail(Long questionId, Model model) {
        // 增加问题的浏览次数
        questionsService.addBrowseCount(questionId);
        // 获取并返回问题的详细信息
        return questionsService.questionDetail(questionId, model);
    }

}
