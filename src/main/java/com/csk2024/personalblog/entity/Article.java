package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 文章id
     */
    @TableId(value = "article_id")
    private String articleId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 文章类型id
     */
    @TableField(value = "article_type_id")
    private String articleTypeId;

    /**
     * 文章标题
     */
    @TableField(value = "article_title")
    private String articleTitle;

    /**
     * 文章添加时间
     */
    @TableField(value = "article_add_time")
    private Date articleAddTime;

    /**
     * 文章内容
     */
    @TableField(value = "article_context")
    private String articleContext;

    /**
     * 点赞次数
     */
    @TableField(value = "article_good_number")
    private Integer articleGoodNumber;

    /**
     * 观看次数
     */
    @TableField(value = "article_look_number")
    private Integer articleLookNumber;

    /**
     * 收藏次数
     */
    @TableField(value = "article_collection_number")
    private Integer articleCollectionNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getArticleTypeId() == null ? other.getArticleTypeId() == null : this.getArticleTypeId().equals(other.getArticleTypeId()))
            && (this.getArticleTitle() == null ? other.getArticleTitle() == null : this.getArticleTitle().equals(other.getArticleTitle()))
            && (this.getArticleAddTime() == null ? other.getArticleAddTime() == null : this.getArticleAddTime().equals(other.getArticleAddTime()))
            && (this.getArticleContext() == null ? other.getArticleContext() == null : this.getArticleContext().equals(other.getArticleContext()))
            && (this.getArticleGoodNumber() == null ? other.getArticleGoodNumber() == null : this.getArticleGoodNumber().equals(other.getArticleGoodNumber()))
            && (this.getArticleLookNumber() == null ? other.getArticleLookNumber() == null : this.getArticleLookNumber().equals(other.getArticleLookNumber()))
            && (this.getArticleCollectionNumber() == null ? other.getArticleCollectionNumber() == null : this.getArticleCollectionNumber().equals(other.getArticleCollectionNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getArticleTypeId() == null) ? 0 : getArticleTypeId().hashCode());
        result = prime * result + ((getArticleTitle() == null) ? 0 : getArticleTitle().hashCode());
        result = prime * result + ((getArticleAddTime() == null) ? 0 : getArticleAddTime().hashCode());
        result = prime * result + ((getArticleContext() == null) ? 0 : getArticleContext().hashCode());
        result = prime * result + ((getArticleGoodNumber() == null) ? 0 : getArticleGoodNumber().hashCode());
        result = prime * result + ((getArticleLookNumber() == null) ? 0 : getArticleLookNumber().hashCode());
        result = prime * result + ((getArticleCollectionNumber() == null) ? 0 : getArticleCollectionNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", userId=").append(userId);
        sb.append(", articleTypeId=").append(articleTypeId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleAddTime=").append(articleAddTime);
        sb.append(", ArticleContext=").append(articleContext);
        sb.append(", articleGoodNumber=").append(articleGoodNumber);
        sb.append(", articleLookNumber=").append(articleLookNumber);
        sb.append(", articleCollectionNumber=").append(articleCollectionNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}