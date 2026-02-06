package com.zhang.Config;

import com.zhang.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor TokenInterceptor;
    //添加拦截器
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(TokenInterceptor)
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/admin/emp/login")//登录接口不拦截
                .excludePathPatterns("/user/login");
    }

}
