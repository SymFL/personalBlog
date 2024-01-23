package com.csk2024.personalblog.service;

import com.csk2024.personalblog.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_tag(标签)】的数据库操作Service
* @createDate 2024-01-04 20:17:07
*/
public interface ArticleTagService extends IService<ArticleTag> {

    List<ArticleTag> listTag(String articleId);
}
