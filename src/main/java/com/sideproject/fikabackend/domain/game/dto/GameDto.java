package com.sideproject.fikabackend.domain.game.dto;

import com.sideproject.fikabackend.domain.game.entity.GameType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class GameDto {

    /**PK**/
    @NotNull
    private Long gmId;

    /**게임 구분(국내/해외)**/
    @NotNull(message = "FootballType must not be null")
    private String gmTp;

    /**게임 라운드**/
    private Long gmRound;

    /**게임 리그**/
    @NotNull(message = "Football-League must not be null")
    private String gmLeague;

    /**홈팀 아이디**/
    @NotNull
    private String gmHomeId;

    /**홈팀 이름**/
    @NotNull(message = "Football-Home must not be null")
    private String gmHome;

    /**홈팀 앰뷸럼 이미지**/
    @NotNull(message = "Football-Home must not be null")
    private String gmHomeImg;

    /**어웨이팀 아이디**/
    @NotNull
    private String gmAwayId;

    /**어웨이팀 이름**/
    @NotNull(message = "Football-Away must not be null")
    private String gmAway;

    /**어웨이팀 앰뷸럼 이미지**/
    @NotNull(message = "Football-Away must not be null")
    private String gmAwayImg;

    /**경기 일자**/
    @NotNull(message = "Football-Date must not be null")
    private String gmDate;

    /**경기 시간**/
    @NotNull(message = "Football-Time must not be null")
    private String gmTime;
}
