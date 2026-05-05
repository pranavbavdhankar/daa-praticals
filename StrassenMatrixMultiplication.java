import java.util.*;

public class StrassenMatrixMultiplication {

    // Add matrices
    static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // Subtract matrices
    static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // Strassen function
    static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        int mid = n / 2;

        int[][] A11 = new int[mid][mid];
        int[][] A12 = new int[mid][mid];
        int[][] A21 = new int[mid][mid];
        int[][] A22 = new int[mid][mid];

        int[][] B11 = new int[mid][mid];
        int[][] B12 = new int[mid][mid];
        int[][] B21 = new int[mid][mid];
        int[][] B22 = new int[mid][mid];

        // Divide matrices
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + mid];
                A21[i][j] = A[i + mid][j];
                A22[i][j] = A[i + mid][j + mid];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + mid];
                B21[i][j] = B[i + mid][j];
                B22[i][j] = B[i + mid][j + mid];
            }
        }

        int[][] M1 = strassen(add(A11, A22), add(B11, B22));
        int[][] M2 = strassen(add(A21, A22), B11);
        int[][] M3 = strassen(A11, subtract(B12, B22));
        int[][] M4 = strassen(A22, subtract(B21, B11));
        int[][] M5 = strassen(add(A11, A12), B22);
        int[][] M6 = strassen(subtract(A21, A11), add(B11, B12));
        int[][] M7 = strassen(subtract(A12, A22), add(B21, B22));

        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        // Merge
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                C[i][j] = C11[i][j];
                C[i][j + mid] = C12[i][j];
                C[i + mid][j] = C21[i][j];
                C[i + mid][j + mid] = C22[i][j];
            }
        }

        return C;
    }

    // Utility to find next power of 2
    static int nextPowerOf2(int n) {
        int power = 1;
        while (power < n) power *= 2;
        return power;
    }

    // Pad matrix
    static int[][] padMatrix(int[][] A, int size) {
        int[][] padded = new int[size][size];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A.length; j++)
                padded[i][j] = A[i][j];
        return padded;
    }

    // Print matrix
    static void printMatrix(int[][] M, int originalSize) {
        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < originalSize; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of matrix (n x n): ");
        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter matrix A:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();

        System.out.println("Enter matrix B:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j] = sc.nextInt();

        int size = nextPowerOf2(n);

        int[][] A_pad = padMatrix(A, size);
        int[][] B_pad = padMatrix(B, size);

        int[][] result = strassen(A_pad, B_pad);

        System.out.println("\nResult:");
        printMatrix(result, n);

        sc.close();
    }
}