import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class BellmanFord {

    static void bellmanFord(int V, List<Edge> edges, int source) {

        int[] dist = new int[V];
        int[] parent = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        // Relax edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[e.src] + e.weight;
                    parent[e.dest] = e.src;
                }
            }
        }

        // Check negative cycle
        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                dist[e.src] + e.weight < dist[e.dest]) {

                System.out.println("Graph contains negative weight cycle!");
                return;
            }
        }

        // Print result
        System.out.println("\nVertex\tDistance\tPath");

        for (int i = 0; i < V; i++) {
            System.out.print(i + "\t" + dist[i] + "\t\t");
            printPath(parent, i);
            System.out.println();
        }
    }

    static void printPath(int[] parent, int v) {
        if (v == -1) return;
        printPath(parent, parent[v]);
        System.out.print(v + " ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(s, d, w));
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        bellmanFord(V, edges, source);

        sc.close();
    }
}