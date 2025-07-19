package com.example.utils;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {
    // 静态变量，存放秘钥
    // private static final String JWT_SECRET = "MySuperSecureSecretKey123!@#";
    // 静态变量，生效时间
    private static final long JWT_TIME = 60 * 60 * 1000;

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 生成token
     */

    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析token
     */
    public static Map<String, Object> parseToken(String token) {
        return Jwts.parserBuilder() // 创建解析器构建器
                .setSigningKey(key) // 设置签名密钥
                .build() // 构建解析器
                .parseClaimsJws(token) // 解析 token
                .getBody(); // 获取 payload 中的声明
    }
}
