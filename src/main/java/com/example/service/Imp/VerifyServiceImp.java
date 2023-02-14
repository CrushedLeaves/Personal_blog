package com.example.service.Imp;

import com.example.service.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class VerifyServiceImp implements VerifyService {

    @Resource
    JavaMailSender sender;

    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendVerify(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("个人博客登录验证码");
        Random random = new Random();
        int code = random.nextInt(899999)+100000;
        //保存到Redis数据库
        template.opsForValue().set("verify_"+email,code+"");
        message.setText("你的注册验证码为:"+code+",有效时间为三分钟,请勿传递给他人使用");
        message.setTo(email);
        message.setFrom("zjp_student@163.com");
        sender.send(message);
    }

    @Override
    public boolean doVerify(String email,String verify) {
        System.out.println(email+":"+verify);
        String string = template.opsForValue().get("verify_"+email);
        if(string==null){
            return false;
        }else{
            return true;
        }
    }
}
