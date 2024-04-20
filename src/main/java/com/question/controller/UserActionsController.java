package com.question.controller;

import com.question.service.UserActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userActions")
public class UserActionsController {
    @Autowired
    private UserActionsService userActionsService;

    /**
     * 统计列表
     */
    @RequestMapping("/page")
    public String page(Integer pageNum, HttpServletRequest request, Model model) {
        return userActionsService.userActionsPage(pageNum,request,model);
    }
}
