package com.example.demo.utils;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;
public class JwtUtils {
    private static long expire = 1000 * 60 * 60 * 24 * 7;
    private static final String secret=Base64.getEncoder().encodeToString("shijian".getBytes());

    /**
     * 生成token
     * @date 2020/7/21 16:25
     * @author zbmds
     */
    public static String createToken(Map<String,String>claimmap){
        Date now=new Date();
        Date expireDate=new Date(now.getTime()+expire);
        Map<String,Object>map=new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        JWTCreator.Builder builder=JWT.create();
        claimmap.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        return builder.withHeader(map).withExpiresAt(expireDate).sign(Algorithm.HMAC256(secret));
    }
    /**
     * 验证token
     * @date 2020/7/21 16:25
     * @author zbmds
     */
    public static DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }
    public static int getUserId(String token){
        return Integer.parseInt(verifyToken(token).getClaim("id").asString());
    }
}
