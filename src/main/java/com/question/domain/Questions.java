package com.question.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 问题表
 * @TableName questions
 */
@TableName(value ="questions")
@Data
public class Questions implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    // 转换时间格式
    @TableField(value = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 问题标题
     */
    private String questionsTitle;

    /**
     * 问题描述
     */
    private String questionsContent;

    /**
     * 难易程度(简单、中等、困难)
     */
    private String difficulty;

    /**
     * 浏览数
     */
    private Integer browseCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Questions other = (Questions) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getQuestionsTitle() == null ? other.getQuestionsTitle() == null : this.getQuestionsTitle().equals(other.getQuestionsTitle()))
            && (this.getQuestionsContent() == null ? other.getQuestionsContent() == null : this.getQuestionsContent().equals(other.getQuestionsContent()))
            && (this.getDifficulty() == null ? other.getDifficulty() == null : this.getDifficulty().equals(other.getDifficulty()))
            && (this.getBrowseCount() == null ? other.getBrowseCount() == null : this.getBrowseCount().equals(other.getBrowseCount()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getQuestionsTitle() == null) ? 0 : getQuestionsTitle().hashCode());
        result = prime * result + ((getQuestionsContent() == null) ? 0 : getQuestionsContent().hashCode());
        result = prime * result + ((getDifficulty() == null) ? 0 : getDifficulty().hashCode());
        result = prime * result + ((getBrowseCount() == null) ? 0 : getBrowseCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", questionsTitle=").append(questionsTitle);
        sb.append(", questionsContent=").append(questionsContent);
        sb.append(", difficulty=").append(difficulty);
        sb.append(", browseCount=").append(browseCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}