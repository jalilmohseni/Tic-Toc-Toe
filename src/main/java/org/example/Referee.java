package org.example;

/**
 * The Referee class manages the game flow between two players.
 * It initializes the game, sets the opponents, and starts the game.
 */
public class Referee {
    private Board theBoard;
    private Player xPlayer;
    private Player oPlayer;

    /**
     * Constructs a Referee object.
     */
    public Referee() {
    }

    /**
     * Sets the board for the game.
     *
     * @param theBoard the game board
     */
    public void setBoard(Board theBoard) {
        this.theBoard = theBoard;
    }

    /**
     * Sets the X player.
     * @param xPlayer the player using the 'X' mark
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    /**
     * Sets the O player.
     * @param oPlayer the player using the 'O' mark
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * Starts the game by setting opponents, displaying the board, and initiating play.
     */
    public void runTheGame() {
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        theBoard.display();
        xPlayer.play();
    }
}