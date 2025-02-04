package org.example;

import org.example.RandomGenerator;

/**
 * The RandomPlayer class represents a computer player that picks a move randomly.
 */
public class RandomPlayer extends Player {
    protected RandomGenerator randomGen;

    /**
     * * Constructs a RandomPlayer object with a name and a mark.
     * @param name the name of Random Player.
     * @param mark the mark ('X' or 'O') used by the Random player.
     */
    public RandomPlayer(String name, char mark) {
        super(name, mark);
        this.randomGen = new RandomGenerator();
    }

    /**
     * Executes a move for the RandomPlayer by selecting an available board position at random.
     * The method generates random row and column and checks if the selected cell is empty.
     * If the cell is occupied, it continues generating new random positions until an empty spot is found.
     */

    @Override
    public void makeMove() {
        int row, col;
        do {
            row = randomGen.discrete(0, 2);
            col = randomGen.discrete(0, 2);
        } while (board.getMark(row, col) != Constants.SPACE_CHAR);

        board.addMark(row, col, mark);
        System.out.println(name + " (RandomPlayer) placed " + mark + " at (" + row + ", " + col + ")");
    }
}
