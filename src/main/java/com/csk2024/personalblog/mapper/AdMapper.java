package com.csk2024.personalblog.mapper;

import com.csk2024.personalblog.entity.Ad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csk2024.personalblog.vo.AdListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 24387
* @description 针对表【ad(广告)】的数据库操作Mapper
* @createDate 2024-01-12 00:59:33
* @Entity com.csk2024.personalblog.entity.Ad
*/
public interface AdMapper extends BaseMapper<Ad> {

    List<AdListVo> adList(@Param("adTypeId") String adTypeId);
}




