# footballScoreboard

Some assumptions for the project:
-It might be possible that person entering the new score made a mistake. Therefore, entering a lower value for the score is possible
-The game will probably never have a score of 100, that's why it has been limited to this value
-The method "getFirstAvailableGameId" might theoretically try to return a value bigger than short, but the probability of it is so small, that's why I didn't implement any exception for this