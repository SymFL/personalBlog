package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告类型
 * @TableName ad_type
 */
@TableName(value ="ad_type")
@Data
public class AdType implements Serializable {
    /**
     * 广告类型id
     */
    @TableId(value = "ad_type_id")
    private String adTypeId;

    /**
     * 广告类型名称
     */
    @TableField(value = "ad_type_title")
    private String adTypeTitle;

    /**
     * 广告标识（首页顶部广告、轮播图广告、文章详情广告）
     */
    @TableField(value = "ad_type_tag")
    private String adTypeTag;

    /**
     * 广告类型排序，数值小的在前
     */
    @TableField(value = "ad_type_sort")
    private Integer adTypeSort;

    /**
     * 广告类型添加时间
     */
    @TableField(value = "ad_type_add_time")
    private Date adTypeAddTime;

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
        AdType other = (AdType) that;
        return (this.getAdTypeId() == null ? other.getAdTypeId() == null : this.getAdTypeId().equals(other.getAdTypeId()))
            && (this.getAdTypeTitle() == null ? other.getAdTypeTitle() == null : this.getAdTypeTitle().equals(other.getAdTypeTitle()))
            && (this.getAdTypeTag() == null ? other.getAdTypeTag() == null : this.getAdTypeTag().equals(other.getAdTypeTag()))
            && (this.getAdTypeSort() == null ? other.getAdTypeSort() == null : this.getAdTypeSort().equals(other.getAdTypeSort()))
            && (this.getAdTypeAddTime() == null ? other.getAdTypeAddTime() == null : this.getAdTypeAddTime().equals(other.getAdTypeAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdTypeId() == null) ? 0 : getAdTypeId().hashCode());
        result = prime * result + ((getAdTypeTitle() == null) ? 0 : getAdTypeTitle().hashCode());
        result = prime * result + ((getAdTypeTag() == null) ? 0 : getAdTypeTag().hashCode());
        result = prime * result + ((getAdTypeSort() == null) ? 0 : getAdTypeSort().hashCode());
        result = prime * result + ((getAdTypeAddTime() == null) ? 0 : getAdTypeAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adTypeId=").append(adTypeId);
        sb.append(", adTypeTitle=").append(adTypeTitle);
        sb.append(", adTypeTag=").append(adTypeTag);
        sb.append(", adTypeSort=").append(adTypeSort);
        sb.append(", adTypeAddTime=").append(adTypeAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}