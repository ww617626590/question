package com.question.controller;

import com.question.aop.SystemCrmlog;
import com.question.domain.User;
import com.question.service.UserService;
import com.question.tips.SuccessTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户前端控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口。该接口使用POST请求，接收一个JSON格式的用户信息，完成用户注册。
     *
     * @param user 包含用户注册信息的对象，通过RequestBody接收前端传来的JSON数据。
     * @return 返回注册结果，可以是注册成功的信息或者错误提示。
     */
    @RequestMapping("/register")
    @ResponseBody
    public Object register(@RequestBody User user) {
        // 调用userService的register方法处理用户注册逻辑
        return userService.register(user);
    }


    /**
     * 用户登录接口。
     * 接收前端发送的用户登录信息，验证用户身份，并返回登录结果。
     *
     * @param user 包含登录所需信息的用户对象，通常包括用户名和密码等。
     * @param request 用户的请求对象，可用于获取额外的请求信息，如IP地址等，用于安全验证或日志记录。
     * @return 返回登录结果，通常是一个包含成功标识和可能的错误信息的对象。具体返回格式和内容由业务需求决定。
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user, HttpServletRequest request) {
        // 调用服务层方法处理用户登录请求
        return userService.login(user, request);
    }


}
