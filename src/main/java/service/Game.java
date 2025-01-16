package service;

import model.Player;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public abstract class Game {
    protected Player player1;
    protected Player player2;
    protected String difficultyLevel;
    protected String[] words;
    protected int attempts;

    public Game(Player player1) {
        this.player1 = player1;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    protected String chooseRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    protected String getDifficultyLevel(Scanner scanner) {
        while (true) {
            System.out.println("Select difficulty level: ");
            System.out.println("1. Easy\n2. Medium\n3. Hard");
            System.out.print("Enter your choice (1/2/3): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        return "easy";
                    case 2:
                        return "medium";
                    case 3:
                        return "hard";
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); // clear the invalid input
            }
        }
    }

    public abstract void startGame();
}