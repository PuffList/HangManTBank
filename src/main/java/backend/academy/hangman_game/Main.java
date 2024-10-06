package backend.academy.hangman_game;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        HangMan hangMan = new HangMan();
        hangMan.run();
    }
}
