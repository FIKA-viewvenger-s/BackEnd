package com.sideproject.fikabackend.domain.status.entity;

import com.sideproject.fikabackend.domain.member.entity.Member;
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
    @JoinColumn(name = "member_nm",nullable = false)
    private Member member;

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
