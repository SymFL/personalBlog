package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章与标签对应关系
 * @TableName article_tag_list
 */
@TableName(value ="article_tag_list")
@Data
public class ArticleTagList implements Serializable {
    /**
     * 文章标签id
     */
    @TableId(value = "article_tag_list_id")
    private String articleTagListId;

    /**
     * 标签名称
     */
    @TableField(value = "article_id")
    private String articleId;

    /**
     * 文章标签id
     */
    @TableField(value = "article_tag_id")
    private String articleTagId;

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
        ArticleTagList other = (ArticleTagList) that;
        return (this.getArticleTagListId() == null ? other.getArticleTagListId() == null : this.getArticleTagListId().equals(other.getArticleTagListId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getArticleTagId() == null ? other.getArticleTagId() == null : this.getArticleTagId().equals(other.getArticleTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleTagListId() == null) ? 0 : getArticleTagListId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleTagId() == null) ? 0 : getArticleTagId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleTagListId=").append(articleTagListId);
        sb.append(", articleId=").append(articleId);
        sb.append(", articleTagId=").append(articleTagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}