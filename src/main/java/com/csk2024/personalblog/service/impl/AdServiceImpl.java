package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Ad;
import com.csk2024.personalblog.service.AdService;
import com.csk2024.personalblog.mapper.AdMapper;
import com.csk2024.personalblog.vo.AdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24387
* @description 针对表【ad(广告)】的数据库操作Service实现
* @createDate 2024-01-12 00:59:33
*/
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad>
    implements AdService{

    @Autowired
    private  AdMapper adMapper;
    @Override
    public List<AdListVo> adList(String adTypeId) {
        return adMapper.adList(adTypeId);
    }
}




