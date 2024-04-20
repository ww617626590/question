package com.question.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.question.domain.Questions;
import com.question.domain.User;
import com.question.pojo.result.CommentListResult;
import com.question.pojo.result.QuestionPageResult;
import com.question.pojo.result.QuestionResult;
import com.question.service.CommentService;
import com.question.service.QuestionsService;
import com.question.mapper.QuestionsMapper;
import com.question.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author wanghao
* @description 针对表【questions(问题表)】的数据库操作Service实现
* @createDate 2024-04-14 21:32:41
*/
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>
    implements QuestionsService{
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @Override
    public String add(Questions questions, HttpServletRequest request, Model model) {
        if (questions.getQuestionsTitle() == null || "".equals(questions.getQuestionsTitle())) {
            model.addAttribute("error", "问题不能为空");
            return "saveQuestion";
        }
        if (questions.getQuestionsContent() == null || "".equals(questions.getQuestionsContent())) {
            model.addAttribute("error", "内容不能为空");
            return "saveQuestion";
        }
        model.addAttribute("questionsTitle", questions.getQuestionsTitle());
        model.addAttribute("questionsContent", questions.getQuestionsContent());
        model.addAttribute("difficulty", questions.getDifficulty());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录，请先登录");
            return "saveQuestion";
        }
        questions.setCreatedBy(user.getId());
        questions.setCreatedTime(new Date());
        this.save(questions);
        return "redirect:/questions/page";
    }

    @Override
    public String questionPage(Integer pageNum, String questionTitle, HttpServletRequest request, Model model) {
        if (pageNum == null) {
            pageNum = 1;
        }
        Page page = new Page<>(pageNum, 5);
        // 设置返回给前端的分页数
        List<QuestionPageResult> questionPageResults = this.baseMapper.questionPage(page, questionTitle);
        page.setRecords(questionPageResults);
        List<Long> pageNumbers = this.getPageNumbers(page);
        // 获取热门问题 --- 热门问题为浏览次数最高的10条
        List<Questions> hotQuestions = this.baseMapper.hotQuestions();
        model.addAttribute("hotQuestions", hotQuestions);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("page", page);
        return "questionPage";

    }

    /**
     * 查看问题详情
     * @param questionId 问题ID
     * @param model 模型，用于在视图和控制器之间传递数据
     * @return 返回问题详情页面的视图名
     */
    @Override
    public String questionDetail(Long questionId, Model model) {
        // 根据问题ID获取问题详细信息
        Questions questions = this.getById(questionId);
        QuestionResult questionResult = new QuestionResult();

        // 获取问题创建者信息，并设置到问题结果中
        User user = userService.getById(questions.getCreatedBy());
        questionResult.setCreatedByName(user.getUserName());

        // 将问题信息复制到问题结果对象中
        BeanUtils.copyProperties(questions, questionResult);

        // 将问题结果对象添加到模型中，以便在视图中使用
        model.addAttribute("questionResult", questionResult);
        List<CommentListResult> comments = commentService.selectCommentList(questionId);
        for (CommentListResult comment : comments) {
            List<CommentListResult> selectSecondList = commentService.selectSecondList(comment.getId());
            comment.setSecondCommentList(selectSecondList);
            comment.setSecondCommentCount(selectSecondList.size());
        }
        List<CommentListResult> allComments = commentService.selectAllList(questionId);
        // 获取问题所有评论列表
        model.addAttribute("allComments", allComments);
        // 获取问题评论列表
        model.addAttribute("commentList", comments);
        // 返回问题详情页面的视图名
        return "questionDetail";
    }


    @Override
    public void addBrowseCount(Long questionId) {
        Questions questions = this.getById(questionId);
        questions.setBrowseCount(questions.getBrowseCount() + 1);
        this.updateById(questions);
    }

    private List<Long> getPageNumbers(Page page) {
        List<Long> pageNumbers = new ArrayList<>();
        Long totalPage;
        if (page.getPages() % page.getSize() == 0) {
            totalPage = page.getPages() / page.getSize();
        } else {
            totalPage = page.getPages() / page.getSize() + 1;
        }
        pageNumbers.add(page.getCurrent());
        for (int i = 1; i <= 3; i++) {
            if (page.getCurrent() + i <= totalPage) {
                pageNumbers.add(page.getCurrent() + i);
            }
            if (page.getCurrent() - i > 0) {
                pageNumbers.add(page.getCurrent() - i);
            }
        }
        return pageNumbers;
    }
}




