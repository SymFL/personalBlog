package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章类型
 * @TableName article_type
 */
@TableName(value ="article_type")
@Data
public class ArticleType implements Serializable {
    /**
     * 文章类型id
     */
    @TableId(value = "article_type_id")
    private String articleTypeId;

    /**
     * 文章类型名字
     */
    @TableField(value = "article_type_name")
    private String articleTypeName;

    /**
     * 文章排序
     */
    @TableField(value = "article_type_sort")
    private Integer articleTypeSort;

    /**
     * 文章类型添加时间
     */
    @TableField(value = "article_type_add_time")
    private Date articleTypeAddTime;

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
        ArticleType other = (ArticleType) that;
        return (this.getArticleTypeId() == null ? other.getArticleTypeId() == null : this.getArticleTypeId().equals(other.getArticleTypeId()))
            && (this.getArticleTypeName() == null ? other.getArticleTypeName() == null : this.getArticleTypeName().equals(other.getArticleTypeName()))
            && (this.getArticleTypeSort() == null ? other.getArticleTypeSort() == null : this.getArticleTypeSort().equals(other.getArticleTypeSort()))
            && (this.getArticleTypeAddTime() == null ? other.getArticleTypeAddTime() == null : this.getArticleTypeAddTime().equals(other.getArticleTypeAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleTypeId() == null) ? 0 : getArticleTypeId().hashCode());
        result = prime * result + ((getArticleTypeName() == null) ? 0 : getArticleTypeName().hashCode());
        result = prime * result + ((getArticleTypeSort() == null) ? 0 : getArticleTypeSort().hashCode());
        result = prime * result + ((getArticleTypeAddTime() == null) ? 0 : getArticleTypeAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleTypeId=").append(articleTypeId);
        sb.append(", articleTypeName=").append(articleTypeName);
        sb.append(", articleTypeSort=").append(articleTypeSort);
        sb.append(", articleTypeAddTime=").append(articleTypeAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}