package com.zhang.Interceptor;
import com.zhang.Properties.JwtProperties;
import com.zhang.Utils.CurrentHolder;
import com.zhang.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AdminTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            log.info("令牌为空，响应401");
            return false;
        }
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties, token);
            // 校验是否为管理员 token（必须包含 empId）
            if (claims.get("empId") == null) {
                response.setStatus(403);
                log.warn("非管理员尝试访问管理端接口");
                return false;
            }
            Long empId = Long.valueOf(claims.get("empId").toString());
            CurrentHolder.setCurrentId(empId);
        } catch (Exception e) {
            log.error("令牌解析失败：{}", e.getMessage());
            response.setStatus(401);
            return false;
        }
        return true;
    }

}