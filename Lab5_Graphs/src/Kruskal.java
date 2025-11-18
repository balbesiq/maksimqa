import java.util.*;

public class Kruskal {
    final static int V = 8;
    final static int X = 100;

    public static void main(String[] args) {
        int[][] G = {
                {X,8,2,4,3,X,X,X},
                {8,X,X,X,6,3,X,X},
                {2,X,X,6,X,7,X,4},
                {4,X,6,X,1,X,X,X},
                {3,6,X,1,X,X,X,X},
                {X,3,7,X,X,X,3,1},
                {X,X,X,X,X,3,X,5},
                {X,X,4,X,X,1,5,X}
        };

        kruskal(G);
    }


    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }

    public static void kruskal(int[][] G) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (G[i][j] != X) edges.add(new Edge(i, j, G[i][j]));
            }
        }

        Collections.sort(edges);
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge e : edges) {
            int uRep = findSet(parent, e.u);
            int vRep = findSet(parent, e.v);
            if (uRep != vRep) {
                mst.add(e);
                totalWeight += e.w;
                unionSet(parent, uRep, vRep);
            }
        }

        System.out.println("Edge : Weight");
        for (Edge e : mst) {
            System.out.println((e.u + 1) + " - " + (e.v + 1) + " : " + e.w);
        }
        System.out.println("Total weight: " + totalWeight);
    }

    private static int findSet(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = findSet(parent, parent[i]);
    }

    private static void unionSet(int[] parent, int u, int v) {
        parent[u] = v;
    }
}