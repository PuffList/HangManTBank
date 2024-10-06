package backend.academy.hangman_game;

import backend.academy.visualization.StringHangmanVisualizer;

public class GameLoop {

    private ConsolePlayer player;
    private Game game;
    private StringHangmanVisualizer visualizer;

    public GameLoop(ConsolePlayer player, Game game) {
        this.player = player;
        this.game = game;
        visualizer = new StringHangmanVisualizer();
    }

    public void start() {
        player.display("Welcome to Hangman!");

        while (!game.isGameOver()) {
            player.display("Current word: " + game.getCurrentWordState());
            player.display("Attempts left: " + game.remainingAttempts());
            player.display(visualizer.getHangmanVisualization(game.remainingAttempts()));

            String guess = player.getLetter();
            String result = "";
            try {
                LetterState letterState = game.makeGuess(guess);
                result = player.getMyGuessMessage(letterState);
            } catch (IllegalArgumentException e) {
                result = e.getMessage();
            }

            player.display(result);
        }

        if (game.remainingAttempts() == 0) {
            player.display(visualizer.getHangmanVisualization(0));
        }

        if (game.isWordGuessed()) {
            player.display("Congratulations! You've guessed the word: " + game.getCurrentWordState());
        } else {
            player.display("Game over! The word was: " + game.wordToGuess());
        }
    }
}
