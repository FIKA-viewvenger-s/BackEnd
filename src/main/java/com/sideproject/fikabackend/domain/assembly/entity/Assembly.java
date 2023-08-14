package com.sideproject.fikabackend.domain.assembly.entity;

import com.sideproject.fikabackend.domain.address.entity.Address;
import com.sideproject.fikabackend.domain.game.entity.Game;
import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.global.util.Timestamped;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Assembly extends Timestamped {

    /**모임 PK**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assmId;

    /**모임 제목**/
    @NotNull(message = "assemblyTitle must not be null")
    private String assmTitle;

    /**모임 날짜**/
    @NotNull(message = "aeemblyDate must not be null")
    private String assmDt;

    /**모임 주소**/
    @NotNull(message = "assmAddr must not be null")
    private String assmAddr;

    /**모임장소 위도**/
    @NotNull(message = "assmLat must not be null")
    private String assmLat;

    /**모임장소 경도**/
    @NotNull(message = "assmLon must not be null")
    private String assmLon;

    /**장소 예약 여부**/
    @NotNull(message = "assmReserveStt must not be null")
    private String assassmReserveSttmDtCnt;

    /**리그**/
    @NotNull(message = "league must not be null")
    private String league;

    /**팀**/
    @NotNull(message = "team must not be null")
    private String team;

    /**태그**/
    @NotNull(message = "tag must not be null")
    @ElementCollection
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

}
