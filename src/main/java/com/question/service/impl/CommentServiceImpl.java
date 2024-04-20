package com.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.Comment;
import com.question.domain.Questions;
import com.question.domain.User;
import com.question.pojo.result.CommentListResult;
import com.question.service.CommentService;
import com.question.mapper.CommentMapper;
import com.question.service.QuestionsService;
import com.question.tips.ErrorTip;
import com.question.tips.SuccessTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
* @author wanghao
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2024-04-16 10:17:33
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private QuestionsService questionsService;

    /**
     * 添加评论。
     * 该方法首先会验证用户是否已登录，未登录则返回错误提示。若用户已登录，则保存评论信息，并更新相关问题的评论计数。
     *
     * @param comment 评论对象，包含评论内容及其他相关信息。
     * @param request HttpServletRequest对象，用于获取当前用户信息。
     * @return 返回操作结果，成功则返回成功提示，失败则返回错误提示。
     */
    @Override
    @Transactional
    public Object add(Comment comment, HttpServletRequest request) {
        // 从请求会话中获取用户对象，判断用户是否已登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // 如果用户未登录，返回提示信息
            return new ErrorTip(505, "未登录不能评论请先登录");
        }
        // 设置评论的创建者ID和创建时间
        comment.setCreatedBy(user.getId());
        comment.setCreatedTime(new Date());
        // 保存评论
        this.save(comment);

        if (comment.getParentId() == null) {
            // 根据评论关联的问题ID，更新问题的评论计数
            Long questionId = comment.getQuestionId();
            Questions question = questionsService.getById(questionId);
            question.setCommentCount(question.getCommentCount() + 1);
            questionsService.updateById(question);
        }
        // 返回成功提示
        return new SuccessTip();
    }


    @Override
    public List<CommentListResult> selectCommentList(Long questionId) {
        return this.baseMapper.selectCommentList(questionId);
    }

    @Override
    public List<CommentListResult> selectSecondList(Long commentId) {
        return this.baseMapper.selectSecondList(commentId);
    }

    @Override
    public List<CommentListResult> selectAllList(Long questionId) {
        return this.baseMapper.selectAllList(questionId);
    }
}




