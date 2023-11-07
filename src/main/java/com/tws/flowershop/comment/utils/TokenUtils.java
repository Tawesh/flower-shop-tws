package com.tws.flowershop.comment.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Tws
 * @Date:2023/8/16-19:31
 */
@Component
public class TokenUtils {

    /**
     * 生成对应的key和token信息
     *
     * @param id       用户id
     * @param account  用户账号
     * @param username 用户名
     */
    public Map<String, String> setToken(String id, String account, String username) {

        // 7 days
        Date expiration = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
        String secretKey = generateSecretKey();
        String token = Jwts.builder()
                .setSubject("login verify")
                .claim("userId", id)
                .claim("account", account)
                .claim("username", username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", secretKey);
        map.put("token", token);
        return map;
    }

    public String generateSecretKey() {
        // 设置密钥长度，可以根据需求调整
        // 256 bits
        int keyLength = 256;
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[keyLength / 8];
        secureRandom.nextBytes(keyBytes);

        // 将随机字节编码为 Base64 字符串

        return Base64.getEncoder().encodeToString(keyBytes);
    }

    /**
     * @param SECRET_KEY token秘钥
     * @param jwtToken   token
     * @return true / false
     */

    public boolean validateJwt(String SECRET_KEY, String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            // 获取过期时间
            Date expiration = claims.getExpiration();
            Date currentTime = new Date();

            // 校验过期时间是否在当前时间之后
            return !expiration.before(currentTime);
        } catch (Exception e) {
            // 解析或验证失败，返回 false 表示 token 无效
            return false;
        }
    }

    /**
     * 解析token
     *
     * @param SECRET_KEY token的秘钥
     * @param jwtToken   token
     * @return Claims对象含user-info
     */

    public Claims resolveToken(String SECRET_KEY, String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);

            return claimsJws.getBody();
        } catch (Exception e) {
            // 解析或验证失败，返回 false 表示 token 无效
            return null;
        }
    }

}
