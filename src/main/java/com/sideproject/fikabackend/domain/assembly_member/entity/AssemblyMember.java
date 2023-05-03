package com.sideproject.fikabackend.domain.assembly_member.entity;

import com.sideproject.fikabackend.domain.assembly.entity.Assembly;
import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.global.util.Timestamped;

import javax.persistence.*;

@Entity
public class AssemblyMember extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AssmMmbrId;

    @ManyToOne
    @JoinColumn
    private Assembly assembly;

    @ManyToOne
    @JoinColumn
    private Member member;



}
