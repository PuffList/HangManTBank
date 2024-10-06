package backend.academy.visualization;

public class StringHangmanVisualizer implements IHangmanVisualizer<String> {

    private final static int VISUALISATION_COUNT = 7;
    @Override
    public String getHangmanVisualization(int stage) {
        stage = VISUALISATION_COUNT - stage - 1;

        if (stage < 0) {
            return "";
        }

        return switch (stage) {
            case 0 -> """
            +---+
            |   |
                |
                |
                |
                |
            =========""";
            case 1 -> """
            +---+
            |   |
            O   |
                |
                |
                |
            =========""";
            case 2 -> """
            +---+
            |   |
            O   |
            |   |
                |
                |
            =========""";
            case 3 -> """
            +---+
            |   |
            O   |
           /|   |
                |
                |
            =========""";
            case 4 -> """
            +---+
            |   |
            O   |
           /|\\  |
                |
                |
            =========""";
            case 5 -> """
            +---+
            |   |
            O   |
           /|\\  |
           /    |
                |
            =========""";
            case 6 -> """
            +---+
            |   |
            O   |
           /|\\  |
           / \\  |
                |
            =========""";
            default -> "Something went wrong with hangman visualization";
        };
    }
}
