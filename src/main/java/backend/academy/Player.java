package backend.academy;

import java.util.Scanner;

public class Player {
    private Scanner scanner;

    public Player() {
        scanner = new Scanner(System.in);
    }

    public String getLetter() {
        System.out.print("Enter a letter: ");
        return scanner.nextLine().toLowerCase();
    }

    public void display(String message) {
        System.out.println(message);
    }

}
