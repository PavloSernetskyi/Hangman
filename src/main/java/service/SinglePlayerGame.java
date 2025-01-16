package service;

import model.Player;
import model.Hangman;

import java.util.Scanner;

public class SinglePlayerGame extends Game {
    private static final String[] EASY_WORDS = {"tree", "book", "lamp", "chair", "clock"};
    private static final String[] MEDIUM_WORDS = {"notebook", "elephant", "chocolate", "backpack", "umbrella"};
    private static final String[] HARD_WORDS = {"constitution", "headquarters", "transportation", "misunderstanding", "revolutionary"};

    public SinglePlayerGame(Player player1) {
        super(player1);
    }

    @Override
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player1 = new Player(scanner.nextLine());

        difficultyLevel = getDifficultyLevel(scanner);
        switch (difficultyLevel) {
            case "easy":
                words = EASY_WORDS;
                attempts = 10;
                break;
            case "medium":
                words = MEDIUM_WORDS;
                attempts = 8;
                break;
            case "hard":
                words = HARD_WORDS;
                attempts = 6;
                break;
        }

        String randomWord = chooseRandomWord(words);
        Hangman game = new Hangman(randomWord, difficultyLevel, attempts, player1.getName(), "Single Player");
        game.playGame();
    }
}