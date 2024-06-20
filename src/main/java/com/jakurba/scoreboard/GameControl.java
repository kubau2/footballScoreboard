package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;

import java.util.List;

public interface GameControl {

    Game startNewGame();

    Game updateGameScore(short gameId, byte homeTeamScore, byte awayTeamScore) throws IncorrectScoreException, GameNotFoundException;

    boolean finishGame(short gameId) throws GameNotFoundException;

    List<Game> getGamesInProgressOrderedByTotalScoreThenByNewestGamestartTime();

}
