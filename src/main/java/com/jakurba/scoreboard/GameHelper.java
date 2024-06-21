package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import com.jakurba.exceptions.IncorrectTeamNameException;
import java.util.List;
import java.util.Optional;
import static java.lang.Short.valueOf;

class GameHelper {

    private GameHelper() {
    }

    protected static Game findGameByIdInList(short gameId, List<Game> listOfGamesInProgresses) throws GameNotFoundException {
        if (listOfGamesInProgresses.isEmpty()) {
            throw new GameNotFoundException();
        }

        Optional<Game> foundGame = listOfGamesInProgresses.stream()
                .filter(game -> valueOf(gameId).equals(game.getId()))
                .findFirst();

        return foundGame.orElseThrow(GameNotFoundException::new);
    }

    protected static void checkIfNumberIsBetween0And100(byte number) throws IncorrectScoreException {
        if (number < 0 || number > 100) {
            throw new IncorrectScoreException();
        }
    }

    protected static void checkIfTeamNameIsCorrect(String teamName) throws IncorrectTeamNameException {
        if (teamName == null || teamName.isBlank()) {
            throw new IncorrectTeamNameException();
        }
    }

}
