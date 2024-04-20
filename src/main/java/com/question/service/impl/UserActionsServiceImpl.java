package com.question.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.UserActions;
import com.question.pojo.result.UserActionsPageResult;
import com.question.service.UserActionsService;
import com.question.mapper.UserActionsMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author wanghao
* @description 针对表【user_actions(用户操作表)】的数据库操作Service实现
* @createDate 2024-04-12 19:52:23
*/
@Service
public class UserActionsServiceImpl extends ServiceImpl<UserActionsMapper, UserActions>
    implements UserActionsService{

    @Override
    public String userActionsPage(Integer pageNum, HttpServletRequest request, Model model) {
        if (pageNum == null) {
            pageNum = 1;
        }
        Page page = new Page(pageNum, 13);
        List<UserActionsPageResult> userActionsList = this.baseMapper.userActionsPage(page);
        page.setRecords(userActionsList);
        // 活跃用户 按照操作记录最高的用户
        List<UserActionsPageResult> activeUsers = this.baseMapper.activeUser();
        model.addAttribute("activeUsers", activeUsers);
        model.addAttribute("page", page);
        return "dataStatisticsPage";
    }
}




