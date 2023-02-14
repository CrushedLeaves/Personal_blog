package com.example.service;

import org.springframework.stereotype.Service;


//发送邮件服务（负责发送，和验证邮件）
@Service
public interface VerifyService {
     void sendVerify(String email);
     boolean doVerify(String email,String verify);
}
