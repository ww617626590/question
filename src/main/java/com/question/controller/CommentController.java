package com.question.controller;

import com.question.aop.SystemCrmlog;
import com.question.domain.Comment;
import com.question.service.CommentService;
import com.question.tips.SuccessTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param comment 包含评论内容的对象，通过RequestBody接收前端传来的JSON数据
     * @param request HttpServletRequest对象，用于获取请求相关信息
     * @return 返回评论添加操作的结果，具体类型依据commentService.add方法的实现而定
     */
    @RequestMapping("/add")
    @ResponseBody
    @SystemCrmlog(description = "评论")
    public Object add(@RequestBody Comment comment, HttpServletRequest request) {
        // 调用评论服务层的方法，添加评论
        return commentService.add(comment,request);
    }

    /**
     * 根据评论id获取二级评论列表
     */
    @RequestMapping("/selectCommentList")
    @ResponseBody
    public Object selectSecondList(Long commentId) {
        // 调用评论服务层的方法，获取二级评论列表
        return new SuccessTip().data(commentService.selectSecondList(commentId));
    }

}
