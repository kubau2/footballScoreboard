package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import com.jakurba.exceptions.IncorrectTeamNameException;

import java.util.List;

public interface GameControl {

    /**
     * Creates a new game, adds it to the game list and returns the newly created game.
     * @param homeTeamName Name of the home team
     * @param awayTeamName Name of the away team
     * @return new {@link Game}
     * @throws IncorrectTeamNameException Incorrect Team name has been entered
     */
    Game startNewGame(String homeTeamName, String awayTeamName) throws IncorrectTeamNameException;

    /**
     * Update the game score. Provide gameId and new scores
     * @param gameId gameId to search for
     * @param homeTeamScore new score for Home team
     * @param awayTeamScore new score for Away team
     * @return updated {@link Game}
     * @throws IncorrectScoreException the newly entered score is incorrect
     * @throws GameNotFoundException game with given ID has not been found
     */
    Game updateGameScore(short gameId, byte homeTeamScore, byte awayTeamScore) throws IncorrectScoreException, GameNotFoundException;

    /**
     * Removes the game from the game list
     * @param gameId gameId to search for
     * @return true if game has been successfully removed from the game list
     * @throws GameNotFoundException game with given ID has not been found
     */
    boolean finishGame(short gameId) throws GameNotFoundException;

    /**
     * Return sorted game list. First the games are sorted by total score, and if 2 games
     * have the same score, then a newer game will be listed first.
     * @return sorted game list
     */
    List<Game> getGamesInProgressOrderedByTotalScoreThenByNewestGameStartTime();

}
