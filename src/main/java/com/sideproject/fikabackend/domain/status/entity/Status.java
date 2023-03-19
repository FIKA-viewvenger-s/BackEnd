package com.sideproject.fikabackend.domain.status.entity;

import com.sideproject.fikabackend.domain.user.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sttNm;

    //유저 정보
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private Long shooting;

    @Column(nullable = false)
    private Long drribble;

    @Column(nullable = false)
    private Long physic;

    @Column(nullable = false)
    private Long passing;

    @Column(nullable = false)
    private Long keeping;

    @Column(nullable = false)
    private Long mental;



}
