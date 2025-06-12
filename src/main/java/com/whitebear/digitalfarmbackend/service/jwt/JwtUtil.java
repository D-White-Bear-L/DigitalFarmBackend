package com.whitebear.digitalfarmbackend.service.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String SECRET_KEY = "a49020fd431f642f754204dac7397be8e72a7837a9fc2d587e880dc5212a3b45"; // 必须32字节+
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 1天
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //  用户名
                .setIssuedAt(new Date()) //  当前时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) //  过期时间
                .signWith(key, SignatureAlgorithm.HS256) // 签名算法和密钥
                .compact(); //  生成token
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}