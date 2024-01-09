package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Article;
import com.csk2024.personalblog.service.ArticleService;
import com.csk2024.personalblog.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2024-01-09 04:00:48
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




