package com.question.service;

import com.question.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.question.tips.Tip;

import javax.servlet.http.HttpServletRequest;

/**
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-04-11 14:39:31
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    Tip register(User user);

    /**
     * 用户登录
     */
    Tip login(User user, HttpServletRequest request);
}
