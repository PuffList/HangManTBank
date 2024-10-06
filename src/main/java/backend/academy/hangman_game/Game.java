package backend.academy.hangman_game;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

public class Game {

    @Getter
    private static final int MIN_WORD_LENGTH = 3;
    @Getter
    private String wordToGuess;
    private Set<Character> guessedLetters = new HashSet<>();
    @Getter
    private int remainingAttempts;
    private StringBuilder currentWordState;

    public Game(String word, int maxAttempts) {
        if (word.length() < MIN_WORD_LENGTH) {
            throw new IllegalArgumentException("Wrong word length. Minimum word length is " + MIN_WORD_LENGTH);
        }

        this.wordToGuess = word.toLowerCase();
        this.remainingAttempts = maxAttempts;
        this.currentWordState = new StringBuilder("_".repeat(word.length()));
    }

    public LetterState makeGuess(String input) {
        if (input.length() != 1) {
            throw new IllegalArgumentException("Wrong enter. Please enter only one character");
        }

        char letter = input.charAt(0);
        char lowerCaseLetter = Character.toLowerCase(letter);

        if (!Character.isLetter(lowerCaseLetter) || lowerCaseLetter < 'a' || lowerCaseLetter > 'z') {
            throw new IllegalArgumentException("Wrong Enter. Please enter a one letter from english alphabet");
        }

        if (guessedLetters.contains(lowerCaseLetter)) {
            return LetterState.AlreadyGuessed;
        }

        guessedLetters.add(lowerCaseLetter);

        if (wordToGuess.contains(String.valueOf(lowerCaseLetter))) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == lowerCaseLetter) {
                    currentWordState.setCharAt(i, lowerCaseLetter);
                }
            }

            return LetterState.Guessed;
        } else {
            remainingAttempts--;
            return LetterState.NotGuessed;
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
}
