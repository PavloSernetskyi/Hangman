package controller;

import service.GameSetup;

import java.util.*;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.println("1. Single Player\n2. Multiplayer");
        System.out.print("Enter your choice (1/2): ");

        // initialize mode to 0. This will be used to store the user's choice 1 as single player mode and 2 as multiplayer mode.
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
            GameSetup.startSinglePlayerMode();
        } else if (mode == 2) {
            GameSetup.startMultiplayerMode();
        }
    }
}
