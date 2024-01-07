package com.csk2024.personalblog.controller;

import com.csk2024.personalblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String index(){
        userService.list().forEach(System.out::println);
        return "ok";
    }
}
