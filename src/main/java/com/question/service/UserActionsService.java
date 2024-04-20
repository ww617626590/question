package com.question.service;

import com.question.domain.UserActions;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
* @author wanghao
* @description 针对表【user_actions(用户操作表)】的数据库操作Service
* @createDate 2024-04-12 19:52:23
*/
public interface UserActionsService extends IService<UserActions> {

    String userActionsPage(Integer pageNum, HttpServletRequest request, Model model);
}
