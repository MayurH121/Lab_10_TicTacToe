import java.util.Scanner;  // Added Scanner utility so that users can input text

public class TicTacToe {
    private static final int ROWS = 3; // Added static final to Rows to set it as a constant to 3
    private static final int COLS = 3; // Added static final to Cols to set it as a constant to 3
    private static String[][] board = new String[ROWS][COLS]; // Create a 3x3 matrix board
    private static int moveCount = 0; // Will track move count
    private static String currentPlayer = "X";
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args) {
        boolean playAgain; // Game replay control

        do {
            // Initialize the game
            clearBoard(); //Method after the main to clear the board
            moveCount = 0; //Reset the move count to 0
            currentPlayer = "X"; // X always starts first
            boolean gameOver = false; // Control for game over boolean

            // Game loop
            while (!gameOver) {  //Means keep looping until game over is True
                // Display the board
                display();

                // Get player move
                System.out.println("\nPlayer " + currentPlayer + "'s turn");
                int row = SafeInput.getRangedInt(scanner, "Enter row", 1, 3) - 1;
                int col = SafeInput.getRangedInt(scanner, "Enter column", 1, 3) - 1;

                // Validate move
                while (!isValidMove(row, col)) {
                    System.out.println("\nInvalid move! That spot is already taken.");
                    row = SafeInput.getRangedInt(scanner, "Enter row", 1, 3) - 1;
                    col = SafeInput.getRangedInt(scanner, "Enter column", 1, 3) - 1;
                }

                // Make the move
                board[row][col] = currentPlayer;
                moveCount++;

                // Check for win or tie
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("\nPlayer " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    display();
                    System.out.println("\nIt's a tie!");
                    gameOver = true;
                } else {
                    // Switch players
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";  // Switch to O if current is X
                        } else {
                        currentPlayer = "X";  // Switch to X if current is O
                        }
                }
            }

            // Ask to play again
            playAgain = SafeInput.getYNConfirm(scanner, "Would you like to play again");

        } while (playAgain);

        System.out.println("\nThanks for playing!");
        scanner.close();
    }

    private static void clearBoard() {   //Set Rows and Cols to blank
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() { // Prints horizontal and vertical lines
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println("-----------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        // Check top-left to bottom-right
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        // Check top-right to bottom-left
        return board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player);
    }

    private static boolean isTie() {
        // If all spaces are filled, it's a tie
        if (moveCount == ROWS * COLS) {
            return true;
        }
        return false;
    }
}