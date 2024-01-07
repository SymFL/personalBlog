package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.User;
import com.csk2024.personalblog.service.UserService;
import com.csk2024.personalblog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-04 20:17:32
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




