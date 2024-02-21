import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;

        // Initialize the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < BOARD_SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    public boolean isMoveValid(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL;
    }

    public boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer ||
                board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    public int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row (0, 1, or 2): ");
        int row = scanner.nextInt();
        System.out.println("Enter column (0, 1, or 2): ");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }

    public int[] getComputerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (!isMoveValid(row, col));
        return new int[]{row, col};
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        while (true) {
            game.printBoard();

            int[] move;
            if (game.currentPlayer == PLAYER_X) {
                move = game.getPlayerMove();
            } else {
                move = game.getComputerMove();
            }

            int row = move[0];
            int col = move[1];

            if (game.isMoveValid(row, col)) {
                game.makeMove(row, col);

                if (game.checkWinner()) {
                    game.printBoard();
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    break;
                } else if (game.isBoardFull()) {
                    game.printBoard();
                    System.out.println("It's a draw!");
                    break;
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
