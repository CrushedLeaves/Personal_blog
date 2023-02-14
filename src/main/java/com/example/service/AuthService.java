package com.example.service;

import com.example.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//自定义一个实现从数据库中加载数据到spring-security
import javax.annotation.Resource;
import java.util.List;

@Service("userDetailsService")
public class AuthService implements UserDetailsService {
    @Resource
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.entity.User user = repository.findUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户为存在，请确认输入的用户名是否正确");
        }

        //建立权限信息对象
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(user.getUsername(),user.getPassword(),auths);
//        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }
}
