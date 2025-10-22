public class Main {
    public static void main(String[] args) {

        int[] A = {50, 80, 19, 86, 35, 7, 60, 48, 51};
        int[] B = {4, 10, 3, 5, 1, 2, 7, 8, 9};

        heapSort.sort(A);

        print(A);
    }

    public static void print (int[] arr){
        System.out.print("\n ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}