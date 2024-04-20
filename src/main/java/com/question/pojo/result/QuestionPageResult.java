package com.question.pojo.result;

import com.question.domain.Questions;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: wanghao
 * @date: 2024/4/15 15:41
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionPageResult extends Questions {
    /**
     * 创建人
     */
    private String createdByName;

}
