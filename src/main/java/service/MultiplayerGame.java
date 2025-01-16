package service;

import model.Player;
import model.Hangman;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplayerGame extends Game {
    private static final String[] EASY_WORDS = {"tree", "book", "lamp", "chair", "clock"};
    private static final String[] MEDIUM_WORDS = {"notebook", "elephant", "chocolate", "backpack", "umbrella"};
    private static final String[] HARD_WORDS = {"constitution", "headquarters", "transportation", "misunderstanding", "revolutionary"};

    public MultiplayerGame(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player 1 name: ");
        player1 = new Player(scanner.nextLine());
        System.out.print("Enter Player 2 name: ");
        player2 = new Player(scanner.nextLine());

        String wordForPlayer2 = getWordForPlayer(scanner, player1, player2);
        Hangman gameForPlayer2 = new Hangman(wordForPlayer2, difficultyLevel, 10, player2.getName(), "Multiplayer");
        System.out.println(player2.getName() + ", it is your turn to guess!");
        gameForPlayer2.playGame();

        String wordForPlayer1 = getWordForPlayer(scanner, player2, player1);
        Hangman gameForPlayer1 = new Hangman(wordForPlayer1, difficultyLevel, 10, player1.getName(), "Multiplayer");
        System.out.println(player1.getName() + ", it is your turn to guess!");
        gameForPlayer1.playGame();
    }

    private String getWordForPlayer(Scanner scanner, Player setter, Player guesser) {
        int choice;
        while (true) {
            System.out.println(setter.getName() + ", choose how to set the word:");
            System.out.println("1. Use predefined words with 3 difficulty levels.\n2. Enter a custom word for " + guesser.getName());
            System.out.print("Enter your choice (1/2): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next(); // clear the invalid input
            }
        }

        if (choice == 1) {
            difficultyLevel = getDifficultyLevel(scanner);
            switch (difficultyLevel) {
                case "easy":
                    words = EASY_WORDS;
                    break;
                case "medium":
                    words = MEDIUM_WORDS;
                    break;
                case "hard":
                    words = HARD_WORDS;
                    break;
            }
            return chooseRandomWord(words);
        } else {
            System.out.print(setter.getName() + ", enter a word for " + guesser.getName() + ": ");
            return scanner.nextLine();
        }
    }
}