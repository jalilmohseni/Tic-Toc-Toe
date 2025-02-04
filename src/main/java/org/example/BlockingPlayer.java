package org.example;

/**
 * The BlockingPlayer class blocks the opponent winning move if possible.
 * Otherwise, it makes a random move like RandomPlayer.
 */
public class BlockingPlayer extends RandomPlayer {
    public BlockingPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    public void makeMove() {
        int row = -1, col = -1;

        // Check for a blocking move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Constants.SPACE_CHAR) {
                    if (testForBlocking(i, j)) { // ✅ Using (row, col) as per class diagram
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            if (row != -1) break;
        }

        // If no blocking move is found, play randomly
        if (row == -1) {
            do {
                row = randomGen.discrete(0, 2);
                col = randomGen.discrete(0, 2);
            } while (board.getMark(row, col) != Constants.SPACE_CHAR);
        }

        board.addMark(row, col, mark);
        System.out.println(name + " (BlockingPlayer) placed " + mark + " at (" + row + ", " + col + ")");
    }

    /**
     * Checks if placing a mark at (row, col) would block the opponents winning move.
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return true if blocking is needed, false otherwise.
     */
    protected boolean testForBlocking(int row, int col) {
        char opponentMark = (mark == Constants.LETTER_X) ? Constants.LETTER_O : Constants.LETTER_X;

        // Temporarily place opponent's mark and check if they would win
        board.addMark(row, col, opponentMark);
        boolean opponentWouldWin = board.xWins() || board.oWins();
        board.addMark(row, col, Constants.SPACE_CHAR); // Reset board position

        return opponentWouldWin;
    }
}
