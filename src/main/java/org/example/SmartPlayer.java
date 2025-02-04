package org.example;

/**
 * The SmartPlayer class attempts to win first, then blocks, then makes a random move.
 */
public class SmartPlayer extends BlockingPlayer {

    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    public void makeMove() {
        // ✅ Check if the board is already full before making a move
        if (board.isFull()) {
            System.out.println(name + " (SmartPlayer) has no moves left. The game is a tie!");
            return; // ✅ Exit immediately without attempting a move
        }

        int row = -1, col = -1;

        // ✅ First, check if a winning move exists
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Constants.SPACE_CHAR) {
                    if (testForWinning(i, j)) { // ✅ Check if this move wins the game
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            if (row != -1) break;
        }

        // ✅ If no winning move, check for a blocking move
        if (row == -1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getMark(i, j) == Constants.SPACE_CHAR) {
                        if (testForBlocking(i, j)) { // ✅ Check if this move blocks opponent
                            row = i;
                            col = j;
                            break;
                        }
                    }
                }
                if (row != -1) break;
            }
        }

        // ✅ If no winning or blocking move, play randomly
        if (row == -1) {
            if (board.isFull()) { // ✅ Double-check if board is full before attempting a random move
                System.out.println(name + " (SmartPlayer) has no valid moves left. The game is a tie!");
                return;
            }

            do {
                row = randomGen.discrete(0, 2);
                col = randomGen.discrete(0, 2);
            } while (board.getMark(row, col) != Constants.SPACE_CHAR);
        }

        // ✅ Place the mark on the board
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
