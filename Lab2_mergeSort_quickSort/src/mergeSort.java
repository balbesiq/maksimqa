public class mergeSort {

    private static int k =0, ass = 0, comp = 0, recur = 0;

    public static void mergeSort(int[] A) {

        int n =  A.length;


        if (n < 2) {
            return;
        }


        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = A[i];
            ass++;
        }

        for (int i = mid; i < n; i++) {
            right[i - mid] = A[i];
            ass++;
        }

        recur++;
        mergeSort(left);

        recur++;
        mergeSort(right);

        System.out.println("\nItteration"+k);

        System.out.println("left part:");
        Main.print(left);
         System.out.println("\nright part:");
        Main.print(right);

        merge(A, left, right, left.length, right.length);
        System.out.println("\nAfter merge: ");
        Main.print(A);

        System.out.println("\nAssignees: "+ass+" Comparisons: "+comp +" Recursion calls:"+ recur);
        System.out.println("\n");
        k++;
    }

    public static void merge(int[] A, int[] left, int[] right, int l, int r) {
        int i = 0, j = 0, k = 0;


        while (i < l && j < r) {

            comp++;
            if (left[i] <= right[j]) {

                A[k++] = left[i++];
                ass++;
            }

            else {
                A[k++] = right[j++];
                ass++;
            }
        }

        while (i < l) {
            A[k++] = left[i++];
            ass++;
        }

        while (j < r) {
            A[k++] = right[j++];
            ass++;
        }
    }
}
