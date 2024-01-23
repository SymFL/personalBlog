package com.csk2024.personalblog.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用户拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(Objects.isNull(session.getAttribute("user"))){
            response.sendRedirect("/user/login");
            String currentUrl = request.getRequestURI();
            session.setAttribute("originalUrl", currentUrl);
            return false;
        }
        return true;
    }
}
