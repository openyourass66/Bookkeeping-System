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
public class UserTokenInterceptor implements HandlerInterceptor {
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
            // 校验是否为用户 token（必须包含 userId）
            if (claims.get("userId") == null) {
                response.setStatus(403);
                log.warn("非用户尝试访问用户端接口");
                return false;
            }
            Long userId = Long.valueOf(claims.get("userId").toString());
            CurrentHolder.setCurrentId(userId);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}