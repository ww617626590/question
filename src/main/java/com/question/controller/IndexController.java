package com.question.controller;

import com.question.tips.SuccessTip;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xx
 * @date: 2024/4/11 16:02
 * @description: 导航controller 每添加一个html页面都需要添加一个方法
 */
@Controller
public class IndexController {
    // 首页跳转
    @RequestMapping("/index")
    public String index() {
        return "redirect:/questions/page";
    }
    // 首页跳转
    @RequestMapping("/")
    public String mainPage() {
        return "redirect:/questions/page";
    }


    // 注册跳转
    @RequestMapping("/register")
    public String register() {
        return "signup";
    }
    // 退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/questions/page";
    }
    // 登录跳转
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/getSessionData")
    @ResponseBody
    public Object getSessionData(HttpServletRequest request) {
        return new SuccessTip().data(request.getSession().getAttribute("user"));
    }

    // 发布问题跳转
    @RequestMapping("/saveQuestion")
    public String saveQuestion() {
        return "saveQuestion";
    }
    // 资源页面
    @RequestMapping("/resourcePage")
    public String resourcePage() {
        return "resourcePage";
    }
    // 问题页面
    @RequestMapping("/questionPage")
    public String questionPage() {
        return "questionPage";
    }
    // 分享资源
    @RequestMapping("/shareResource")
    public String shareResource() {
        return "shareResource";
    }
    // 数据统计
    @RequestMapping("/dataStatisticsPage")
    public String dataStatistics() {
        return "dataStatisticsPage";
    }


    // 后台页面
    @RequestMapping("/admin")
    public String admin() {
        return "/admin/index";
    }
}
