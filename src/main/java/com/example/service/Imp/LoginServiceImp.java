package com.example.service.Imp;

import com.alibaba.fastjson.JSON;
import com.example.entity.RestBean;
import com.example.entity.User;
import com.example.service.LoginService;
import com.example.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {

    @Resource
    StringRedisTemplate template;
    @Override
    public RestBean<User> login(User user) {
        user.setPassword("");
        //使用userid生成JWT，并将userid所在用户信息(json)存入redis
        String userid = String.valueOf(user.getId());
        String userJson = JSON.toJSONString(user);
        //存入Redis
        template.opsForValue().set("user"+userid,userJson);
        System.out.println("Redis存入key:user"+userid);
        Map<String,String> map = new HashMap<>();
        map.put("userToken",userid);
        String token = JwtUtils.sign(map);

        //返回token给响应体
        return new RestBean<>(200,"用户登陆成功",user,token);
    }

    @Override
    public RestBean<Void> logout() {
        //获取推出用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        System.out.println("用户退出");
        //从redis中删除该用户信息
        template.delete("user"+user.getId());
        return new RestBean(204,"退出成功");
    }
}
