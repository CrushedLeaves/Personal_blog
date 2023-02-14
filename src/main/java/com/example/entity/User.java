//通过jpa自动实现在数据库中添加表
package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//用户表
@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "identify")
    String identify;

    @Column(name = "password")
    String password;

    //多对多映射到角色表
//    @ManyToMany(mappedBy = "users")
//    private Set<Role> roles = new HashSet<Role>(0);
}
