package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.ArticleTagList;
import com.csk2024.personalblog.service.ArticleTagListService;
import com.csk2024.personalblog.mapper.ArticleTagListMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【article_tag_list(文章与标签对应关系)】的数据库操作Service实现
* @createDate 2024-01-04 20:17:18
*/
@Service
public class ArticleTagListServiceImpl extends ServiceImpl<ArticleTagListMapper, ArticleTagList>
    implements ArticleTagListService{

}




