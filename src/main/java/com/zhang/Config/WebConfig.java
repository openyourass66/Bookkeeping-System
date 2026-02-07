package com.zhang.Config;

import com.zhang.Interceptor.AdminTokenInterceptor;
import com.zhang.Interceptor.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminTokenInterceptor adminTokenInterceptor;
    @Autowired
    private UserTokenInterceptor userTokenInterceptor;
    //添加拦截器
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(adminTokenInterceptor)
                .addPathPatterns("/admin/**")//拦截所有请求
                .excludePathPatterns("/admin/emp/login");//登录接口不拦截
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/user/**")//拦截所有请求
                .excludePathPatterns("/user/user/login");//登录接口不拦截
    }

}
