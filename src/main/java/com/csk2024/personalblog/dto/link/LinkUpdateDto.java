package com.csk2024.personalblog.dto.link;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端传回友链修改数据类
 */
@Data
public class LinkUpdateDto {
    /**
     * 友情连接id
     */
    private String linkId;

    /**
     * 友情连接标题
     */
    @NotBlank(message = "友链名称不能为空")
    private String linkTitle;

    /**
     * 友情连接排序，数字小的在前面
     */
    @NotNull(message = "友链排序依据不能为空")
    private Integer linkSort;

    /**
     * 友情连接的地址
     */
    @NotBlank(message = "友链地址不能为空")
    private String linkUrl;

    /**
     * 友情连接logo
     */
    private String linkLogoUrl;
}
