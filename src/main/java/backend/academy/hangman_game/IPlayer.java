package backend.academy.hangman_game;

public interface IPlayer {

    String getLetter();

    void display(String message);

    String getMyGuessMessage(LetterState letterState);
}
