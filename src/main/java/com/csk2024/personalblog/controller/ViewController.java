package com.csk2024.personalblog.controller;

import cn.hutool.captcha.CircleCaptcha;
import com.csk2024.personalblog.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ViewController {
    /**
     * 获取图像验证码
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CircleCaptcha captcha = CommonUtils.getCaptcha(request);
        captcha.write(response.getOutputStream());
    }
}
