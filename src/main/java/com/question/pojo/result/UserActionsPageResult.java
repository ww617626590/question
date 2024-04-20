package com.question.pojo.result;


import com.question.domain.UserActions;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserActionsPageResult extends UserActions {
    /**
     * 创建人
     */
    private String createdByName;
}
