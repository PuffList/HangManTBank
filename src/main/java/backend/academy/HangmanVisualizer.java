package backend.academy;

public class HangmanVisualizer {
    private final String[] hangmanStages = {
        """
        -----
        |   |
        |
        |
        |
        |
        """,
        """
        -----
        |   |
        |   O
        |
        |
        |
        """,
        """
        -----
        |   |
        |   O
        |   |
        |
        |
        """,
        """
        -----
        |   |
        |   O
        |  /|
        |
        |
        """,
        """
        -----
        |   |
        |   O
        |  /|\\
        |
        |
        """,
        """
        -----
        |   |
        |   O
        |  /|\\
        |  /
        |
        """,
        """
        -----
        |   |
        |   O
        |  /|\\
        |  / \\
        |
        """,
    };

    public void displayHangman(int remainingAttempts) {
        int stageIndex = hangmanStages.length - remainingAttempts - 1;
        if (stageIndex >= 0) {
            System.out.println(hangmanStages[stageIndex]);
        }
    }
}
