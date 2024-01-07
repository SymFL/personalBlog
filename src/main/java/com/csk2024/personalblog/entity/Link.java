package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 友情连接
 * @TableName link
 */
@TableName(value ="link")
@Data
public class Link implements Serializable {
    /**
     * 友情连接id
     */
    @TableId(value = "link_id")
    private String linkId;

    /**
     * 友情连接标题
     */
    @TableField(value = "link_title")
    private String linkTitle;

    /**
     * 友情连接的地址
     */
    @TableField(value = "link_url")
    private String linkUrl;

    /**
     * 友情连接logo
     */
    @TableField(value = "link_logo_url")
    private String linkLogoUrl;

    /**
     * 添加友情连接的时间
     */
    @TableField(value = "link_add_time")
    private Date linkAddTime;

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
        Link other = (Link) that;
        return (this.getLinkId() == null ? other.getLinkId() == null : this.getLinkId().equals(other.getLinkId()))
            && (this.getLinkTitle() == null ? other.getLinkTitle() == null : this.getLinkTitle().equals(other.getLinkTitle()))
            && (this.getLinkUrl() == null ? other.getLinkUrl() == null : this.getLinkUrl().equals(other.getLinkUrl()))
            && (this.getLinkLogoUrl() == null ? other.getLinkLogoUrl() == null : this.getLinkLogoUrl().equals(other.getLinkLogoUrl()))
            && (this.getLinkAddTime() == null ? other.getLinkAddTime() == null : this.getLinkAddTime().equals(other.getLinkAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLinkId() == null) ? 0 : getLinkId().hashCode());
        result = prime * result + ((getLinkTitle() == null) ? 0 : getLinkTitle().hashCode());
        result = prime * result + ((getLinkUrl() == null) ? 0 : getLinkUrl().hashCode());
        result = prime * result + ((getLinkLogoUrl() == null) ? 0 : getLinkLogoUrl().hashCode());
        result = prime * result + ((getLinkAddTime() == null) ? 0 : getLinkAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", linkId=").append(linkId);
        sb.append(", linkTitle=").append(linkTitle);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", linkLogoUrl=").append(linkLogoUrl);
        sb.append(", linkAddTime=").append(linkAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}