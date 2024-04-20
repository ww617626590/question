package com.question.service;

import com.question.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.question.pojo.result.CommentListResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author wanghao
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2024-04-16 10:17:33
*/
public interface CommentService extends IService<Comment> {

    Object add(Comment comment, HttpServletRequest request);

    List<CommentListResult> selectCommentList(Long questionId);

    List<CommentListResult> selectSecondList(Long commentId);

    List<CommentListResult> selectAllList(Long questionId);
}
