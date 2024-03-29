package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.ArticleTag;
import com.csk2024.personalblog.service.ArticleTagService;
import com.csk2024.personalblog.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_tag(标签)】的数据库操作Service实现
* @createDate 2024-01-04 20:17:07
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTag> listTag(String articleId) {
        return articleTagMapper.listTag(articleId);
    }
}




