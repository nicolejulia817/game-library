package data_structures;

import classes.Game;

import java.util.Comparator;

public class GameComparator implements Comparator<Game> {
    @Override
    public int compare(Game a, Game b) {
        double bayAverageA = a.bayesianAverageRating(2, 3);
        double bayAverageB = b.bayesianAverageRating(2, 3);
        if (bayAverageA < bayAverageB) { return -1; }
        if (bayAverageA > bayAverageB) { return 1; }
        return 0;
    }
}