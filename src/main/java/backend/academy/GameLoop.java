package backend.academy;

public class GameLoop {
    private Player player;
    private Game game;
    private HangmanVisualizer visualizer;

    public GameLoop(Player player, Game game) {
        this.player = player;
        this.game = game;
        visualizer = new HangmanVisualizer();
    }

    public void start() {
        player.display("Welcome to Hangman!");

        while(!game.isGameOver()) {
            player.display("Current word: " + game.getCurrentWordState());
            player.display("Attempts left: " + game.getRemainingAttempts());
            visualizer.displayHangman(game.getRemainingAttempts());

            String guess = player.getLetter();
            String result = game.checkBeforeMakeGuess(guess);
            player.display(result);
        }

        if (game.isWordGuessed()) {
            player.display("Congratulations! You've guessed the word: " + game.getCurrentWordState());
        } else {
            player.display("Game over! The word was: " + game.getWordToGuess());
        }
    }
}
