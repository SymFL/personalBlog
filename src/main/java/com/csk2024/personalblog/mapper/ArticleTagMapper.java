package com.csk2024.personalblog.mapper;

import com.csk2024.personalblog.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_tag(标签)】的数据库操作Mapper
* @createDate 2024-01-04 20:17:07
* @Entity com.personalblog.personalblog.entity.ArticleTag
*/
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> listTag(String articleId);
}




