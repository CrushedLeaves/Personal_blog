package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


//返回数据的JSON响应表
@Data
@AllArgsConstructor
public class RestBean<T>{

    int code;

    String reason;

    T data;

    String token;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
