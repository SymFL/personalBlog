package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Link;
import com.csk2024.personalblog.service.LinkService;
import com.csk2024.personalblog.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【link(友情连接)】的数据库操作Service实现
* @createDate 2024-01-10 23:51:15
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

}




