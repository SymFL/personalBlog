package com.csk2024.personalblog.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleTagAddOrUpdateDto {

    /**
     * 文章标签id
     */
    private String articleTagId;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名不允许为空")
    private String articleTagName;
}
