package tests;
import classes.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGame {
    private final double EPSILON = 0.01;

    @Test
    public void testGetSet() {
        Game pacman = new Game("Pacman","Atari");
        //Test get() and set()
        assertTrue("Could not get Game's title.\n",pacman.getTitle().equals("Pacman"));
        assertTrue("Could not get Game's publisher.\n",pacman.getPublisher().equals("Atari"));
        pacman.setTitle("Mario");
        pacman.setPublisher("Nintendo");
        assertTrue("Could not set Game's title.\n",pacman.getTitle().equals("Mario"));
        assertTrue("Could not set Game's producer.\n",pacman.getPublisher().equals("Nintendo"));
    }

    @Test (timeout = 20000)
    public void testAddRatings(){
        Game pacman = new Game("Pacman","Atari");
        //Test adding invalid ratings
        pacman.addRating(0);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        pacman.addRating(6);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        pacman.addRating(4632784);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        pacman.addRating(-5);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        pacman.addRating(Integer.MAX_VALUE);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        pacman.addRating(Integer.MIN_VALUE);
        for (int i = 0; i < 5; i++) assertEquals(Integer.valueOf(0), pacman.getRatings().get(i));
        //Test adding valid ratings
        pacman.addRating(1);
        pacman.addRating(5);
        pacman.addRating(2);
        pacman.addRating(3);
        pacman.addRating(3);
        pacman.addRating(4);
        //Mix in some invalid ratings between valid ratings and make sure they weren't added
        pacman.addRating(2);
        pacman.addRating(2);
        pacman.addRating(54);
        pacman.addRating(3);
        pacman.addRating(-3);
        pacman.addRating(2);
        pacman.addRating(6);
        pacman.addRating(5);
        pacman.addRating(0);
        assertEquals(Integer.valueOf(1), pacman.getRatings().get(0));
        assertEquals(Integer.valueOf(4), pacman.getRatings().get(1));
        assertEquals(Integer.valueOf(3), pacman.getRatings().get(2));
        assertEquals(Integer.valueOf(1), pacman.getRatings().get(3));
        assertEquals(Integer.valueOf(2), pacman.getRatings().get(4));
    }

    @Test (timeout = 20000)
    public void testAverageRating(){
        Game tetris = new Game("Tetris", "Pajitnov");
        //Test bayesianAverage of a game with no ratings
        assertEquals("A game with no ratings did not produce an average rating of 0.0\n",0.0,tetris.bayesianAverageRating(2,3),EPSILON);
        //Test bayesianAverage of a game with no ratings (attempted to add invalid ratings)
        assertEquals("Adding invalid ratings to a game with no ratings did not produce an average rating of 0.0\n",0.0,tetris.bayesianAverageRating(2,3),EPSILON);
        //Add valid ratings
        tetris.addRating(1);
        tetris.addRating(2);
        tetris.addRating(3);
        tetris.addRating(4);
        tetris.addRating(5);
        //Test invalid arguments to bayesianAverage
        assertEquals("bayesianAverage() did not return -1 for an invalid value argument\n",-1.0,tetris.bayesianAverageRating(15,6), EPSILON);
        assertEquals("bayesianAverage() did not return -1 for an invalid value argument\n",-1.0,tetris.bayesianAverageRating(15,9), EPSILON);
        assertEquals("bayesianAverage() did not return -1 for an invalid value argument\n",-1.0,tetris.bayesianAverageRating(8,0), EPSILON);
        assertEquals("bayesianAverage() did not return -1 for an invalid value argument\n",-1.0,tetris.bayesianAverageRating(8,-3), EPSILON);
        assertEquals("bayesianAverage() did not return -1 for an invalid amount of extra values\n",-1.0,tetris.bayesianAverageRating(0,4),EPSILON);
        assertEquals("bayesianAverage() did not return -1 for an invalid amount of extra values\n",-1.0,tetris.bayesianAverageRating(-7,2),EPSILON);
        assertEquals("bayesianAverage() did not return -1 for all invalid arguments\n",-1.0,tetris.bayesianAverageRating(0,0), EPSILON);
        assertEquals("bayesianAverage() did not return -1 for all invalid arguments\n",-1.0,tetris.bayesianAverageRating(5,127),EPSILON);
        //Test correct bayesianAverage calculation
        assertEquals("Bayesian average incorrect\n",3.0, tetris.bayesianAverageRating(2,3),EPSILON);
        assertEquals("Bayesian average incorrect\n",2.6, tetris.bayesianAverageRating(3,2),EPSILON);
        assertEquals("Bayesian average incorrect\n",2.0, tetris.bayesianAverageRating(5,1),EPSILON);
    }

    @Test
    public void testPrintRatings(){
        Game pokemon = new Game("Pokemon", "Nintendo");
        assertEquals("This game has no ratings.\n", pokemon.printRatings());
        pokemon.addRating(10);
        pokemon.addRating(5);
        pokemon.addRating(3);
        pokemon.addRating(0);
        pokemon.addRating(2);
        pokemon.addRating(4);
        pokemon.addRating(5);
        pokemon.addRating(100);
        pokemon.addRating(4);
        pokemon.addRating(-9876);
        pokemon.addRating(5);
        //the only valid ratings added are 5,3,2,4,5,4,5
        // 2 extra val 3 = 3.8
        String expected = "Average rating: " + 3.8 + "\n" +
                5 + " stars: " + 3 + "\n" +
                4 + " stars: " + 2 + "\n" +
                3 + " stars: " + 1 + "\n" +
                2 + " stars: " + 1 + "\n" +
                1 + " stars: " + 0 + "\n";
        assertEquals(expected, pokemon.printRatings());
    }
}
