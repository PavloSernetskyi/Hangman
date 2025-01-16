package service;
import model.Hangman;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameSetup {
    private static final String[] EASY_WORDS = {"tree", "book", "lamp", "chair", "clock"};
    private static final String[] MEDIUM_WORDS = {"notebook", "elephant", "chocolate", "backpack", "umbrella"};
    private static final String[] HARD_WORDS = {"constitution", "headquarters", "transportation", "misunderstanding", "revolutionary"};

    public static void startSinglePlayerMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        int choice = 0;
        while (true) {
            System.out.println("Select difficulty level: ");
            System.out.println("1. Easy level 4-5 letters word and 10 attempts\n2. Medium level 8-9 letters word and 8 attempts\n3. Hard level 12 letters word or more and 6 attempts\n(Press 'hint' during the game for a hint.)");
            System.out.print("Enter your choice (1/2/3): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                scanner.next(); // clear the invalid input
            }
        }

        String[] selectedWords;
        String difficultyLevel;
        int attempts;
        if (choice == 1) {
            selectedWords = EASY_WORDS;
            difficultyLevel = "easy";
            attempts = 10;
        } else if (choice == 2) {
            selectedWords = MEDIUM_WORDS;
            difficultyLevel = "medium";
            attempts = 8;
        } else {
            selectedWords = HARD_WORDS;
            difficultyLevel = "hard";
            attempts = 6;
        }

        Random random = new Random();
        String randomWord = selectedWords[random.nextInt(selectedWords.length)];

        Hangman game = new Hangman(randomWord, difficultyLevel, attempts, playerName, "Single Player");
        game.playGame();
    }


    public static void startMultiplayerMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        int choice = 0;
        while (true) {
            System.out.println(player1Name + ", choose how to set the word:");
            System.out.println("1. Use predefined words with 3 difficulty levels.\n2. Enter a custom word for " + player2Name);
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

        String wordForPlayer2;
        String difficultyLevel = null;
        if (choice == 1) {
            difficultyLevel = getDifficultyLevel(scanner);
            wordForPlayer2 = choosePredefinedWord(scanner, difficultyLevel);
        } else {
            System.out.print(player1Name + ", enter a word for " + player2Name + ": ");
            wordForPlayer2 = scanner.nextLine();
        }

        Hangman gameForPlayer2 = new Hangman(wordForPlayer2, difficultyLevel, 10, player2Name, "Multiplayer");
        System.out.println(player2Name + ", it is your turn to guess!");
        gameForPlayer2.playGame();

        while (true) {
            System.out.println(player2Name + ", now set the word for " + player1Name + ".");
            System.out.println("1. Use predefined words with 3 difficulty levels.\n2. Enter a custom word for " + player1Name);
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

        String wordForPlayer1;
        if (choice == 1) {
            difficultyLevel = getDifficultyLevel(scanner);
            wordForPlayer1 = choosePredefinedWord(scanner, difficultyLevel);
        } else {
            System.out.print(player2Name + ", enter a word for " + player1Name + ": ");
            wordForPlayer1 = scanner.nextLine();
        }

        Hangman gameForPlayer1 = new Hangman(wordForPlayer1, difficultyLevel, 10, player1Name, "Multiplayer");
        System.out.println(player1Name + ", it is your turn to guess!");
        gameForPlayer1.playGame();
    }

    private static String choosePredefinedWord(Scanner scanner, String difficultyLevel) {
        String[] selectedWords;
        switch (difficultyLevel) {
            case "easy":
                selectedWords = EASY_WORDS;
                break;
            case "medium":
                selectedWords = MEDIUM_WORDS;
                break;
            case "hard":
                selectedWords = HARD_WORDS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + difficultyLevel);
        }

        Random random = new Random();
        return selectedWords[random.nextInt(selectedWords.length)];
    }

    private static String getDifficultyLevel(Scanner scanner) {
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
}