package model;

import repository.DatabaseManager;

import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    private String wordToGuess;
    private char[] guessedWord;
    private int remainingAttempts;
    private int hintsUsed;
    private String difficultyLevel;
    private String playerName;
    private String gameMode;
    private static final int MAX_HINTS = 3;

    public Hangman(String wordToGuess, String difficultyLevel, int remainingAttempts, String playerName, String gameMode) {
        this.wordToGuess = wordToGuess.toLowerCase();
        this.difficultyLevel = difficultyLevel;
        this.remainingAttempts = remainingAttempts;
        this.hintsUsed = 0;
        this.playerName = playerName;
        this.gameMode = gameMode;
        this.guessedWord = new char[wordToGuess.length()];
        Arrays.fill(this.guessedWord, '_');
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (remainingAttempts > 0) {
            System.out.println("\nWord to guess: " + String.valueOf(guessedWord));
            System.out.println("Remaining attempts: " + remainingAttempts);
            System.out.println("Hints used: " + hintsUsed + "/" + MAX_HINTS);
            System.out.println("Remaining hints: " + (MAX_HINTS - hintsUsed));
            System.out.println("Press 'h' for a hint to reveal a letter.");
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("No input provided. Please enter a letter.");
                continue;
            }

            if (input.equals("hint")) {
                if (hintsUsed < MAX_HINTS) {
                    revealHint();
                    hintsUsed++;
                } else {
                    System.out.println("No more hints available.");
                }
                continue;
            }
            char guessedChar = input.charAt(0);
            boolean correctGuess = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedChar) {
                    guessedWord[i] = guessedChar;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                remainingAttempts--;
            }

            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
                DatabaseManager.saveGame(playerName, gameMode, remainingAttempts, hintsUsed, difficultyLevel);
                return;
            }
        }

        System.out.println("\nGame Over. The word was: " + wordToGuess);
    }

    private void revealHint() {
        for (int i = 0; i < guessedWord.length; i++) {
            if (guessedWord[i] == '_') {
                guessedWord[i] = wordToGuess.charAt(i);
                System.out.println("Hint revealed: " + String.valueOf(guessedWord));
                return;
            }
        }
        System.out.println("No more hints available.");
    }
}