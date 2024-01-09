package com.csk2024.personalblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.csk2024.personalblog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csk2024.personalblog.vo.ArticleListVo;

/**
* @author 24387
* @description 针对表【article(文章)】的数据库操作Service
* @createDate 2024-01-09 04:00:48
*/
public interface ArticleService extends IService<Article> {

    IPage<ArticleListVo> listArticleListVo(IPage<ArticleListVo> page, String articleTitle);
}
