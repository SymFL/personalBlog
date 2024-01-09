package com.csk2024.personalblog.dto.article;

import com.csk2024.personalblog.dto.base.BasePageDto;
import lombok.Data;

/**
 * 前端文章列表分页数据类
 */
@Data
public class ArticleListPageDto extends BasePageDto {
    private String articleTitle;
}
