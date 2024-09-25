package backend.academy;

import java.io.PrintStream;

public class HangmanVisualizer {
    private static final PrintStream OUT = System.out;
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
            OUT.println(hangmanStages[stageIndex]);
        }
    }
}
