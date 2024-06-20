package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import com.jakurba.exceptions.IncorrectTeamNameException;

import java.util.List;

public interface GameControl {

    Game startNewGame(String homeTeamName, String awayTeamName) throws IncorrectTeamNameException;

    Game updateGameScore(short gameId, byte homeTeamScore, byte awayTeamScore) throws IncorrectScoreException, GameNotFoundException;

    boolean finishGame(short gameId) throws GameNotFoundException;

    List<Game> getGamesInProgressOrderedByTotalScoreThenByNewestGamestartTime();

}
