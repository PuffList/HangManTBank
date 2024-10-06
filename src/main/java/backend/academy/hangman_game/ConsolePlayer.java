package backend.academy.hangman_game;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsolePlayer implements IPlayer {

    private static final PrintStream OUT = System.out;
    private Scanner scanner;

    public ConsolePlayer() {
        scanner = new Scanner(System.in);
    }

    public String getLetter() {
        OUT.print("Enter a letter: ");
        return scanner.nextLine().toLowerCase();
    }

    public void display(String message) {
        OUT.println(message);
    }

    public String getMyGuessMessage(LetterState letterState) {
        switch (letterState) {
            case AlreadyGuessed:
                return "This letter is already guessed";
            case Guessed:
                return "Successful attempt!";
            case NotGuessed:
                return "Wrong letter.";
            default:
                return "";
        }
    }
}
