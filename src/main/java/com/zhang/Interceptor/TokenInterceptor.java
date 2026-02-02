package com.zhang.Interceptor;

import com.zhang.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.zhang.Utils.CurrentHolder;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    //请求处理之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String path = request.getRequestURI();
        //获取请求头中的令牌
        String token= request.getHeader("token");
        //判断令牌是否存在，不存在则返回错误信息
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }
        //验证令牌，如果验证失败则返回错误信息
        try{
            Claims claims = JwtUtils.parseJWT(token);
            Long userId = Long.valueOf(claims.get("userId").toString());
            CurrentHolder.setCurrentId(userId);
            log.info("当前用户id为：{}",userId);
        } catch (Exception e) {
            log.info("令牌非法，响应401{}",e);
            response.setStatus(401);
            return false;
        }
        //放行
        log.info("令牌合法，放行");
        return true;
    }
    //请求处理之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
    }
}
