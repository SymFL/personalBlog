package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告
 * @TableName ad
 */
@TableName(value ="ad")
@Data
public class Ad implements Serializable {
    /**
     * 广告id
     */
    @TableId(value = "ad_id")
    private String adId;

    /**
     * 广告类型
     */
    @TableField(value = "ad_type_id")
    private String adTypeId;

    /**
     * 广告标题
     */
    @TableField(value = "ad_title")
    private String adTitle;

    /**
     * 广告图片的url地址
     */
    @TableField(value = "ad_img_url")
    private String adImgUrl;

    /**
     * 广告的url地址
     */
    @TableField(value = "ad_link_url")
    private String adLinkUrl;

    /**
     * 广告排序，数值的小排在前面
     */
    @TableField(value = "ad_sort")
    private Integer adSort;

    /**
     * 广告开始时间
     */
    @TableField(value = "ad_begin_time")
    private Date adBeginTime;

    /**
     * 广告结束时间
     */
    @TableField(value = "ad_end_time")
    private Date adEndTime;

    /**
     * 添加广告的时间
     */
    @TableField(value = "ad_add_time")
    private Date adAddTime;

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
        Ad other = (Ad) that;
        return (this.getAdId() == null ? other.getAdId() == null : this.getAdId().equals(other.getAdId()))
            && (this.getAdTypeId() == null ? other.getAdTypeId() == null : this.getAdTypeId().equals(other.getAdTypeId()))
            && (this.getAdTitle() == null ? other.getAdTitle() == null : this.getAdTitle().equals(other.getAdTitle()))
            && (this.getAdImgUrl() == null ? other.getAdImgUrl() == null : this.getAdImgUrl().equals(other.getAdImgUrl()))
            && (this.getAdLinkUrl() == null ? other.getAdLinkUrl() == null : this.getAdLinkUrl().equals(other.getAdLinkUrl()))
            && (this.getAdSort() == null ? other.getAdSort() == null : this.getAdSort().equals(other.getAdSort()))
            && (this.getAdBeginTime() == null ? other.getAdBeginTime() == null : this.getAdBeginTime().equals(other.getAdBeginTime()))
            && (this.getAdEndTime() == null ? other.getAdEndTime() == null : this.getAdEndTime().equals(other.getAdEndTime()))
            && (this.getAdAddTime() == null ? other.getAdAddTime() == null : this.getAdAddTime().equals(other.getAdAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdId() == null) ? 0 : getAdId().hashCode());
        result = prime * result + ((getAdTypeId() == null) ? 0 : getAdTypeId().hashCode());
        result = prime * result + ((getAdTitle() == null) ? 0 : getAdTitle().hashCode());
        result = prime * result + ((getAdImgUrl() == null) ? 0 : getAdImgUrl().hashCode());
        result = prime * result + ((getAdLinkUrl() == null) ? 0 : getAdLinkUrl().hashCode());
        result = prime * result + ((getAdSort() == null) ? 0 : getAdSort().hashCode());
        result = prime * result + ((getAdBeginTime() == null) ? 0 : getAdBeginTime().hashCode());
        result = prime * result + ((getAdEndTime() == null) ? 0 : getAdEndTime().hashCode());
        result = prime * result + ((getAdAddTime() == null) ? 0 : getAdAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adId=").append(adId);
        sb.append(", adTypeId=").append(adTypeId);
        sb.append(", adTitle=").append(adTitle);
        sb.append(", adImgUrl=").append(adImgUrl);
        sb.append(", adLinkUrl=").append(adLinkUrl);
        sb.append(", adSort=").append(adSort);
        sb.append(", adBeginTime=").append(adBeginTime);
        sb.append(", adEndTime=").append(adEndTime);
        sb.append(", adAddTime=").append(adAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}