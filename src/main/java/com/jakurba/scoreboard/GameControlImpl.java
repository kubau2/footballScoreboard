package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import com.jakurba.exceptions.IncorrectTeamNameException;

import java.util.*;
import java.util.stream.IntStream;

import static com.jakurba.scoreboard.GameHelper.*;

class GameControlImpl implements GameControl {

    List<Game> listOfGames = new ArrayList<>();

    GameControlImpl() {
    }

    GameControlImpl(List<Game> listOfGames) {
        this.listOfGames = listOfGames;
    }

    @Override
    public Game startNewGame(String homeTeamName, String awayTeamName) throws IncorrectTeamNameException {
        checkIfTeamNameIsCorrect(homeTeamName);
        checkIfTeamNameIsCorrect(awayTeamName);
        Game newGame = new Game(getFirstAvailableGameId(), (byte) 0, (byte) 0, homeTeamName, awayTeamName);
        listOfGames.add(newGame);
        return newGame;
    }

    @Override
    public Game updateGameScore(short gameId, byte homeTeamScore, byte awayTeamScore) throws IncorrectScoreException, GameNotFoundException {
        checkIfNumberIsGreaterThan0AndLessThan100(homeTeamScore);
        checkIfNumberIsGreaterThan0AndLessThan100(awayTeamScore);
        Game gameFound = findGameById(gameId, listOfGames);
        gameFound.setHomeTeamScore(homeTeamScore);
        gameFound.setAwayTeamScore(awayTeamScore);
        return gameFound;
    }

    @Override
    public boolean finishGame(short gameId) throws GameNotFoundException {
        Game gameFound = findGameById(gameId, listOfGames);
        return listOfGames.remove(gameFound);
    }

    @Override
    public List<Game> getGamesInProgressOrderedByTotalScoreThenByNewestGameStartTime() {
        if (listOfGames.isEmpty()) {
            return Collections.emptyList();
        }

        listOfGames.sort(Comparator.comparing(Game::getGameStart));
        listOfGames.sort(Comparator.comparing(game -> (game.getHomeTeamScore() + game.getAwayTeamScore())));
        Collections.reverse(listOfGames);
        return listOfGames;
    }

    private short getFirstAvailableGameId() {
        if (listOfGames.isEmpty()) {
            return 0;
        } else {
            // Extract and sort the IDs, converting them to int for stream operations
            List<Integer> sortedIDs = listOfGames.stream()
                    .map(obj -> (int) obj.getId())
                    .sorted()
                    .toList();

            // Find the first missing ID
            OptionalInt firstFreeID = IntStream.range(1, sortedIDs.size() + 1)
                    .filter(id -> !sortedIDs.contains(id))
                    .findFirst();

            // If no gap is found, return the next ID, otherwise return the found gap as a short
            return (short) firstFreeID.orElse(sortedIDs.size() + 1);
        }

    }
}
