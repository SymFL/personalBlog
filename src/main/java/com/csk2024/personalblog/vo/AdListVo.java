package com.csk2024.personalblog.vo;

import lombok.Data;

import java.util.Date;

/**
 * 广告列表类，包含广告类型
 */
@Data
public class AdListVo {
    /**
     * 广告id
     */
    private String adId;

    /**
     * 广告类型id
     */
    private String adTypeId;

    /**
     * 广告类型标题
     */
    private String adTypeTitle;

    /**
     * 广告标题
     */
    private String adTitle;

    /**
     * 广告图片的url地址
     */
    private String adImgUrl;

    /**
     * 广告的url地址
     */
    private String adLinkUrl;

    /**
     * 广告排序，数值的小排在前面
     */
    private Integer adSort;

    /**
     * 广告开始时间
     */
    private Date adBeginTime;

    /**
     * 广告结束时间
     */
    private Date adEndTime;
}
