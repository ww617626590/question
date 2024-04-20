package com.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.User;
import com.question.service.UserService;
import com.question.mapper.UserMapper;
import com.question.tips.ErrorTip;
import com.question.tips.SuccessTip;
import com.question.tips.Tip;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-04-11 14:39:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 用户注册功能实现。
     *
     * @param user 包含用户名、密码和电子邮件的用户对象。
     * @return 根据注册结果返回不同的提示信息，成功返回SuccessTip，失败返回ErrorTip。
     */
    @Override
    public Tip register(User user) {
        // 检查用户注册信息是否完整
        if (user.getUserName() == null || user.getPassword() == null || user.getUserEmail() == null) {
            return new ErrorTip(501, "参数错误");
        }

        // 检查用户名是否已被注册
        User userName = this.getOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (userName != null) {
            return new ErrorTip(502, "该用户已存在");
        }

        // 尝试保存用户信息到数据库
        if (this.save(user)) {
            return new SuccessTip("注册成功");
        } else {
            return new ErrorTip(503, "注册失败");
        }
    }


    /**
     * 用户登录功能实现。
     *
     * @param user 包含用户名和密码的用户对象。
     * @param request 用户的请求对象，用于登录成功后存储用户信息。
     * @return 根据登录结果返回不同的提示信息，成功返回SuccessTip，失败返回ErrorTip。
     */
    @Override
    public Tip login(User user, HttpServletRequest request) {
        // 验证用户输入的用户名和密码是否为空
        if (user.getUserName() == null || user.getPassword() == null) {
            return new ErrorTip(501, "参数错误");
        }
        // 根据用户名查询数据库中的用户信息
        User userName = this.getOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (userName == null) {
            // 如果查询不到用户，则返回用户名密码错误提示
            return new ErrorTip(502, "用户名密码错误");
        }
        // 验证密码是否正确
        if (userName.getPassword().equals(user.getPassword())) {
            // 登录成功，将用户信息存储到session中
            request.getSession().setAttribute("user", userName);
            return new SuccessTip("登录成功");
        } else {
            // 密码不匹配，返回用户名密码错误提示
            return new ErrorTip(503, "用户名密码错误");
        }
    }

}




