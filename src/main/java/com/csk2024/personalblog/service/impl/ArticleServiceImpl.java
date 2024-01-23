package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Article;
import com.csk2024.personalblog.mapper.ArticleMapper;
import com.csk2024.personalblog.service.ArticleService;
import com.csk2024.personalblog.vo.ArticleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2024-01-09 04:00:48
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public IPage<ArticleListVo> listArticleListVo(IPage<ArticleListVo> page, String articleTitle) {

        return articleMapper.listArticleListVo(page,articleTitle);
    }

    @Override
    public IPage<ArticleListVo> listArticle(IPage<ArticleListVo> page, String articleTitle, String articleType) {
        return articleMapper.listArticle(page,articleTitle,articleType);
    }

    @Override
    public IPage<ArticleListVo> listCollectionArticle(IPage<ArticleListVo> page, String userId, String articleTitle) {
        return articleMapper.listCollectionArticle(page,userId,articleTitle);
    }

    @Override
    public IPage<ArticleListVo> listUserArticle(IPage<ArticleListVo> page, String userId, String articleTitle) {
        return articleMapper.listUserArticle(page,userId,articleTitle);
    }

    @Override
    public ArticleListVo getArticleDetails(String articleId) {
        return articleMapper.getArticleDetails(articleId);
    }
}




