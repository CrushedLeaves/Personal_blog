package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    int bid;

    @Column(name = "title")
    String title;

    @Column(name = "type")
    String type;

    @Column(name = "context")
    String context;

    @Column(name = "create_time")
    Date create_time;

    @Column(name = "picture")
    String picture;

    @Column(name = "context_picture_address")
    String contextPictureAddress;

    public Book() {

    }
}
