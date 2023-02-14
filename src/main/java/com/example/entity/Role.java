package com.example.entity;

//角色表

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    int rid;

    @Column(name = "role_name")
    String role_name;

    @Column(name = "role_key")
    String role_key;

    @Column(name = "status")
    int status;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns = {@JoinColumn(name = "rid",referencedColumnName = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "id",referencedColumnName = "id")}
    )
    private Set<User> users = new HashSet<User>(0);

    @ManyToMany
    @JoinTable(name = "menu_role",
               joinColumns = {@JoinColumn(name = "rid",referencedColumnName = "rid")},
               inverseJoinColumns = {@JoinColumn(name = "mid",referencedColumnName = "mid")}
    )
    private Set<Menu> menus = new HashSet<Menu>(0);

}
