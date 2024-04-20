package com.question.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件表
 * @TableName document
 */
@TableName(value ="document")
@Data
public class Document implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 创建人id
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 文件名
     */
    private String documentName;

    /**
     * 文件描述
     */
    private String documentDescribe;

    /**
     * 文件地址
     */
    private String documentLocation;

    /**
     * 下载次数
     */
    private Integer downloadCount;

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
        Document other = (Document) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getDocumentName() == null ? other.getDocumentName() == null : this.getDocumentName().equals(other.getDocumentName()))
            && (this.getDocumentDescribe() == null ? other.getDocumentDescribe() == null : this.getDocumentDescribe().equals(other.getDocumentDescribe()))
            && (this.getDocumentLocation() == null ? other.getDocumentLocation() == null : this.getDocumentLocation().equals(other.getDocumentLocation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getDocumentName() == null) ? 0 : getDocumentName().hashCode());
        result = prime * result + ((getDocumentDescribe() == null) ? 0 : getDocumentDescribe().hashCode());
        result = prime * result + ((getDocumentLocation() == null) ? 0 : getDocumentLocation().hashCode());
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
        sb.append(", documentName=").append(documentName);
        sb.append(", documentDescribe=").append(documentDescribe);
        sb.append(", documentLocation=").append(documentLocation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}