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
    @Test
    public void testIncorrectGuess() {
        hangman.playGame('z');
        assertArrayEquals(new char[]{'_', '_', '_', '_', '_', '_', '_'}, hangman.guessedWord);
        assertEquals(4, hangman.remainingAttempts);
    }

    @Test
    public void testMultipleCorrectGuesses() {
        hangman.playGame('e');
        hangman.playGame('x');
        assertArrayEquals(new char[]{'e', 'x', '_', '_', '_', '_', 'e'}, hangman.guessedWord);
        assertEquals(5, hangman.remainingAttempts);
    }

    @Test
    public void testMultipleIncorrectGuesses() {
        hangman.playGame('z');
        hangman.playGame('y');
        assertArrayEquals(new char[]{'_', '_', '_', '_', '_', '_', '_'}, hangman.guessedWord);
        assertEquals(3, hangman.remainingAttempts);
    }

    @Test
    public void testRevealHint() {
        hangman.revealHint();
        assertNotEquals('_', hangman.guessedWord[0]);
        assertEquals(1, hangman.hintsUsed);
    }

    @Test
    public void testMaxHints() {
        hangman.revealHint();
        hangman.revealHint();
        hangman.revealHint();
        hangman.revealHint(); // This should not reveal any more hints
        assertEquals(3, hangman.hintsUsed);
    }

    @Test
    public void testGameOver() {
        hangman.playGame('z');
        hangman.playGame('y');
        hangman.playGame('w');
        hangman.playGame('v');
        hangman.playGame('u');
        assertEquals(0, hangman.remainingAttempts);
    }
}