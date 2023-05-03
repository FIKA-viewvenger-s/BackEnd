package com.sideproject.fikabackend.domain.assembly.entity;

import com.sideproject.fikabackend.domain.address.entity.Address;
import com.sideproject.fikabackend.domain.football.entity.Football;
import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.global.util.Timestamped;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Assembly extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assmId;

    @NotNull(message = "assemblyTitle must not be null")
    private String assmTitle;

    @NotNull(message = "aeemblyDate must not be null")
    private String assmDt;

    @ManyToOne
    @JoinColumn(name="ADDRESS_ID")
    private Address address;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Football football;


}
