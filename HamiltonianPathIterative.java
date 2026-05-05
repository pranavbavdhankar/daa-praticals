import java.util.*;

public class HamiltonianPathIterative {

    static boolean findHamiltonianPath(int[][] graph, int V) {

        for (int start = 0; start < V; start++) {

            boolean[] visited = new boolean[V];
            int[] path = new int[V];
            int[] nextIndex = new int[V]; // track next neighbor to try

            Arrays.fill(path, -1);

            Stack<Integer> stack = new Stack<>();

            path[0] = start;
            visited[start] = true;
            stack.push(start);

            int pos = 1;

            while (!stack.isEmpty()) {

                if (pos == V) {
                    // Found Hamiltonian Path
                    System.out.println("Hamiltonian Path Exists:");
                    for (int v : path) System.out.print(v + " ");
                    System.out.println();
                    return true;
                }

                int current = stack.peek();
                boolean found = false;

                for (int i = nextIndex[current]; i < V; i++) {

                    nextIndex[current] = i + 1;

                    if (graph[current][i] == 1 && !visited[i]) {

                        path[pos++] = i;
                        visited[i] = true;
                        stack.push(i);

                        found = true;
                        break;
                    }
                }

                // Backtrack
                if (!found) {
                    int removed = stack.pop();
                    visited[removed] = false;
                    nextIndex[removed] = 0;
                    pos--;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        boolean exists = findHamiltonianPath(graph, V);

        if (!exists) {
            System.out.println("No Hamiltonian Path exists.");
        }

        sc.close();
    }
}