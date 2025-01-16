package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class HangmanTest {
    private Hangman hangman;

    @Before
    public void setUp() {
        hangman = new Hangman("example", "medium", 5, "Player1", "SinglePlayer");
    }

    @Test
    public void testInitialization() {
        assertEquals("example", hangman.wordToGuess);
        assertEquals("medium", hangman.difficultyLevel);
        assertEquals(5, hangman.remainingAttempts);
        assertEquals("Player1", hangman.playerName);
        assertEquals("SinglePlayer", hangman.gameMode);
        assertArrayEquals(new char[]{'_', '_', '_', '_', '_', '_', '_'}, hangman.guessedWord);
    }

    @Test
    public void testCorrectGuess() {
        hangman.playGame('e');
        assertArrayEquals(new char[]{'e', '_', '_', '_', '_', '_', 'e'}, hangman.guessedWord);
        assertEquals(5, hangman.remainingAttempts);
    }

}