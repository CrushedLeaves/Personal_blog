//前端控制器来拦截前端url请求
package com.example.controller;

//在spring MVC中的前端控制封装到了springboot框架中
//        DispatcherServlet 前端控制器拦截请求 /users
//        servlet 决定使用哪个 handler 处理
//        Spring 检测哪个控制器匹配 /users，Spring 从 @RequestMapping 中查找出需要的信息
//        Spring 找到正确的 Controller 方法后，开始执行 Controller 方法
//        返回 users 对象列表
//        根据与客户端交互需要返回 Json 或者 Xml 格式
import com.example.entity.RestBean;
import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.Imp.VerifyServiceImp;
import com.example.service.LoginService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//负责展示层的类标识为 Spring Bean

//该注解的作用不只是将类识别为Bean，
// 同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
// Spring本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，
// 用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {


    @Resource
    UserRepository repository;
    //提交注册功能
    @Resource
    VerifyServiceImp service;
    //登陆服务
    @Resource
    LoginService LoginService;

    //数据加密
    @Resource
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public RestBean<Void> register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String verify){

        if(service.doVerify(email,verify)) {

            //对密码加密处理
            String encoder = passwordEncoder.encode(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(encoder);
            user.setEmail(email);
            user.setIdentify("user");
            repository.save(user);
            return new RestBean<>(201, "注册成功");
        }else{
            System.out.println("注册失败");
            return new RestBean<>(402,"注册失败，请稍后再试");
        }
    }

    //获取邮件服务
    @RequestMapping("verify/{email}")
    public void getVerify(@PathVariable("email") String email){
        System.out.println("发送邮件中..."+email);
        service.sendVerify(email);
    }

    //登陆验证功能
    @PostMapping("login-failure")
    public RestBean<Void> loginFailure(){
        System.out.println("登陆失败");
        return new RestBean<>(401,"登陆失败,铁子");
    }

    //登录成功
    @PostMapping("login-success")
    public RestBean<User> loginSuccess(){
        //从security中获取那个用户登陆成功。
        SecurityContext context = SecurityContextHolder.getContext();
        User user = repository.findUserByUsername(context.getAuthentication().getName());

        //传递数据交给登录业务，
        /**
         * Param 用户信息
         * return 相应数据
         */
        return LoginService.login(user);
    }
    //退出登录
    @PostMapping("/logout")
    public RestBean logout(){
        return LoginService.logout();
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
