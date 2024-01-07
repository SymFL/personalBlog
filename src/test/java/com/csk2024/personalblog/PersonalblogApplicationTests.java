package com.csk2024.personalblog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.csk2024.personalblog.entity.User;
import com.csk2024.personalblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PersonalblogApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUserName("test" + i);
            user.setUserPassword(SecureUtil.md5("123456"));
            user.setUserRegisterTime(DateUtil.date());
            user.setUserFrozen(0);
            users.add(user);
        }
        userService.saveBatch(users,50);

    }

}
