####dat1
1完成maven的下载安装并且集成到idea中，解决了security依赖注入无法解析的问题
2创建实体类，使用Spring-data-Jpa完成实现数据库中创建数据。
3通过使用@repository实现了对数据的访问。
4引入spring-mvc以及spring-web依赖，实现了前端控制。
5实现邮件验证码模块的实现
####day2
1,spring分离的跨域问题
2，设置security为自己的登录页
####day3
1,完成前端控制器的测试以及实现（了解了@Controller以及@RestController并且初次使用Mapping注解）
2,对security中的UserDetailsService接口，UserDetails，UserDetailsServiceAutoConfiguration。
        UserDetailsService:将查询的数据源加载到spring-security中，
        UserDetails：保存UserDETAILSsERVICE加载的用户信息。
        UserDetailsServiceAutoConfiguration:初始化了一个UserDetailsManager 类型的Bean。UserDetailsManager 类型负责对安全用户实体抽象UserDetails的增删查改操作。同时还继承了UserDetailsService接口。
3，自定义自己的UserDetailsManage的Bean,从而实现自己的用户管理逻辑

        
####day4
1,学习spring-security,内容如下
        认证管理/权限管理
        基本原理（过滤链机制）
        过滤链的加载过程（把过滤器加载到过滤链中）
        实现两个接口（PasswordEncoding接口的实现，UserDetailsService实现，一个用户存储用户数据，一个用于登录加密）
        设置用户名和密码（第一，通过配置文件，第二，通过配置类，第三，自定义编写实现类）
        自定义用户界面兼容Spring-security页面
                1，重写http方法
                2，过滤器重写，添加token,
2，学习token内容，学习spring-jwt令牌的工作原理，以及了解token在jwt中的实现
####day5
1,添加表项，增添了角色权限表，使用jpa实现了多对多映射的关系表建立。
2,实现前端mackdown文档传到后端
3，实现前端主页面布局
