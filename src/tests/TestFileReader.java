package tests;

import classes.Game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class TestFileReader {

    public static void compareGameLists(ArrayList<Game> a1, ArrayList<Game> a2){
        HashSet<Game> h1 = new HashSet<>(a1);
        HashSet<Game> h2 = new HashSet<>(a2);


    }
    @Test
    public void testReadGames() {
        ArrayList<Game> expected = new ArrayList<>();
        Game minecraft = new Game("Minecraft","Mojang");
        minecraft.addRating(4);
        Game overwatch = new Game("Overwatch","Blizzard Entertainment");
        overwatch.addRating(3);
        Game gtaV = new Game("Grand Theft Auto V","Rockstar Games");
        gtaV.addRating(2);
        Game fortnite = new Game("Fortnite","Epic Games");
        fortnite.addRating(1);
        Game animalCrossing = new Game("Animal Crossing: New Horizons","Nintendo");
        fortnite.addRating(5);
        Game bloodborne = new Game("Bloodborne","Sony Interactive Entertainment");
        bloodborne.addRating(4);

    }
}
