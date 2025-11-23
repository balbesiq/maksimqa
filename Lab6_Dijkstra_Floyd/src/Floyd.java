public class Floyd {
    final static int V = 8;
    final static int X =  100;
    static int[][] W = new int[V][V];

    public static void main(String[] args) {
        int[][] G = {
                {0,8,2,4,3,X,X,X},
                {8,0,X,X,6,3,X,X},
                {2,X,0,6,X,7,X,4},
                {4,X,6,0,1,X,X,X},
                {3,6,X,1,0,X,X,X},
                {X,3,7,X,X,0,3,1},
                {X,X,X,X,X,3,0,5},
                {X,X,4,X,X,1,5,0}
        };

        floyd(G);

       print(W);
    }

    static void  floyd(int[][]G){

            for (int i = 0; i < V; i++) {
                System.arraycopy(G[i], 0, W[i], 0, V);
            }

            for (int k = 0; k < V; k++) {
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        if (W[i][k] + W[k][j] < W[i][j]) {
                            W[i][j] = W[i][k] + W[k][j];
                        }
                    }

                }
                System.out.println("k = "+(k+1));
                print(W);
                System.out.println();
            }
    }

    static void print(int[][] G){
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(G[i][j] == X){
                    System.out.print("X  ");
                }
                else{
                System.out.printf("%-3d", W[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
}
