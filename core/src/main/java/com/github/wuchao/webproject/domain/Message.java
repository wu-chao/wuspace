package com.github.wuchao.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
//@Table(name = "messages")
public class Message  {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false)
    private Long id;

//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "content")
//    private String content;
//
//    @Column(name = "sender_id")
//    private User sender;

    private User receiver;
}
