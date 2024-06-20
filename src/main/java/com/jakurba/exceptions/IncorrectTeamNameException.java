package com.jakurba.exceptions;

public class IncorrectTeamNameException extends Exception {
    public IncorrectTeamNameException() {
        super("The team name is incorrect");
    }
}
