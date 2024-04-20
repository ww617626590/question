package com.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.Feedback;
import com.question.domain.User;
import com.question.service.FeedbackService;
import com.question.mapper.FeedbackMapper;
import com.question.tips.ErrorTip;
import com.question.tips.SuccessTip;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author wanghao
* @description 针对表【feedback(意见反馈表)】的数据库操作Service实现
* @createDate 2024-04-16 15:48:32
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService{

    /**
     * 添加反馈信息。
     * 该方法会将提供的反馈信息保存到数据库中，并在保存前设置反馈的创建者ID和创建时间。
     * 方法会检查当前用户是否已登录，未登录则返回错误提示。
     *
     * @param feedback 反馈信息对象，包含了反馈的详细内容。
     * @param request 用户的请求对象，用于获取当前登录的用户信息。
     * @return 返回一个提示对象，成功保存则返回成功提示，未登录则返回请先登录的错误提示。
     */
    @Override
    public Object add(Feedback feedback, HttpServletRequest request) {
        // 尝试从会话中获取当前用户对象
        User user = (User) request.getSession().getAttribute("user");
        // 如果用户对象为空，表示用户未登录，返回请先登录的错误提示
        if (user == null) {
            return new ErrorTip(505, "请先登录");
        }
        // 设置反馈的创建者ID和创建时间
        feedback.setCreatedBy((Long) request.getSession().getAttribute("userId"));
        feedback.setCreatedTime(new java.util.Date());
        // 保存反馈信息到数据库
        this.save(feedback);
        // 返回成功添加的提示
        return new SuccessTip();
    }
}




