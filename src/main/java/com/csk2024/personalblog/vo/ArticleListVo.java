package com.csk2024.personalblog.vo;

import lombok.Data;

import java.util.Date;

/**
 * 文章列表
 */
@Data
public class ArticleListVo {
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 文章类型id
     */
    private String articleTypeId;

    /**
     * 文章类型名
     */
    private String articleTypeName;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章添加时间
     */
    private Date articleAddTime;

    /**
     * 点赞次数
     */
    private Integer articleGoodNumber;

    /**
     * 观看次数
     */
    private Integer articleLookNumber;

    /**
     * 收藏次数
     */
    private Integer articleCollectionNumber;
}
