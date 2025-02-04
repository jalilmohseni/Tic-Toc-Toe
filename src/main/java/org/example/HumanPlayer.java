package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The HumanPlayer class represents a human-controlled player in the Tic-Tac-Toe game.
 */
public class HumanPlayer extends Player {
    private BufferedReader stdin;

    public HumanPlayer(String name, char mark) {
        super(name, mark);
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void makeMove() {
        try {
            while (true) {
                int row = getValidInput(name + ", what row should your next " + mark + " be placed in? (0, 1, 2): ");
                int col = getValidInput(name + ", what column should your next " + mark + " be placed in? (0, 1, 2): ");

                if (board.getMark(row, col) == Constants.SPACE_CHAR) {
                    board.addMark(row, col, mark);
                    break;
                } else {
                    System.out.println("The selected cell is already occupied. Please choose another cell.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getValidInput(String prompt) throws IOException {
        while (true) {
            System.out.print(prompt);
            String input = stdin.readLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= 0 && value <= 2) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a number between 0 and 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
