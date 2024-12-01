package classes;

import data_structures.GameComparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameLibrary extends FileReader {
        private ArrayList<Game> games;

        public GameLibrary() {
            this.games = new ArrayList<>();
        }

        public void populateLibrary(String gameFile) {
            HashSet<Game> gameList = readGames(gameFile);
            this.games.addAll(gameList);
        }

        public ArrayList<Game> topKGames(int k) {
            ArrayList<Game> result = new ArrayList<>();
            if (this.games.isEmpty()) {
                return result;
            }
            if (this.games.size() < k) {
                k = this.games.size();
            }
            HashMap<Game, Double> rated = new HashMap<>();
            for (Game game : this.games) {
                double bayesianAverage = game.bayesianAverageRating(2, 3);
                rated.put(game, bayesianAverage);
            }
            GameComparator comparator = new GameComparator();
            for (Game game : rated.keySet()) {
                int location = 0;
                for (Game game2 : result) {
                    if (comparator.compare(game2, game) == 1) {
                        location++;
                    } }
                result.add(location, game);
            }
            if (result.size() > k) {
                int difference = result.size() - k;
                for (int i = 0; i < difference; i++) {
                    result.remove(result.size()-1);
                }
            }
            return result;
        }

    }

