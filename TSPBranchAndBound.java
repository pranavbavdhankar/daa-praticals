import java.util.*;

public class TSPBranchAndBound {

    static int N;
    static int[][] cost;
    static boolean[] visited;
    static int[] currPath;
    static int[] finalPath;
    static int finalCost = Integer.MAX_VALUE;

    // Find minimum edge from a vertex
    static int firstMin(int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            if (i != k && cost[i][k] < min) {
                min = cost[i][k];
            }
        }
        return min;
    }

    // Find second minimum edge from a vertex
    static int secondMin(int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j) continue;

            if (cost[i][j] <= first) {
                second = first;
                first = cost[i][j];
            } else if (cost[i][j] < second) {
                second = cost[i][j];
            }
        }
        return second;
    }

    // Recursive BnB function
    static void tsp(int currBound, int currWeight, int level) {

        // If all cities visited
        if (level == N) {
            if (cost[currPath[level - 1]][currPath[0]] != 0) {
                int currRes = currWeight + cost[currPath[level - 1]][currPath[0]];

                if (currRes < finalCost) {
                    System.arraycopy(currPath, 0, finalPath, 0, N);
                    finalPath[N] = currPath[0];
                    finalCost = currRes;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {

            if (cost[currPath[level - 1]][i] != 0 && !visited[i]) {

                int temp = currBound;
                currWeight += cost[currPath[level - 1]][i];

                // Update bound
                if (level == 1) {
                    currBound -= ((firstMin(currPath[level - 1]) + firstMin(i)) / 2);
                } else {
                    currBound -= ((secondMin(currPath[level - 1]) + firstMin(i)) / 2);
                }

                // Check promising node
                if (currBound + currWeight < finalCost) {
                    currPath[level] = i;
                    visited[i] = true;

                    tsp(currBound, currWeight, level + 1);
                }

                // Backtrack
                currWeight -= cost[currPath[level - 1]][i];
                currBound = temp;
                Arrays.fill(visited, false);
                for (int j = 0; j < level; j++) {
                    visited[currPath[j]] = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        N = sc.nextInt();

        cost = new int[N][N];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[N];
        currPath = new int[N + 1];
        finalPath = new int[N + 1];

        int bound = 0;

        for (int i = 0; i < N; i++) {
            bound += (firstMin(i) + secondMin(i));
        }

        bound = (bound % 2 == 1) ? bound / 2 + 1 : bound / 2;

        visited[0] = true;
        currPath[0] = 0;

        tsp(bound, 0, 1);

        System.out.println("\nMinimum Cost: " + finalCost);
        System.out.print("Path: ");
        for (int i = 0; i <= N; i++) {
            System.out.print(finalPath[i] + " ");
        }

        sc.close();
    }
}