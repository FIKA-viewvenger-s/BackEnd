package com.sideproject.fikabackend.domain.team.entity;

import com.sideproject.fikabackend.domain.member.entity.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamNm;

    @Column(nullable = false)
    private String region;

    //유저 정보
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();


}
