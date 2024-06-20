package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameControlTests {

    @Test
    void createNewMatchOk() {
        //given
        GameControl gameControl = GameControlFactory.createGameControl();

        //when
        Game game = gameControl.startNewGame();

        //then
        Assertions.assertNotNull(game);
        Assertions.assertEquals(0, game.getHomeTeamScore());
        Assertions.assertEquals(0, game.getAwayTeamScore());

        //Maybe check the date too, but there might be a slight difference before the object is created
//        assertEquals(LocalDateTime.now(), match.getGameStart());
    }

    @Test
    void updateScoreOk() {
        //given
        List<Game> listOfGamesToTest = new ArrayList<>();
        listOfGamesToTest.add(new Game());

        GameControlImpl gameControl = new GameControlImpl(listOfGamesToTest);

        short gameIdToUpdate = 0;
        byte newScoreHomeTeam = 5;
        byte newScoreAwayTeam = 9;

        //when
        Game gameUpdated = gameControl.updateGameScore(gameIdToUpdate, newScoreHomeTeam, newScoreAwayTeam);

        //then
        Assertions.assertEquals(gameIdToUpdate, gameUpdated.getId());
        Assertions.assertEquals(newScoreHomeTeam, gameUpdated.getHomeTeamScore());
        Assertions.assertEquals(newScoreAwayTeam, gameUpdated.getAwayTeamScore());
    }

    @Test
    void updateScoreErrorIncorrectScore() {
        //given
        List<Game> listOfGamesToTest = new ArrayList<>();
        listOfGamesToTest.add(new Game());

        GameControlImpl gameControl = new GameControlImpl(listOfGamesToTest);

        short gameIdToUpdate = 0;
        byte newScoreHomeTeam = -5;
        byte newScoreAwayTeam = 9;

        //when and then
        Assertions.assertThrows(IncorrectScoreException.class, () -> gameControl.updateGameScore(gameIdToUpdate, newScoreHomeTeam, newScoreAwayTeam));
    }

    @Test
    void finishGameOk() {
        //given
        List<Game> listOfGamesToTest = new ArrayList<>();
        listOfGamesToTest.add(new Game());

        GameControlImpl gameControl = new GameControlImpl(listOfGamesToTest);

        //when and then
        Assertions.assertTrue(gameControl.finishGame((short) 0));
    }

    @Test
    void finishGameErrorGameNotFound() {
        //given
        List<Game> listOfGamesToTest = new ArrayList<>();
        listOfGamesToTest.add(new Game());

        GameControlImpl gameControl = new GameControlImpl(listOfGamesToTest);

        //when and then
        Assertions.assertThrows(GameNotFoundException.class, () -> gameControl.finishGame((short) 1));
    }

}
