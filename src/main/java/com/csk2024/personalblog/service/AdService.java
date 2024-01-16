package com.csk2024.personalblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csk2024.personalblog.entity.Ad;
import com.csk2024.personalblog.vo.AdListVo;

import java.util.List;

/**
* @author 24387
* @description 针对表【ad(广告)】的数据库操作Service
* @createDate 2024-01-12 00:59:33
*/
public interface AdService extends IService<Ad> {

    List<AdListVo> adList(String adTypeId);
}
