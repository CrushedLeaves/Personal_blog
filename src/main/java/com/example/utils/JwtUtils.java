package com.example.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

//实现JWT服务的工具类
public class JwtUtils {

    //设置token私钥
    private static final String SECRET_KEY = "WuHaoDeMao";


    //生成token
    public static String sign(Map<String,String> map){
        try{
            //设置过期时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,7);
            //私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            //添加payload信息，并且放入token中
            JWTCreator.Builder  builder= JWT.create();
            map.forEach((k,v)->{builder.withClaim(k,v);});
            return builder.withExpiresAt(calendar.getTime())
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //判断token是否正确,验签
    /**
     * @param token,username,password
     * @return true,返回
     */
    public static boolean verify(String token){
        try{
            //设置解密方式，以及密钥
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            //创建验证对象
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .build();
            //验证token，通过不报异常，否且产生异常。
            DecodedJWT jwt = jwtVerifier.verify(token);
            System.out.println("验证用户token:"+jwt.getClaim("user").asString());
            return true;
        }catch(Exception e){
            System.out.println("验签失败");
            return false;
        }
    }
    /**
     * 根据token获取用户信息
     */
    public static DecodedJWT getTokenINfo(String token){
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
        return jwt;
    }
}
