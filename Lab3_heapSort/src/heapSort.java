public class heapSort {

    private static int ass =0, comp =0, recur=1;

    public static void sort(int[] arr) {
        int n = arr.length;


        System.out.println("BUILDING HEAP");
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            System.out.println("Assignees:"+ ass + " Comparissons:"+ comp +" Recursive calls:"+ recur);
        }

        System.out.println("ARRAY AFTER HEAP BUILD");
        Main.print(arr);

        System.out.print("\nSORTING HEAP\n");
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            ass+=3;

            heapify(arr, i, 0);
            Main.print(arr);
            System.out.println("Assignees:"+ ass + " Comparissons:"+ comp+" Recursive calls:"+ recur);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        ass+=3;



        if (l < n){
            if(arr[l] > arr[largest]){

                largest = l;
                ass++;
            }
            comp++;
        }
        comp++;


        if (r < n){
            if(arr[r] > arr[largest]){

                largest = r;
                ass++;
            }
            comp++;
        }
        comp++;


        System.out.println("Largest:" + arr[largest]+" index"+largest+" i:"+i);
        comp++;
        if (largest != i) {

            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            ass+=3;
            System.out.println("Largest:" + arr[largest]+" index^ "+largest+" i:"+i+"   (if largest != i)");

            recur++;
            heapify(arr, n, largest);
        }
    }
}
