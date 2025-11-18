public class Prim {
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
        prim(G);
    }

    public static void prim(int[][] G) {
        int no_edge = 0;
        int[] selected = new int[V];

        selected[0] = 1;

        int x, y, total =0;

        System.out.println("Edge : Weight");
        while (no_edge < V - 1) {
            int min =X;
            x = 0;
            y = 0;

            for (int i = 0; i < V; i++) {
                if (selected[i] == 1) {

                    for (int j = 0; j < V; j++) {

                        if (selected[j] == 0 && G[i][j] != X) {

                            if (min > G[i][j]) {
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            total+=G[x][y];
            System.out.println((x + 1) + " - " + (y + 1) + " : " + G[x][y]);
            selected[y] = 1;
            no_edge++;
        }
        System.out.println("Total weight: " + total);
    }
}