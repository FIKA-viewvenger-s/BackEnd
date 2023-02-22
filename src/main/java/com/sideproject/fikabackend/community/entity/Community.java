package com.sideproject.fikabackend.community.entity;

import com.sideproject.fikabackend.global.Timestamped;
import com.sideproject.fikabackend.user.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Community extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNm;

    @ManyToOne
    @JoinColumn(name = "userNm")
    private User user;

    @Column(nullable = false)
    private String cateGoru;

    @Column(nullable = false)
    private Long viewCnt;

    @Column(nullable = false)
    private String content;

    // 동영상, 이미지는 null 허용?
    @Column
    private String videoUrl;

    @Column
    private String imgUrl;

}
