package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Admin;
import com.csk2024.personalblog.service.AdminService;
import com.csk2024.personalblog.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【admin(管理员)】的数据库操作Service实现
* @createDate 2024-01-17 15:37:52
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




