package com.csk2024.personalblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.csk2024.personalblog.entity.Article;
import com.csk2024.personalblog.vo.ArticleListVo;
import org.apache.ibatis.annotations.Param;

/**
* @author 24387
* @description 针对表【article(文章)】的数据库操作Mapper
* @createDate 2024-01-09 04:00:48
* @Entity com.csk2024.personalblog.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleListVo> listArticleListVo(IPage<ArticleListVo> page, @Param("articleTitle") String articleTitle);

    IPage<ArticleListVo> listArticle(IPage<ArticleListVo> page, @Param("articleTitle") String articleTitle, @Param("articleType") String articleType);

    IPage<ArticleListVo> listCollectionArticle(IPage<Article> page, @Param("userId") String userId, @Param("articleTitle") String articleTitle);

    IPage<ArticleListVo> listUserArticle(IPage<Article> page, @Param("userId") String userId, @Param("articleTitle") String articleTitle);
}




