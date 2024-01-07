package com.csk2024.personalblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csk2024.personalblog.dto.user.UserListPageDto;
import com.csk2024.personalblog.entity.User;
import com.csk2024.personalblog.service.UserService;
import com.csk2024.personalblog.utils.CommonPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csk2024/user")
public class UserController {

    @Autowired
    private UserService userService;


}
