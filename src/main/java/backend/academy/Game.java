package backend.academy;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private String wordToGuess;
    private Set<Character> guessedLetters = new HashSet<>();
    private int remainingAttempts;
    private StringBuilder currentWordState;
    private static final int MIN_WORD_LENGTH = 3;

    public Game(String word, int maxAttempts) {

        if (word.length() < MIN_WORD_LENGTH) {
            throw new IllegalArgumentException("Wrong word length");
        }

        this.wordToGuess = word.toLowerCase();
        this.remainingAttempts = maxAttempts;
        this.currentWordState = new StringBuilder("_".repeat(word.length()));
    }

    public String checkBeforeMakeGuess(String input) {

        if (input.length() != 1) {
            return "Wrong enter. Please enter only one character";
        }

        return makeGuess(input.charAt(0));
    }

    public String makeGuess(char letter) {
        char lowerCaseLetter = Character.toLowerCase(letter);

        if (!Character.isLetter(lowerCaseLetter) || lowerCaseLetter < 'a' || lowerCaseLetter > 'z') {
            return "Wrong Enter. Please enter a one letter from english alphabet";
        }

        if (guessedLetters.contains(lowerCaseLetter)) {
            return "This letter is already guessed";
        }

        guessedLetters.add(lowerCaseLetter);

        if (wordToGuess.contains(String.valueOf(lowerCaseLetter))) {

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == lowerCaseLetter) {
                    currentWordState.setCharAt(i, lowerCaseLetter);
                }
            }

            return "Successful attempt!";
        } else {
            remainingAttempts--;
            return "Wrong letter.";
        }
    }

    public boolean isWordGuessed() {
        return currentWordState.indexOf("_") == -1;
    }

    public boolean isGameOver() {
        return remainingAttempts == 0 || isWordGuessed();
    }

    public String getCurrentWordState() {
        return currentWordState.toString();
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

}
