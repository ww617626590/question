package com.question.pojo.result;

import com.question.domain.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentPageResult extends Document {
    /**
     * 创建人
     */
    private String createdByName;
}
