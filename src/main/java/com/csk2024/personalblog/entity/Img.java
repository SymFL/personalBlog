package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片
 * @TableName img
 */
@TableName(value ="img")
@Data
public class Img implements Serializable {
    /**
     * 图片id
     */
    @TableId(value = "img_id")
    private String imgId;

    /**
     * 图片大小
     */
    @TableField(value = "img_size")
    private Long imgSize;

    /**
     * 图片地址
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 图片添加时间
     */
    @TableField(value = "img_add_time")
    private Date imgAddTime;

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
        Img other = (Img) that;
        return (this.getImgId() == null ? other.getImgId() == null : this.getImgId().equals(other.getImgId()))
            && (this.getImgSize() == null ? other.getImgSize() == null : this.getImgSize().equals(other.getImgSize()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getImgAddTime() == null ? other.getImgAddTime() == null : this.getImgAddTime().equals(other.getImgAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getImgId() == null) ? 0 : getImgId().hashCode());
        result = prime * result + ((getImgSize() == null) ? 0 : getImgSize().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getImgAddTime() == null) ? 0 : getImgAddTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", imgId=").append(imgId);
        sb.append(", imgSize=").append(imgSize);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", imgAddTime=").append(imgAddTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}