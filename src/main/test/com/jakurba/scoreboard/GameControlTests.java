package com.jakurba.scoreboard;

import com.jakurba.exceptions.GameNotFoundException;
import com.jakurba.exceptions.IncorrectScoreException;
import com.jakurba.exceptions.IncorrectTeamNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class GameControlTests {

    @Test
    void createNewGameOk() throws IncorrectTeamNameException {
        //given
        GameControl gameControl = GameControlFactory.createGameControl();

        //when
        Game game = gameControl.startNewGame("Miszczowie", "Kołkogłowi");

        //then
        Assertions.assertNotNull(game);
        Assertions.assertEquals(0, game.getHomeTeamScore());
        Assertions.assertEquals(0, game.getAwayTeamScore());

        //Check if the time between test start and the object creation is below 1 second
        Assertions.assertTrue(ChronoUnit.SECONDS.between(game.getGameStart(), LocalDateTime.now()) < 1);
    }

    @Test
    void createNewGameErrorIncorrectTeamName() {
        //given
        GameControl gameControl = GameControlFactory.createGameControl();

        //when and then
        Assertions.assertThrows(IncorrectTeamNameException.class, () -> gameControl.startNewGame("Miszczowie", null));
    }

    @Test
    void createFiveNewGames() throws IncorrectTeamNameException {
        //given
        GameControl gameControl = GameControlFactory.createGameControl();

        //when
        gameControl.startNewGame("Miszczowie", "Kołkogłowi");
        gameControl.startNewGame("Pomorzanie", "Ślązoki");
        gameControl.startNewGame("Wschód", "Zachód");
        gameControl.startNewGame("Legia", "Warszawa");
        gameControl.startNewGame("Niemcy", "Polska");

        //then
        Assertions.assertEquals(5, gameControl.getGamesInProgressOrderedByTotalScoreThenByNewestGamestartTime().size());
    }

    @Test
    void updateScoreOk() throws IncorrectScoreException, GameNotFoundException {
        //given
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

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
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

        short gameIdToUpdate = 0;
        byte newScoreHomeTeam = -5;
        byte newScoreAwayTeam = 9;

        //when and then
        Assertions.assertThrows(IncorrectScoreException.class, () -> gameControl.updateGameScore(gameIdToUpdate, newScoreHomeTeam, newScoreAwayTeam));
    }

    @Test
    void updateScoreErrorIncorrectGameId() {
        //given
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

        short gameIdToUpdate = -10;
        byte newScoreHomeTeam = 2;
        byte newScoreAwayTeam = 9;

        //when and then
        Assertions.assertThrows(GameNotFoundException.class, () -> gameControl.updateGameScore(gameIdToUpdate, newScoreHomeTeam, newScoreAwayTeam));
    }

    @Test
    void finishGameOk() throws GameNotFoundException {
        //given
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

        //when and then
        Assertions.assertTrue(gameControl.finishGame((short) 0));
    }

    @Test
    void finishGameErrorGameNotFound() {
        //given
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

        //when and then
        Assertions.assertThrows(GameNotFoundException.class, () -> gameControl.finishGame((short) 3));
    }

    @Test
    void checkIfSortedByTotalScoreThenByNewestGamestartTime() {
        //given
        GameControlImpl gameControl = new GameControlImpl(prepareListWithFewGames());

        List<Game> result = gameControl.getGamesInProgressOrderedByTotalScoreThenByNewestGamestartTime();

        Assertions.assertEquals(9, result.get(0).getId());
        Assertions.assertEquals(6, result.get(1).getId());
        Assertions.assertEquals(8, result.get(2).getId());
        Assertions.assertEquals(7, result.get(3).getId());
        Assertions.assertEquals(4, result.get(4).getId());
        Assertions.assertEquals(2, result.get(5).getId());
        Assertions.assertEquals(1, result.get(6).getId());
        Assertions.assertEquals(0, result.get(7).getId());
    }

    @Test
    void testAllFunctionalities() throws GameNotFoundException, IncorrectScoreException, IncorrectTeamNameException {
        GameControl gameControl = GameControlFactory.createGameControl();

        Game game1 = gameControl.startNewGame("Włochy", "Niemcy");
        Game game2 = gameControl.startNewGame("Estonia", "Litwa");
        Game game3 = gameControl.startNewGame("ZSZ nr1","Liceum 2");

        gameControl.updateGameScore(game1.getId(), (byte) 3, (byte) 3); //should get an Id = 0
        gameControl.updateGameScore(game2.getId(), (byte) 5, (byte) 5); //should get an Id = 1
        gameControl.updateGameScore(game3.getId(), (byte) 6, (byte) 6); //should get an Id = 2

        gameControl.finishGame(game2.getId());

        Game game4 = gameControl.startNewGame("USA", "Anglia");

        gameControl.updateGameScore(game4.getId(), (byte) 3, (byte) 3); //should get an Id = 1

        List<Game> gamesListed = gameControl.getGamesInProgressOrderedByTotalScoreThenByNewestGamestartTime();

        Assertions.assertEquals(2, gamesListed.get(0).getId());
        Assertions.assertEquals(1, gamesListed.get(1).getId());
        Assertions.assertEquals(0, gamesListed.get(2).getId());
    }

    private static List<Game> prepareListWithFewGames() {
        List<Game> listOfGamesToTest = new ArrayList<>();
        listOfGamesToTest.add(new Game((short) 0, (byte) 0, (byte) 0,"USA", "Anglia"));
        listOfGamesToTest.add(new Game((short) 1, (byte) 0, (byte) 2,"ZSZ nr1","Liceum 2"));
        listOfGamesToTest.add(new Game((short) 2, (byte) 1, (byte) 1,"Włochy", "Niemcy"));
        listOfGamesToTest.add(new Game((short) 4, (byte) 5, (byte) 5,"Miszczowie", "Kołkogłowi"));
        listOfGamesToTest.add(new Game((short) 8, (byte) 10, (byte) 10,"Pomorzanie", "Ślązoki"));
        listOfGamesToTest.add(new Game((short) 6, (byte) 10, (byte) 10,"Wschód", "Zachód"));
        listOfGamesToTest.add(new Game((short) 7, (byte) 9, (byte) 10,"Legia", "Warszawa"));
        listOfGamesToTest.add(new Game((short) 9, (byte) 10, (byte) 10,"Niemcy", "Polska"));
        return listOfGamesToTest;
    }

}
