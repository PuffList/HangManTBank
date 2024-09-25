package backend.academy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;

public class HangMan {

    private String chosenCategory = " ";
    private String chosenWord = " ";
    private static final int ATTEMPTS = 7;
    private static final List<String> CATEGORIES = new ArrayList<>();
    private static final PrintStream OUT = System.out;
    private static final PrintStream ERR = System.err;
    private static final String YOUR_CHOICE_MSG = "Your choice: ";
    private static final String ERROR_READING_FILE_MSG = "Error while reading a file: ";
    private static final String WORDS_FILE_PATH = "src/main/resources/words.txt";

    /**
     * Main method to start the Hangman game.
     */
    public static void main(String[] args) {
        HangMan player = new HangMan();
        player.run();
    }

    private void run() {
        findCategory();
        chooseDifficulty();

        Player player = new Player();
        Game game = new Game(chosenWord, ATTEMPTS);
        GameLoop gameLoop = new GameLoop(player, game);

        gameLoop.start();
    }

    private void findCategory() {
        Scanner scanner = new Scanner(System.in);
        boolean categoryFound = false;

        OUT.println("Please enter one of proposed categories: animals, countries, fruits, sports, vegetables");
        OUT.print(YOUR_CHOICE_MSG);
        String category = scanner.nextLine();

        loadingCategories();
        while (!categoryFound) {
            for (int i = 0; i < CATEGORIES.size(); i++) {
                if (category.equalsIgnoreCase(CATEGORIES.get(i))) {
                    categoryFound = true;
                    chosenCategory = category;
                    OUT.println("Category found: " + category);
                }
            }

            if (!categoryFound) {
                OUT.print("Please enter a valid category (animals, countries, fruits, sports, vegetables): ");
                category = scanner.nextLine();
            }
        }
    }

    private void loadingCategories() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (splitLine.length > 0) {
                    CATEGORIES.add(splitLine[0]);
                }
            }
            reader.close();

        } catch (IOException e) {
            ERR.println(ERROR_READING_FILE_MSG + e.getMessage());
        }
    }

    private void chooseDifficulty() {
        boolean correctDifficulty = false;
        while (!correctDifficulty) {
            Scanner scanner = new Scanner(System.in);
            OUT.println("Choose difficulty level: easy, medium, hard");
            OUT.print(YOUR_CHOICE_MSG);
            String difficulty = scanner.nextLine().toLowerCase();
            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                correctDifficulty = true;
                loadingAndFindWord(difficulty);
            } else {
                OUT.println("Please enter a valid difficulty");
            }
        }
    }

    private void loadingAndFindWord(String difficult) {
        List<String> words = new ArrayList<>();
        List<String> filteredWords = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (splitLine[0].equalsIgnoreCase(chosenCategory)) {
                    for (int i = 1; i < splitLine.length; i++) {
                        words.add(splitLine[i]);
                    }
                }
            }
            reader.close();

            for (String word : words) {
                if (difficult.equals("easy") && word.length() <= 4) {
                    filteredWords.add(word);
                } else if (difficult.equals("medium") && word.length() > 4 && word.length() <= 7) {
                    filteredWords.add(word);
                } else if (difficult.equals("hard") && word.length() > 7) {
                    filteredWords.add(word);
                }
            }

            Random randomWord = new Random();
            chosenWord = filteredWords.get(randomWord.nextInt(filteredWords.size()));
            OUT.println("Selected word: " + chosenWord);  // для отладки, можно убрать

        } catch (IOException e) {
            ERR.println(ERROR_READING_FILE_MSG + e.getMessage());
        }
    }
}

