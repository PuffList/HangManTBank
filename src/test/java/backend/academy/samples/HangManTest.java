package backend.academy.samples;

import backend.academy.hangman_game.Game;
import backend.academy.hangman_game.LetterState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HangManTest {
    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game("apple", 7);  //слово для теста - "apple"
    }

    // Тест на правильный выбор слова из списка (длина слова соответствует сложности)
    @Test
    public void testWordSelection() {
        assertEquals("apple".length(), game.getCurrentWordState().length());
    }

    // Тест на корректность отображения состояния игры после каждого ввода
    @Test
    public void testGameStateAfterEachGuess() {
        game.makeGuess("a");
        assertEquals("a____", game.getCurrentWordState());

        game.makeGuess("p");
        assertEquals("app__", game.getCurrentWordState());

        game.makeGuess("z");
        assertEquals("app__", game.getCurrentWordState());
    }

    // Тест на обработку ввода вне зависимости от регистра
    @Test
    public void testCaseInsensitiveInput() {
        game.makeGuess("A");  // Заглавная буква "A"
        assertEquals("a____", game.getCurrentWordState());

        game.makeGuess("P");  // Заглавная буква "P"
        assertEquals("app__", game.getCurrentWordState());
    }

    // Тест, что игра не запускается, если слово имеет некорректную длину
    @Test
    public void testInvalidWordLength() {
        assertThrows(IllegalArgumentException.class, () -> new Game("ab", 6),
            "Expected to throw exception for word length less than " + Game.MIN_WORD_LENGTH());
    }

    // Тест, что игра завершится поражением после превышения количества попыток
    @Test
    public void testGameEndsAfterMaxAttempts() {
        game.makeGuess("z");
        game.makeGuess("y");
        game.makeGuess("x");
        game.makeGuess("w");
        game.makeGuess("v");
        game.makeGuess("u");
        game.makeGuess("t");

        assertTrue(game.isGameOver());
        assertFalse(game.isWordGuessed());
    }

    // Тест, что состояние игры корректно изменяется при угадывании
    @Test
    public void testGameStateChangesCorrectlyOnGuess() {
        game.makeGuess("a");
        assertEquals("a____", game.getCurrentWordState());

        game.makeGuess("b");
        assertEquals("a____", game.getCurrentWordState());
    }

    // Тест, что ввод строки длиной больше или меньше чем 1 символ приводит к повторному запросу ввода
    @Test
    public void testInvalidInputLength() {
        assertThrows(IllegalArgumentException.class, () -> game.makeGuess("ab"),
            "Expected to throw exception for invalid input length");

        // Проверка ввода пустой строки
        assertThrows(IllegalArgumentException.class, () -> game.makeGuess(""),
            "Expected to throw exception for empty input");
    }
}
