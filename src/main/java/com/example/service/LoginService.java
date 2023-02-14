package com.example.service;

import com.example.entity.RestBean;
import com.example.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    RestBean<User> login(User user);
    RestBean logout();
}
