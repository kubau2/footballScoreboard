package com.jakurba.scoreboard;

import lombok.Getter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class Game {

    private final short id;
    private byte homeTeamScore;
    private byte awayTeamScore;
    private final String homeTeamName;
    private final String awayTeamName;

    private final LocalDateTime gameStartTime = LocalDateTime.now(ZoneOffset.UTC);

    void setTeamScores(byte homeTeamScore, byte awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
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
