package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class testController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('test')")
    public String test(String username, HttpServletRequest request){
        request.getSession().setAttribute("username",username);
        return "hello";
    }
}
