package com.jakurba.scoreboard;

public final class GameControlFactory {

    private GameControlFactory() {

    }

    /**
     * Creates a new instance of {@link GameControl}
     * @return the new instance
     */
    public static GameControl createGameControl() {
        return new GameControlImpl();
    }

}
