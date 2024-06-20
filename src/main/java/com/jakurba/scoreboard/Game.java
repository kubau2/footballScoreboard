package com.jakurba.scoreboard;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Game {

    private final short id;
    private byte homeTeamScore = 0;
    private byte awayTeamScore = 0;
    private final String homeTeamName;
    private final String awayTeamName;

    private final LocalDateTime gameStart = LocalDateTime.now();

    protected void setHomeTeamScore(byte homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    protected void setAwayTeamScore(byte awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    Game(short id, byte homeTeamScore, byte awayTeamScore, String homeTeamName, String awayTeamName) {
        this.id = id;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

}
