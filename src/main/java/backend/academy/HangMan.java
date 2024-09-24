package backend.academy;

import lombok.experimental.UtilityClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class HangMan {

    private String chosenCategory = " ";
    private String chosenWord = " ";
    private static final int attempts = 7;
    private static final List<String> categories = new ArrayList<>();

    public static void main(String[] args) {
        HangMan player = new HangMan();
        player.run();
    }

    private void run() {
        findCategory();
        chooseDifficulty();

        Player player = new Player();
        Game game = new Game(chosenWord, attempts);
        GameLoop gameLoop = new GameLoop(player, game);

        gameLoop.start();
    }

    private void findCategory() {
        Scanner scanner = new Scanner(System.in);
        boolean categoryFound = false;

        System.out.println("Please enter one of proposed categories: animals, countries, fruits, sports, vegetables");
        System.out.print("Your choice: ");
        String category = scanner.nextLine();

        loadingCategories();
        while (!categoryFound) {
            for (int i = 0; i < categories.size(); i++) {
                if (category.equalsIgnoreCase(categories.get(i))) {
                    categoryFound = true;
                    chosenCategory = category;
                    System.out.println("Category found: " + category);
                }
            }

            if (!categoryFound) {
                System.out.println("Please enter a valid category(animals, countries, fruits, sports, vegetables): ");
                category = scanner.nextLine();
            }
        }
    }

    private void loadingCategories() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/words.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (splitLine.length > 0) {
                    categories.add(splitLine[0]);
                }
            }
            reader.close();

        }
        catch (IOException e){
            System.out.print("Error while reading a file: " + e.getMessage());
        }
    }

    private void chooseDifficulty() {
        boolean correct_difficult = false;
        while(!correct_difficult) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose difficulty level: easy, medium, hard");
            System.out.print("Your choice: ");
            String difficulty = scanner.nextLine().toLowerCase();
            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                correct_difficult = true;
                loadingAndFindWord(difficulty);
            }
            else {
                System.out.println("Please enter a valid difficulty");
            }
        }
    }

    private void loadingAndFindWord(String difficult) {

        List<String> words = new ArrayList<>();
        List<String> filteredWords = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/words.txt"));
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
            //System.out.println("Selected word: " + chosenWord); // строка для проверки, потом убрать

        }
        catch (IOException e){
            System.out.print("Error while reading a file: " + e.getMessage());
        }
    }
}
