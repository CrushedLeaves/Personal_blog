package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//权限表
@Data
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    int mid;

    //权限名称
    @Column(name = "menu_name")
    String menu_name;

    //权限是否可用
    @Column(name = "status")
    int status;

    //权限作用
    @Column(name = "perms")
    String perms;

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles = new HashSet<Role>(0);


}
