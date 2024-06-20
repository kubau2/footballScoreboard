package com.jakurba.exceptions;

public class IncorrectScoreException extends Exception {
    public IncorrectScoreException() {
        super("The entered score is incorrect");
    }
}
