package com.csk2024.personalblog.dto.ad;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdTypeUpdateDto {
    /**
     * 广告类型id
     */
    private String adTypeId;

    /**
     * 广告类型名称
     */
    @NotBlank(message = "广告类型名称不允许为空")
    private String adTypeTitle;

    /**
     * 广告标识（首页顶部广告、轮播图广告、文章详情广告）
     */
    @NotBlank(message = "广告类型标识不允许为空")
    private String adTypeTag;

    /**
     * 广告类型排序，数值小的在前
     */
    @NotNull(message = "广告类型排序依据不允许为空")
    private Integer adTypeSort;
}
