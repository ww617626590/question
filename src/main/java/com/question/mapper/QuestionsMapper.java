package com.question.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.question.domain.Questions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.pojo.result.QuestionPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wanghao
* @description 针对表【questions(问题表)】的数据库操作Mapper
* @createDate 2024-04-14 21:32:41
* @Entity com.question.domain.Questions
*/
public interface QuestionsMapper extends BaseMapper<Questions> {

    List<QuestionPageResult> questionPage(@Param("page") Page page, @Param("questionTitle") String questionTitle);

    List<Questions> hotQuestions();

}




