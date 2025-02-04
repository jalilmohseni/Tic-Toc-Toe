package org.example;

import java.io.*;

/**
 * The Game class is the entry point of the Tic-Tac-Toe game.
 * It initializes the game, allows player type selection, and appoints a referee to manage the game.
 */
public class Game implements Constants {

    private Board theBoard;
    private Referee theRef;

    /**
     * Constructs a Game object and initializes the game board.
     */
    public Game() {
        theBoard = new Board();
    }

    /**
     * Appoints a referee to manage the game.
     * @param r the referee to be appointed
     * @throws IOException if an I/O error occurs
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.runTheGame();
    }

    /**
     * The main method that starts the Tic-Tac-Toe game.
     * @param args command line arguments (not used)
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        Referee theRef;
        Player xPlayer, oPlayer;
        BufferedReader stdin;
        Game theGame = new Game();
        stdin = new BufferedReader(new InputStreamReader(System.in));

        // Get and validate X player's name and type
        String xName = getValidName(stdin, "X");
        xPlayer = createPlayer(stdin, xName, LETTER_X);
        xPlayer.setBoard(theGame.theBoard);

        // Get and validate O player's name and type
        String oName = getValidName(stdin, "O");
        oPlayer = createPlayer(stdin, oName, LETTER_O);
        oPlayer.setBoard(theGame.theBoard);

        theRef = new Referee();
        theRef.setBoard(theGame.theBoard);
        theRef.setoPlayer(oPlayer);
        theRef.setxPlayer(xPlayer);

        theGame.appointReferee(theRef);
    }

    /**
     * Method to get and validate a player's name.
     * @param stdin  the input reader
     * @param player the player type ("X" or "O")
     * @return a valid player name
     * @throws IOException if an I/O error occurs
     */
    private static String getValidName(BufferedReader stdin, String player) throws IOException {
        while (true) {
            System.out.print("\nPlease enter the name of the '" + player + "' player: ");
            String name = stdin.readLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            } else if (name.length() > 20) {
                System.out.println("Name is too long. Please keep it under 20 characters.");
            } else if (!name.matches("[a-zA-Z0-9 _-]+")) {
                System.out.println("Name contains invalid characters. Only letters, numbers, spaces, '-', and '_' are allowed.");
            } else {
                return name; // Return the valid name
            }
        }
    }

    /**
     * Method to select a player type and create the appropriate Player object.
     * @param stdin the input reader
     * @param name  the name of the player
     * @param mark  the mark ('X' or 'O') used by the player
     * @return a Player object of the selected type
     * @throws IOException if an I/O error occurs
     */
    private static Player createPlayer(BufferedReader stdin, String name, char mark) throws IOException {
        while (true) {
            System.out.println("\nWhat type of player is " + name + "?");
            System.out.println("1: Human");
            System.out.println("2: Random Player");
            System.out.println("3: Blocking Player");
            System.out.println("4: Smart Player");
            System.out.print("Please enter a number (1-4): ");

            try {
                int choice = Integer.parseInt(stdin.readLine().trim());

                switch (choice) {
                    case 1:
                        return new HumanPlayer(name, mark);
                    case 2:
                        return new RandomPlayer(name, mark);
                    case 3:
                        return new BlockingPlayer(name, mark);
                    case 4:
                        return new SmartPlayer(name, mark);
                    default:
                        System.out.println("Invalid selection. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
