import java.util.*;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.println("1. Single Player\n2. Multiplayer");
        System.out.print("Enter your choice (1/2): ");
        int mode = scanner.nextInt();

        if (mode == 1) {
            GameSetup.startSinglePlayerMode();
        } else {
            GameSetup.startMultiplayerMode();
        }
    }
}
