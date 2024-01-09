package com.csk2024.personalblog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 文章类型列表视图对象
 */
@Data
public class ArticleTypeVo {
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


    /**
     * 文章类型下的文章数量
     */
    @TableField(exist = false)
    private Integer articleCount;
}
