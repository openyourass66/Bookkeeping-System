package com.zhang.Config;

import com.zhang.Interceptor.AdminTokenInterceptor;
import com.zhang.Interceptor.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminTokenInterceptor adminTokenInterceptor;
    @Autowired
    private UserTokenInterceptor userTokenInterceptor;
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 管理端拦截器：仅拦截 /admin/**
        registry.addInterceptor(adminTokenInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/emp/login");

        // 用户端拦截器：仅拦截 /user/**
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login");
    }

}
