package org.example;

/**
 * The Board class represents the Tic-Tac-Toe game board.
 * It manages the state of the board, checks for wins, and displays the board.
 */
public class Board implements Constants {
    private char theBoard[][];
    private int markCount;

    /**
     * Constructs a Board object and initializes the board with empty spaces.
     */
    public Board() {
        markCount = 0;
        theBoard = new char[3][];
        for (int i = 0; i < 3; i++) {
            theBoard[i] = new char[3];
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        }
    }

    /**
     * Returns the mark at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @return the mark at the specified position
     */
    public char getMark(int row, int col) {
        return theBoard[row][col];
    }

    /**
     * Checks if the board is full.
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        return markCount == 9;
    }

    /**
     * Checks if the X player has won.
     * @return true if X has won, false otherwise
     */
    public boolean xWins() {
        if (checkWinner(LETTER_X) == 1)
            return true;
        else
            return false;
    }

    /**
     * Checks if the O player has won.
     * @return true if O has won, false otherwise
     */
    public boolean oWins() {
        if (checkWinner(LETTER_O) == 1)
            return true;
        else
            return false;
    }

    /**
     * Displays the current state of the board.
     */
    public void display() {
        displayColumnHeaders();
        addHyphens();
        for (int row = 0; row < 3; row++) {
            addSpaces();
            System.out.print("    row " + row + ' ');
            for (int col = 0; col < 3; col++)
                System.out.print("|  " + getMark(row, col) + "  ");
            System.out.println("|");
            addSpaces();
            addHyphens();
        }
    }

    /**
     * Adds a mark to the specified position on the board.
     * @param row  the row index
     * @param col  the column index
     * @param mark the mark to be added
     */
    public void addMark(int row, int col, char mark) {
        theBoard[row][col] = mark;
        markCount++;
    }

    /**
     * Clears the board by resetting all positions to empty spaces.
     */
    public void clear() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        markCount = 0;
    }

    /**
     * Checks if the specified mark has won the game.
     * @param mark the mark to check for a win
     * @return 1 if the mark has won, 0 otherwise
     */
    int checkWinner(char mark) {
        int row, col;
        int result = 0;

        for (row = 0; result == 0 && row < 3; row++) {
            int row_result = 1;
            for (col = 0; row_result == 1 && col < 3; col++)
                if (theBoard[row][col] != mark)
                    row_result = 0;
            if (row_result != 0)
                result = 1;
        }

        for (col = 0; result == 0 && col < 3; col++) {
            int col_result = 1;
            for (row = 0; col_result != 0 && row < 3; row++)
                if (theBoard[row][col] != mark)
                    col_result = 0;
            if (col_result != 0)
                result = 1;
        }

        if (result == 0) {
            int diag1Result = 1;
            for (row = 0; diag1Result != 0 && row < 3; row++)
                if (theBoard[row][row] != mark)
                    diag1Result = 0;
            if (diag1Result != 0)
                result = 1;
        }
        if (result == 0) {
            int diag2Result = 1;
            for (row = 0; diag2Result != 0 && row < 3; row++)
                if (theBoard[row][3 - 1 - row] != mark)
                    diag2Result = 0;
            if (diag2Result != 0)
                result = 1;
        }
        return result;
    }

    /**
     * Displays the column headers for the board.
     */
    void displayColumnHeaders() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("|col " + j);
        System.out.println();
    }

    /**
     * Adds hyphens to the board display.
     */
    void addHyphens() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("+-----");
        System.out.println("+");
    }

    /**
     * Adds spaces to the board display.
     */
    void addSpaces() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("|     ");
        System.out.println("|");
    }
}