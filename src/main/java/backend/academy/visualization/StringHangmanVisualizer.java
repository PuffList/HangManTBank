package backend.academy.visualization;

public class StringHangmanVisualizer implements IHangmanVisualizer<String> {

    private final static int VISUALISATION_COUNT = 7;

    @Override
    public String getHangmanVisualization(int stage) {
        int adjustedStage = VISUALISATION_COUNT - stage - 1;
        StagesVisualizer stagesVisualizer;

        if (adjustedStage < 0) {
            return "";
        }
        else {
            stagesVisualizer = StagesVisualizer.values()[adjustedStage];
        }

        return switch (stagesVisualizer) {
            case Stage_0 -> """
            +---+
            |   |
                |
                |
                |
                |
            =========""";
            case Stage_1 -> """
            +---+
            |   |
            O   |
                |
                |
                |
            =========""";
            case Stage_2 -> """
            +---+
            |   |
            O   |
            |   |
                |
                |
            =========""";
            case Stage_3 -> """
            +---+
            |   |
            O   |
           /|   |
                |
                |
            =========""";
            case Stage_4 -> """
            +---+
            |   |
            O   |
           /|\\ |
                |
                |
            =========""";
            case Stage_5 -> """
            +---+
            |   |
            O   |
           /|\\ |
           /    |
                |
            =========""";
            case Stage_6 -> """
            +---+
            |   |
            O   |
           /|\\ |
           / \\ |
                |
            =========""";
            default -> "Something went wrong with hangman visualization";
        };
    }
}
