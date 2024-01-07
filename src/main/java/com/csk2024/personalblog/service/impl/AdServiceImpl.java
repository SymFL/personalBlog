package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Ad;
import com.csk2024.personalblog.service.AdService;
import com.csk2024.personalblog.mapper.AdMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【ad(广告)】的数据库操作Service实现
* @createDate 2024-01-04 20:16:37
*/
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad>
    implements AdService{

}




