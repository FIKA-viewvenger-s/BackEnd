package com.sideproject.fikabackend.domain.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fbId;

    @NotNull(message = "FootballType must not be null")
    private GameType gameTp;

    @NotNull(message = "Football-League must not be null")
    private String fbLeague;

    @NotNull(message = "Football-Home must not be null")
    private String fbHome;

    @NotNull(message = "Football-Away must not be null")
    private String fbAway;

    @NotNull(message = "Football-Stadium must not be null")
    private String fbStadium;

    @NotNull(message = "Football-Date must not be null")
    private String fbDate;

    @NotNull(message = "Football-Time must not be null")
    private String fbTime;
}
