package com.question.service;

import com.question.domain.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author wanghao
* @description 针对表【feedback(意见反馈表)】的数据库操作Service
* @createDate 2024-04-16 15:48:32
*/
public interface FeedbackService extends IService<Feedback> {

    Object add(Feedback feedback, HttpServletRequest request);
}
