import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        boolean playAgain;

        do {
            initializeBoard(board);
            boolean gameWon = false;
            boolean playerXTurn = true;
            int movesCount = 0;

            while (!gameWon && movesCount < 9) {
                displayBoard(board);
                if (playerXTurn) {
                    System.out.println("Player X's turn.");
                } else {
                    System.out.println("Player O's turn.");
                }

                int row, col;
                while (true) {
                    System.out.print("Enter row (1-3): ");
                    row = scanner.nextInt() - 1;
                    System.out.print("Enter column (1-3): ");
                    col = scanner.nextInt() - 1;

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                        break;
                    } else {
                        System.out.println("This move is not valid. Try again.");
                    }
                }

                board[row][col] = playerXTurn ? 'X' : 'O';
                gameWon = checkWin(board, playerXTurn ? 'X' : 'O');
                playerXTurn = !playerXTurn;
                movesCount++;
            }

            displayBoard(board);
            if (gameWon) {
                System.out.println("Player " + (playerXTurn ? 'O' : 'X') + " wins!");
            } else {
                System.out.println("It's a draw!");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        scanner.close();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }
}
