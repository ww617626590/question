package com.question.controller;


import com.question.aop.SystemCrmlog;
import com.question.domain.Feedback;
import com.question.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller()
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 处理添加反馈的请求。
     *
     * @return 返回添加反馈页面的视图路径。
     */
    @RequestMapping("/add")
    @ResponseBody
    @SystemCrmlog(description = "意见反馈")
    public Object add(@RequestBody Feedback feedback, HttpServletRequest request) {
        return feedbackService.add(feedback, request);
    }
}
