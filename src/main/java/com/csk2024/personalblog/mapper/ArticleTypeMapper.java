package com.csk2024.personalblog.mapper;

import com.csk2024.personalblog.entity.ArticleType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csk2024.personalblog.vo.ArticleTypeVo;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_type(文章类型)】的数据库操作Mapper
* @createDate 2024-01-09 04:02:58
* @Entity com.csk2024.personalblog.entity.ArticleType
*/
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {

    List<ArticleTypeVo> articleTypeList();
}




