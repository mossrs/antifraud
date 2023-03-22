package com.mossflower.antifraud.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mossflower.antifraud.common.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author z's'b
 */
public class JwtUtil {

    /**
     * 获得token
     * java-jwt的第三方资源库有问题 每次都生成同样的token 所以暂时不用
     */
    public static String generateToken(@NotNull Map<String, String> map) {
        // 获取JWT构造器
        JWTCreator.Builder builder = JWT.create();
        // 设置自定义payload载荷
        map.forEach(builder::withClaim);
        // 设置签发时间
        builder.withIssuedAt(new Date());
        // 设置过期时间
        builder.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        // 设置签名算法后得到token并返回
        return builder.sign(Algorithm.HMAC256(Constant.TOKEN_SECRET));
    }

    /**
     * 验证token
     * 如果有任何验证异常，此处都会抛出异常
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(Constant.TOKEN_SECRET)).build().verify(token);
    }

    /**
     * 获得token
     */
    public static String getToken(Map<String, Object> map) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, Constant.TOKEN_SECRET)
                .compact();
    }

    /**
     * 解析token
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(Constant.TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

}
