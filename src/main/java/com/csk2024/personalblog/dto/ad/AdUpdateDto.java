package com.csk2024.personalblog.dto.ad;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端传回广告数据类
 */
@Data
public class AdUpdateDto {
    /**
     * 广告id
     */
    private String adId;

    /**
     * 广告类型
     */
    @NotBlank(message = "数据缺失")
    private String adTypeId;

    /**
     * 广告标题
     */
    @NotBlank(message = "数据缺失")
    private String adTitle;

    /**
     * 广告图片的url地址
     */
    @NotBlank(message = "数据缺失")
    private String adImgUrl;

    /**
     * 广告的url地址
     */
    @NotBlank(message = "数据缺失")
    private String adLinkUrl;

    /**
     * 广告排序，数值的小排在前面
     */
    @NotNull(message = "数据缺失")
    private Integer adSort;

    /**
     * 广告开始时间
     */
    @NotBlank(message = "数据缺失")
    private String adBeginTime;

    /**
     * 广告结束时间
     */
    @NotBlank(message = "数据缺失")
    private String adEndTime;
}
