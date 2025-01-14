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
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy level 4-5 letters word and 10 attempts\n2. Medium level 8-9 letters word and 8 attempts\n3. Hard level 12 letters word or more and 6 attempts\n(Press 'h' during the game for a hint.)");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();

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

        System.out.println(player1Name + ", choose how to set the word:");
        System.out.println("1. Use predefined words\n2. Enter a custom word for " + player2Name);
        System.out.print("Enter your choice (1/2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String wordForPlayer2;
        String difficultyLevel;
        if (choice == 1) {
            wordForPlayer2 = choosePredefinedWord(scanner);
            difficultyLevel = getDifficultyLevel(scanner);
        } else {
            System.out.print(player1Name + ", enter a word for " + player2Name + ": ");
            wordForPlayer2 = scanner.nextLine();
            difficultyLevel = getDifficultyLevel(scanner);
        }

        Hangman gameForPlayer2 = new Hangman(wordForPlayer2, difficultyLevel, 10, player2Name, "Multiplayer");
        System.out.println(player2Name + ", it is your turn to guess!");
        gameForPlayer2.playGame();

        System.out.println(player2Name + ", now set the word for " + player1Name + ".");
        System.out.println("1. Use predefined words\n2. Enter a custom word for " + player1Name);
        System.out.print("Enter your choice (1/2): ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String wordForPlayer1;
        if (choice == 1) {
            wordForPlayer1 = choosePredefinedWord(scanner);
            difficultyLevel = getDifficultyLevel(scanner);
        } else {
            System.out.print(player2Name + ", enter a word for " + player1Name + ": ");
            wordForPlayer1 = scanner.nextLine();
            difficultyLevel = getDifficultyLevel(scanner);
        }

        Hangman gameForPlayer1 = new Hangman(wordForPlayer1, difficultyLevel, 10, player1Name, "Multiplayer");
        System.out.println(player1Name + ", it is your turn to guess!");
        gameForPlayer1.playGame();
    }

    private static String choosePredefinedWord(Scanner scanner) {
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy\n2. Medium\n3. Hard");
        System.out.print("Enter your choice (1/2/3): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[] selectedWords;
        if (difficulty == 1) {
            selectedWords = EASY_WORDS;
        } else if (difficulty == 2) {
            selectedWords = MEDIUM_WORDS;
        } else {
            selectedWords = HARD_WORDS;
        }

        Random random = new Random();
        return selectedWords[random.nextInt(selectedWords.length)];
    }

    private static String getDifficultyLevel(Scanner scanner) {
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy\n2. Medium\n3. Hard");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            return "easy";
        } else if (choice == 2) {
            return "medium";
        } else {
            return "hard";
        }
    }
}