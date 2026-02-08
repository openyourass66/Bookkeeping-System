package com.zhang.Utils;

import com.zhang.Properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(JwtProperties jwtProperties,Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpire()))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwtProperties，jwt
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(JwtProperties jwtProperties,String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
