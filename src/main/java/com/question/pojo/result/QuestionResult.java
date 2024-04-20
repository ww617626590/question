package com.question.pojo.result;


import com.question.domain.Questions;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionResult extends Questions {

    /**
     * 创建人
     */
    private String createdByName;


}
