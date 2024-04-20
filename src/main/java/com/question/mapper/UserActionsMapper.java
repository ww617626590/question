package com.question.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.question.domain.UserActions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.pojo.result.UserActionsPageResult;

import java.util.List;

/**
* @author xxx
* @description 针对表【user_actions(用户操作表)】的数据库操作Mapper
* @createDate 2024-04-12 19:52:23
* @Entity com.question.domain.UserActions
*/
public interface UserActionsMapper extends BaseMapper<UserActions> {

    List<UserActionsPageResult> userActionsPage(Page page);

    List<UserActionsPageResult> activeUser();

}




