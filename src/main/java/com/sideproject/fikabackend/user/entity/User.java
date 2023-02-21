package com.sideproject.fikabackend.user.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNm;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String profileImg;

    @Column(nullable = false)
    private String uId;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private Long userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private int age;

    ////////////////////////////////////////////// 회원 가입 이후 받는 정보
    @Column
    private int mannerPnt;

    @Column
    private int reCode;

    @Column
    private String position;

    @Column
    private String tier;

    @Column
    private String odds;

    //스탯번호
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Status> status = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teamNm",nullable = false)
    private Team team;

}
