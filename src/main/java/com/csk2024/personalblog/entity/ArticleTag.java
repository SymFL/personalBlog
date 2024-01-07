package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签
 * @TableName article_tag
 */
@TableName(value ="article_tag")
@Data
public class ArticleTag implements Serializable {
    /**
     * 文章标签id
     */
    @TableId(value = "article_tag_id")
    private String articleTagId;

    /**
     * 标签名称
     */
    @TableField(value = "article_tag_name")
    private String articleTagName;

    /**
     * 添加时间
     */
    @TableField(value = "article_tag_add_time")
    private Date articleTagAddTime;

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
        ArticleTag other = (ArticleTag) that;
        return (this.getArticleTagId() == null ? other.getArticleTagId() == null : this.getArticleTagId().equals(other.getArticleTagId()))
            && (this.getArticleTagName() == null ? other.getArticleTagName() == null : this.getArticleTagName().equals(other.getArticleTagName()))
            && (this.getArticleTagAddTime() == null ? other.getArticleTagAddTime() == null : this.getArticleTagAddTime().equals(other.getArticleTagAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleTagId() == null) ? 0 : getArticleTagId().hashCode());
        result = prime * result + ((getArticleTagName() == null) ? 0 : getArticleTagName().hashCode());
        result = prime * result + ((getArticleTagAddTime() == null) ? 0 : getArticleTagAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleTagId=").append(articleTagId);
        sb.append(", articleTagName=").append(articleTagName);
        sb.append(", articleTagAddTime=").append(articleTagAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}