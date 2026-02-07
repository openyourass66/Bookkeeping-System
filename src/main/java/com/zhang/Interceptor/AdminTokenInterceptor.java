package com.zhang.Interceptor;
import com.zhang.Utils.CurrentHolder;
import com.zhang.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AdminTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            log.info("令牌为空，响应401");
            return false;
        }
        try {
            Claims claims = JwtUtils.parseJWT(token);
            if (claims.get("empId") != null) {
                log.info("职员id：{}", claims.get("empId"));
                Long empId = Long.valueOf(claims.get("empId").toString());
                CurrentHolder.setCurrentId(empId);
            }
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}