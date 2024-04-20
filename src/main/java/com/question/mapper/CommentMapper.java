package com.question.mapper;

import com.question.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.pojo.result.CommentListResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wanghao
* @description 针对表【comment(评论表)】的数据库操作Mapper
* @createDate 2024-04-16 10:17:33
* @Entity com.question.domain.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentListResult> selectCommentList(@Param("questionId") Long questionId);

    List<CommentListResult> selectSecondList(@Param("commentId") Long commentId);

    List<CommentListResult> selectAllList(@Param("questionId") Long questionId);
}




