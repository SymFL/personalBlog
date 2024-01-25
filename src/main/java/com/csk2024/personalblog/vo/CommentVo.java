package com.csk2024.personalblog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    /**
     * 文章评论id
     */
    private String commentId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id（评论者）
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 评论内容
     */
    private String commentContext;
}
