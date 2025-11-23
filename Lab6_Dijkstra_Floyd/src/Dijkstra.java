import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

    final static int V = 8;
    final static int X =  100;

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

        dijkstra(G,0);


    }
    static int[] pred = new int[V];
    static int[] dist = new int[V];

    public static void dijkstra(int[][] G, int s) {


        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for(int i =  0; i < V; i++){
            pred[i] = -1;
            if(i == s) dist[i] = 0;
            else dist[i] = X;
            pq.add(new int[]{i,dist[i]});
        }

        System.out.print("      1 2 3 4 5 6 7 8            1 2 3 4 5 6 7 8\n");
        while (!pq.isEmpty()) {
            int[] rec = pq.poll();
            int u = rec[0];
            int du = rec[1];

            if (du != dist[u]) continue;


            for (int i = 0; i < 8; i++) {

                if (G[u][i] == X) continue;

                if (dist[i] > dist[u] + G[u][i]) {
                    dist[i] = dist[u] + G[u][i];
                    pred[i] = u;
                    pq.add(new int[]{i, dist[i]});
                }
            }
            printArrays(dist,pred);
        }

    }

    private static void printArrays(int[] dist, int[] pred) {

        System.out.print("dist: ");


        for (int k : dist) {
            if (k == X) System.out.print("X  ");
            else System.out.print(k + " ");
        }

        System.out.print("   pred: ");
        for (int j : pred) {
            System.out.print(j + " ");
        }

        System.out.println();
    }
}