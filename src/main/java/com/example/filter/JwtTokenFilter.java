package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.User;
import com.example.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


//token有效性拦截器

@Component
public class JwtTokenFilter extends OncePerRequestFilter{

    @Resource
    private StringRedisTemplate template;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //获取token
            String token = request.getHeader("Authentication");
            //判断token为空
            if(!StringUtils.hasText(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            //判断token是否正确(验签)
            String userid = null;
            if(JwtUtils.verify(token)){
                System.out.println("登录成功");
                DecodedJWT decodedJWT = JwtUtils.getTokenINfo(token);
                userid = decodedJWT.getClaim("userToken").asString();
            }

            //从redis中获取用户信息
            String userInfo =  template.opsForValue().get("user"+userid);
            User user = JSON.parseObject(userInfo,User.class);
            System.out.println("Redis获取到:"+user.getUsername());
            if(Objects.isNull(user)){
                throw new RuntimeException("未查询到此用户");
            }
            //TODO 获取权限信息到authenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }catch(Exception e){
            throw new BadCredentialsException("登录凭证失效,请重新登陆");
        }
    }
}
