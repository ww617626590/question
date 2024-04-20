package com.question.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.question.domain.Document;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.question.pojo.result.DocumentPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wanghao
* @description 针对表【document(文件表)】的数据库操作Mapper
* @createDate 2024-04-12 10:00:16
* @Entity com.question.domain.Document
*/
public interface DocumentMapper extends BaseMapper<Document> {

    List<DocumentPageResult> documentPage(@Param("page") Page page, @Param("queryContent") String queryContent);

    List<Document> hotDocument();

}




