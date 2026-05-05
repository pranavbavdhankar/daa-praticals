import java.util.*;

public class NQueens {

    static int N;

    // Print board
    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if safe
    static boolean isSafe(int[][] board, int row, int col) {

        // Column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Backtracking
    static boolean solve(int[][] board, int row) {

        if (row == N) {
            printBoard(board);
            return true;
        }

        boolean found = false;

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {

                board[row][col] = 1;

                found = solve(board, row + 1) || found;

                board[row][col] = 0; // backtrack
            }
        }

        return found;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        N = sc.nextInt();

        int[][] board = new int[N][N];

        boolean result = solve(board, 0);

        if (!result) {
            System.out.println("No solution exists");
        }

        sc.close();
    }
}