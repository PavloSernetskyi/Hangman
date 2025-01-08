import java.util.*;

class Hangman {
    private String wordToGuess;
    private char[] guessedWord;
    private int remainingAttempts;

    public Hangman(String wordToGuess, int maxAttempts) {
        this.wordToGuess = wordToGuess.toLowerCase();
        this.remainingAttempts = maxAttempts;
        this.guessedWord = new char[wordToGuess.length()];
        Arrays.fill(this.guessedWord, '_');
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (remainingAttempts > 0) {
            System.out.println("\nWord to guess: " + String.valueOf(guessedWord));
            System.out.println("Remaining attempts: " + remainingAttempts);
            System.out.print("Enter a letter: ");
            char guessedChar = scanner.nextLine().toLowerCase().charAt(0);

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

    public static void main(String[] args) {
        String[] words = {"example", "java", "program", "object", "inheritance", "polymorphism"};
        Random random = new Random();
        String randomWord = words[random.nextInt(words.length)];

        Hangman game = new Hangman(randomWord, 6);
        game.playGame();
    }
}