package com.question.pojo.result;

import com.question.domain.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentListResult extends Comment {
    /**
     * 创建人
     */
    private String createdByName;
    /**
     * 评论数
     */
    private Integer secondCommentCount;

    /**
     * 子评论
     */
    private List<CommentListResult> secondCommentList;
}
