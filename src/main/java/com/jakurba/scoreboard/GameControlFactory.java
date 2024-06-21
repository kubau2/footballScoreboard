package com.jakurba.scoreboard;

public final class GameControlFactory {

    private GameControlFactory() {

    }

    public static GameControl createGameControl() {
        return new GameControlImpl();
    }

}
