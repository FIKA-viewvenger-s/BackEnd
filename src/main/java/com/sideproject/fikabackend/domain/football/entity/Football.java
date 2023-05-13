package com.sideproject.fikabackend.domain.football.entity;

import com.sideproject.fikabackend.domain.game.entity.GameType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Football {

    /**축구 PK**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fbId;

    /** 축구 구분**/
    @NotNull(message = "FootballType must not be null")
    private GameType fbTp;

    /**축구 리그**/
    @NotNull(message = "Football-League must not be null")
    private String fbLeague;

    /**축구팀 이름**/
    @NotNull(message = "FootballTeam-Name must not be null")
    private String fbNm;

    /** 경기수**/
    @NotNull(message = "Football-Played must not be null")
    private String fbPlayed;

    /**승점**/
    @NotNull(message = "Football-Stadium must not be null")
    private String fbPoints;

    /**승**/
    @NotNull(message = "Football-Won must not be null")
    private String fbWon;

    /**무승부**/
    @NotNull(message = "Football-Draw must not be null")
    private String fbDraw;

    /**패**/
    @NotNull(message = "Football-Loss must not be null")
    private String fbLoss;

    /**득점**/
    @NotNull(message = "Football-GF must not be null")
    private String fbGf;

    /**실점**/
    @NotNull(message = "Football-GA must not be null")
    private String fbGa;

    /**득실차**/
    @NotNull(message = "Football-GD must not be null")
    private String fbGd;


}
