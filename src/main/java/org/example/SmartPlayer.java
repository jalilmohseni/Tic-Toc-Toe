package org.example;
import org.example.BlockingPlayer;
/**
 * The SmartPlayer class attempts to win first, then blocks, then makes a random move.
 */
public class SmartPlayer extends BlockingPlayer {
    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    public void makeMove() {
        int row = -1, col = -1;

        // First, check if a winning move exists
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Constants.SPACE_CHAR) {
                    if (testForWinning(i, j)) { // ✅ Using (row, col) as per class diagram
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            if (row != -1) break;
        }

        // If no winning move, check for a blocking move
        if (row == -1) {
            row = -1;
            col = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getMark(i, j) == Constants.SPACE_CHAR) {
                        if (testForBlocking(i, j)) {
                            row = i;
                            col = j;
                            break;
                        }
                    }
                }
                if (row != -1) break;
            }
        }

        // If no winning or blocking move, play randomly
        if (row == -1) {
            do {
                row = randomGen.discrete(0, 2);
                col = randomGen.discrete(0, 2);
            } while (board.getMark(row, col) != Constants.SPACE_CHAR);
        }

        board.addMark(row, col, mark);
        System.out.println(name + " (SmartPlayer) placed " + mark + " at (" + row + ", " + col + ")");
    }

    /**
     * Checks if placing a mark at (row, col) would win the game.
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return true if the move is a winning move, false otherwise.
     */
    private boolean testForWinning(int row, int col) {
        // Temporarily place own mark and check if this would win the game
        board.addMark(row, col, mark);
        boolean wouldWin = board.xWins() || board.oWins();
        board.addMark(row, col, Constants.SPACE_CHAR); // Reset board position

        return wouldWin;
    }
}
