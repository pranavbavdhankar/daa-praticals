import java.util.*;

public class KnapsackDP {

    static int knapsack(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        val[i - 1] + dp[i - 1][w - wt[i - 1]]
                    );
                }
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected items
        System.out.println("\nSelected items (0-based index):");
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print((i - 1) + " ");
                w -= wt[i - 1];
            }
        }
        System.out.println();

        return dp[n][W];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int maxProfit = knapsack(wt, val, W, n);

        System.out.println("\nMaximum value: " + maxProfit);

        sc.close();
    }
}