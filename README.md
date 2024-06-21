# Live Football World Cup Scoreboard

The Live Football World Cup Scoreboard library allows you to manage and display live scores of ongoing football matches. It supports starting new games, updating scores, finishing games, and getting a summary of games in progress.

## Features:

    -Start a new game: Initialize a new match with the home and away teams, starting with a score of 0-0.
    -Update score: Update the score of an ongoing match with the absolute scores for the home and away teams by providing gameId and new scores.
    -Finish game: Finish an ongoing match and remove it from the scoreboard.
    -Get summary: Retrieve a summary of all ongoing matches, ordered by their total score. If two matches have the same total score, they are ordered by the most recently started match.


## Usage:

*Add the library to your project: \out\artifacts\footballScoreboard_jar\footballScoreboard.jar

*Use it by calling: GameControlFactory.createGameControl();


### Some assumptions for the project:

-It might be possible that person entering the new score made a mistake. Therefore, entering a lower value for the score is possible

-The game will probably never have a score of 100, that's why it has been limited to this value

-The method "getFirstAvailableGameId" might theoretically try to return a value bigger than short, but the probability of it is so small, that's why I didn't implement any exception for this

-Optional mechanism to implement if it would be european football: check if score has changed by 1

-I'm allowing to start games with same names, as maybe two countries (different leagues), can be playing at the same time
