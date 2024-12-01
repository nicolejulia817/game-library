
package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;


    public class FileReader {

        public FileReader() {
        }
        public static HashSet<Game> readGames(String csv) {
            HashSet<Game> result = new HashSet<>();
            try {
                ArrayList<String> readFile = new ArrayList<>(Files.readAllLines(Paths.get(csv)));
                for (String line : readFile) {
                    ArrayList<String> lineSplit = new ArrayList<>(Arrays.asList(line.split(",")));
                    readGamesHelper(result,lineSplit);
                }
            } catch (IOException | NullPointerException e) {
                return new HashSet<Game>();
            }
            return result;
        }

        public static void readGamesHelper(HashSet<Game> result, ArrayList<String> splitLine) {
            String title = splitLine.get(1);
            String publisher = splitLine.get(2);
            int ratingVal = Integer.parseInt(splitLine.get(3));
            Game newGame = new Game(title, publisher);
            newGame.addRating(ratingVal);
            for (Game game : result) {
                if (game.getTitle().equalsIgnoreCase(title) && game.getPublisher().equalsIgnoreCase(publisher)){
                    game.addRating(ratingVal);
                    return;
                }
            }
            result.add(newGame);
        }


}
