package com.jakurba.exceptions;

public class GameNotFoundException extends Exception {
    public GameNotFoundException() {
        super("Game not found");
    }
}
