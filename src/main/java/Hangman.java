import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    private String wordToGuess;
    private char[] guessedWord;
    private int remainingAttempts;
    private int hintsUsed;
    private static final int MAX_HINTS = 3;

    public Hangman(String wordToGuess, int maxAttempts) {
        this.wordToGuess = wordToGuess.toLowerCase();
        this.remainingAttempts = maxAttempts;
        this.guessedWord = new char[wordToGuess.length()];
        this.hintsUsed = 0;
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
            char guessedChar = scanner.nextLine().toLowerCase().charAt(0);

            if (guessedChar == 'h') {
                if (hintsUsed < MAX_HINTS) {
                    revealHint();
                    hintsUsed++;
                } else {
                    System.out.println("No more hints available.");
                }
                continue;
            }

            if (wordToGuess.contains(String.valueOf(guessedChar))) {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guessedChar) {
                        guessedWord[i] = guessedChar;
                    }
                }
            } else {
                remainingAttempts--;
            }

            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
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
