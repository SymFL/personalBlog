package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.ArticleType;
import com.csk2024.personalblog.service.ArticleTypeService;
import com.csk2024.personalblog.mapper.ArticleTypeMapper;
import com.csk2024.personalblog.vo.ArticleTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_type(文章类型)】的数据库操作Service实现
* @createDate 2024-01-09 04:02:58
*/
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType>
    implements ArticleTypeService{
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    /**
     * 文章类型列表，包含文章数量
     * @return
     */
    @Override
    public List<ArticleTypeVo> articleTypeVoList() {
        return articleTypeMapper.articleTypeList();
    }
}




