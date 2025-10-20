public class Main {
    public static void main(String[] args) {

        int[] A = {50, 80, 19, 86, 35, 7, 60, 48, 51};
        int[] B = {50, 80, 19, 86, 35, 7, 60, 48, 51};


        Main.print(A);
        System.out.print("\n\n");
        mergeSort.mergeSort(A);

        System.out.print("\n\n");
        Main.print(B);
        System.out.print("\n\n");
        System.out.println(quickSort.quickSort(B, 0, B.length - 1));
    }

    public static void print (int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}