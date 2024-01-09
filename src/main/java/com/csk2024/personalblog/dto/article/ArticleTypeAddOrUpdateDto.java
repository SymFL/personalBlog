package com.csk2024.personalblog.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端返回文章类型数据类
 */
@Data
public class ArticleTypeAddOrUpdateDto {
    /**
     * 文章类型 id
     */
    private String articleTypeId;

    /**
     * 文章类型名字
     */
    @NotBlank(message = "文章类型名称不允许为空")
    private String articleTypeName;

    /**
     * 文章排序
     */
    @NotNull(message = "文章类型排序依据不允许为空")
    private Integer articleTypeSort;
}
