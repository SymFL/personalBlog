package com.csk2024.personalblog.service;

import com.csk2024.personalblog.entity.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csk2024.personalblog.vo.ArticleTypeVo;

import java.util.List;

/**
* @author 24387
* @description 针对表【article_type(文章类型)】的数据库操作Service
* @createDate 2024-01-09 04:02:58
*/
public interface ArticleTypeService extends IService<ArticleType> {

    List<ArticleTypeVo> articleTypeVoList();
}
