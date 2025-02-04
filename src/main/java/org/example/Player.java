package org.example;

/**
 * The abstract Player class represents a player in the Tic-Tac-Toe game.
 * Each player has a name, a mark ('X' or 'O'), and knows their opponent and the game board.
 * Subclasses must implement the `makeMove()` method.
 */
public abstract class Player {
    protected String name;
    protected char mark;
    protected Player opponent;
    protected Board board;

    /**
     * Constructs a Player object with a name and a mark.
     * @param name the name of the player
     * @param mark the mark ('X' or 'O') used by the player
     */
    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Sets the opponent player.
     * @param opponent the opponent player
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Sets the game board.
     * @param theBoard the game board
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    /**
     * Initiates the player's turn. The player makes a move, and the turn passes to the opponent.
     * The game continues until a player wins or the board is full.
     */
    /**
     * Initiates the player's turn. The player makes a move, and the turn passes to the opponent.
     * The game continues until a player wins or the board is full.
     */
    public void play() {
        makeMove(); // Player makes a move
        board.display();

        // ✅ Check if the game has ended
        if (board.xWins()) {
            System.out.println("THE GAME IS OVER: " + name + " is the winner!");
            return;
        }

        if (board.isFull()) {
            System.out.println("THE GAME IS OVER: It's a tie!");
            return;
        }

        // ✅ Only continue if the game is still ongoing
        if (!board.isFull() && !board.xWins() && !board.oWins()) {
            opponent.play();
        }
    }





    /**
     * Abstract method for making a move.
     * This must be implemented by subclasses (HumanPlayer, RandomPlayer, etc.).
     */
    public abstract void makeMove();
}
