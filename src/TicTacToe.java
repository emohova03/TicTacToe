import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();
        char currentPlayer = 'X';

        while (true) {
            System.out.println("Player " + (currentPlayer == 'X' ? "1 (X)" : "2 (O)") + ", enter your move (e.g., A 1): ");
            String move = scanner.nextLine();

            if (makeMove(currentPlayer, move)) {
                displayBoard();

                if (checkWin(currentPlayer)) {
                    System.out.println("Player " + (currentPlayer == 'X' ? "1" : "2") + " wins!");
                    break;
                }

                if (isBoardFull()) {
                    System.out.println("The game is a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '□';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("   A B C");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean makeMove(char player, String move) {
        if (move.length() != 3) return false;

        char column = move.charAt(0);
        char row = move.charAt(2);

        int colIndex = column - 'A';
        int rowIndex = row - '1';

        if (colIndex < 0 || colIndex >= SIZE || rowIndex < 0 || rowIndex >= SIZE) return false;
        if (board[rowIndex][colIndex] != '□') return false;

        board[rowIndex][colIndex] = player;
        return true;
    }

    private static boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a winning line
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '□') return false;
            }
        }
        return true;
    }
}
