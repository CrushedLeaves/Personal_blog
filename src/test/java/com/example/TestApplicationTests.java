package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.entity.User;
import com.example.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
@SpringBootTest
class TestApplicationTests {

//    @Resource
//    StringRedisTemplate template;
//
    @Resource
    UserRepository repository;
//
//
//    @Test
//    void contextLoads() {
//        ValueOperations<String, String> operations =  template.opsForValue();
//        operations.set("name","zip");
//        System.out.println(operations.get("name"));
//    }
////测试使用jedis访问redis数据库
//    @Test
//    void test(){
//        Jedis jedis = new Jedis("localhost",6379);
//        jedis.set("name","zjp");
//        System.out.println(jedis.get("name"));
//        jedis.close();
//    }
////  继承jpa测试访问mysql数据库，jpa中封装了常用的数据使用指令
//
//
//    @Test
//    void test1(){
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword("user");
//        user.setEmail("zjp_student@163.com");
//        user.setIdentify("7777");
//        repository.save(user);
//
//        repository.findAll(PageRequest.of(0,1)).forEach(System.out::println);
//
//        System.out.println(repository.findById(1));
//
//
//    }
//
//    //测试链式调用
    @Test
    void test3(){
        User user = repository.findUserByUsername("admin");

        System.out.println(user.getUsername()+":"+user.getPassword());
    }

//    //JWT测试
//    @Test
//    public void testGenerateToken(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND,60);
//
//        String token = JWT.create()
//                .withHeader(new HashMap<>())
//                .withClaim("userid",21)
//                .withClaim("username","baobao")
//                .withExpiresAt(calendar.getTime())
//                .sign(Algorithm.HMAC256("wuHao"));
//        System.out.println(token);
//    }
//    //获取JWT原文测试
//    public void testResolveToken(){
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("wuHao")).build();
//
//        DecodedJWT decodedJWT = jwtVerifier.verify(("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTM3MDY2NjMsInVzZXJpZCI6MjEsInVzZXJuYW1lIjoiYmFvYmFvIn0.qAKPSWnQTEC9_dPBEBX3Z2rOTzM__N_eqALC7ICF5KU"));
//
//        Claim userID = decodedJWT.getClaim("userID");
//        Claim username = decodedJWT.getClaim("username");
//        System.out.println(userID.asString());
//        System.out.println(username.asString());
//        System.out.println(decodedJWT.getExpiresAt());
//
//    }
//    @Test
//    public void testTime(){
//        System.out.println(System.currentTimeMillis());
//    }
//
//    //加密测试
//    @Test
//    public void password(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User)authentication.getPrincipal();
//        System.out.println(user.getUsername());
//    }
//    //用户权限查询
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void select(){
//        User user = repository.findUserByUsername("admin");
//        System.out.println(user.getEmail());
//        System.out.println(user.getId());
//    }

}















