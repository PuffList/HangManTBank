package backend.academy;

import java.io.PrintStream;
import java.util.Scanner;

public class Player {
    private Scanner scanner;
    private static final PrintStream OUT = System.out;

    public Player() {
        scanner = new Scanner(System.in);
    }

    public String getLetter() {
        OUT.print("Enter a letter: ");
        return scanner.nextLine().toLowerCase();
    }

    public void display(String message) {
        System.out.println(message);
    }

}
