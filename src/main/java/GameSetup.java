import java.util.Random;
import java.util.Scanner;

public class GameSetup {
    private static final String[] EASY_WORDS = {"tree", "book", "lamp", "chair", "clock"};
    private static final String[] MEDIUM_WORDS = {"notebook", "elephant", "chocolate", "backpack", "umbrella"};
    private static final String[] HARD_WORDS = {"constitution", "headquarters", "transportation", "misunderstanding", "revolutionary"};

    public static void startSinglePlayerMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy level 4-5 letters word and 10 attempts\n2. Medium level 8-9 letters word and 8 attempts\n3. Hard level 12 letters word or more and 6 attempts\n(Press 'h' during the game for a hint.)");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();

        String[] selectedWords;
        int attempts;
        if (choice == 1) {
            selectedWords = EASY_WORDS;
            attempts = 10;
        } else if (choice == 2) {
            selectedWords = MEDIUM_WORDS;
            attempts = 8;
        } else {
            selectedWords = HARD_WORDS;
            attempts = 6;
        }

        Random random = new Random();
        String randomWord = selectedWords[random.nextInt(selectedWords.length)];

        Hangman game = new Hangman(randomWord, attempts);
        game.playGame();
    }

    public static void startMultiplayerMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player 1 name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Enter Player 2 name: ");
        Player player2 = new Player(scanner.nextLine());

        System.out.println(player1.getName() + ", choose how to set the word:");
        System.out.println("1. Use predefined words\n2. Enter a custom word for " + player2.getName());
        System.out.print("Enter your choice (1/2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String wordForPlayer2;
        if (choice == 1) {
            wordForPlayer2 = choosePredefinedWord(scanner);
        } else {
            System.out.print(player1.getName() + ", enter a word for " + player2.getName() + ": ");
            wordForPlayer2 = scanner.nextLine();
        }

        Hangman gameForPlayer2 = new Hangman(wordForPlayer2, 10);
        System.out.println(player2.getName() + ", it is your turn to guess!");
        gameForPlayer2.playGame();

        System.out.println(player2.getName() + ", now set the word for " + player1.getName() + ".");
        System.out.println("1. Use predefined words\n2. Enter a custom word for " + player1.getName());
        System.out.print("Enter your choice (1/2): ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String wordForPlayer1;
        if (choice == 1) {
            wordForPlayer1 = choosePredefinedWord(scanner);
        } else {
            System.out.print(player2.getName() + ", enter a word for " + player1.getName() + ": ");
            wordForPlayer1 = scanner.nextLine();
        }

        Hangman gameForPlayer1 = new Hangman(wordForPlayer1, 10);
        System.out.println(player1.getName() + ", it is your turn to guess!");
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
}
