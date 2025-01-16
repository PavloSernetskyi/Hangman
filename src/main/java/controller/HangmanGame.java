package controller;

import model.Player;
import service.SinglePlayerGame;
import service.MultiplayerGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.println("1. Single Player\n2. Multiplayer");
        System.out.print("Enter your choice (1/2): ");

        int mode = 0;
        while (true) {
            System.out.print("Enter your choice (1/2): ");
            try {
                mode = scanner.nextInt();
                if (mode == 1 || mode == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }

        if (mode == 1) {
            SinglePlayerGame singlePlayerGame = new SinglePlayerGame(new Player(""));
            singlePlayerGame.startGame();
        } else if (mode == 2) {
            MultiplayerGame multiplayerGame = new MultiplayerGame(new Player(""), new Player(""));
            multiplayerGame.startGame();
        }
    }
}