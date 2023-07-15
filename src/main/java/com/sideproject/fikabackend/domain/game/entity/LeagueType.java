package com.sideproject.fikabackend.domain.game.entity;

public enum LeagueType {

    PREMIER_LEAGUE(47, "premier", "해외"),
    LA_LIGA(87, "laliga", "해외"),
    BUNDESLIGA(54, "bundesliga", "해외"),
    SERIE_A(55, "serieA", "해외"),
    LEAGUE_1(53, "league1", "해외"),
    K_LEAGUE_1(9080, "k-league1", "국내"),
    K_LEAGUE_2(9116, "k-league2", "국내");

    private final int leagueId;
    private final String leagueNm;
    private final String leagueTp;

    LeagueType(int leagueId, String leagueNm, String leagueTp) {
        this.leagueId = leagueId;
        this.leagueNm = leagueNm;
        this.leagueTp = leagueTp;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getLeagueNm() {
        return leagueNm;
    }

    public String getLeagueTp() {
        return leagueTp;
    }
}
