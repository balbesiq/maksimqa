public class insertSort {

    public static void sort(int[] arr) {
        int ass = 0, comp = 0;

        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];
            ass += 1;

            int j = i - 1;
            ass++;

            while (true) {
                comp += 1;
                if (j < 0 || key >= arr[j]) break;
                    arr[j + 1] = arr[j];
                ass += 1;

                j--;
                ass++;
            }

            arr[j + 1] = key;
            ass += 1;

            Main.print(arr);
            System.out.println("\nassignees:" + ass + " comparisons:" + comp);
        }
    }
}