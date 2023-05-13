package com.sideproject.fikabackend.domain.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Game {

    /**게임 PK**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gmId;

    /**게임 구분(국내/해외)**/
    @NotNull(message = "FootballType must not be null")
    private GameType gmTp;

    /**게임 리그**/
    @NotNull(message = "Football-League must not be null")
    private String gmLeague;

    /**홈팀**/
    @NotNull(message = "Football-Home must not be null")
    private String gmHome;

    /**어웨이팀**/
    @NotNull(message = "Football-Away must not be null")
    private String gmAway;

    /**경기 장소**/
    @NotNull(message = "Football-Stadium must not be null")
    private String gmStadium;

    /**경기 일자**/
    @NotNull(message = "Football-Date must not be null")
    private String gmDate;

    /**경기 시간**/
    @NotNull(message = "Football-Time must not be null")
    private String gmTime;
}
