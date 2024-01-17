package com.csk2024.personalblog.common;

import com.csk2024.personalblog.intercepter.AdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局配置类
 */
@Configuration
public class GlobalConfig implements WebMvcConfigurer {
    /**
     * 管理员拦截器
     */
    @Bean
    public HandlerInterceptor getAdminInterceptor() {
        return new AdminInterceptor();
    }

    /**
     * 添加全局拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //管理员拦截
        registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/csk2024/**")
                .excludePathPatterns("/csk2024/login", "/csk2024/logout", "/csk2024/adminLogin");
    }
}
