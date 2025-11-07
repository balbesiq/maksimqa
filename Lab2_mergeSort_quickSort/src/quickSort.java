public class quickSort {

    private static int ass=0, comp=0, recur=1;

    public static String quickSort(int[] arr, int low, int high){

        Main.print(arr);
        System.out.println("\nAssignees: " + ass + ", Comparisons: " + comp +" Recursion calls:"+ recur);

        comp++;
        if(low<high){
            recur++;

            ass++;
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);

            quickSort(arr, pivotIndex + 1, high);
        }

        return "In total\nAssignees: " + ass + ", Comparisons: " + comp +" Recursion calls:"+ recur;
    }



    private static int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int left = low +1;
        int right = high;
        ass+=3;
        System.out.println("AT START left:"+ left +" right:"+right);

        while(true){

            comp+=2;
            while(left <= right && arr[left] <= pivot){
                left++;
                ass++;
            }

            comp+=2;
            while(right >= left && arr[right] >= pivot){
                right--;
                ass++;
            }

            comp++;
            if(right < left){
                break;
            }
            else{
                int tmp =  arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                ass+=3;
            }
        }

        int tmp = arr[low];
        arr[low] = arr[right];
        arr[right] = tmp;
        ass+=3;

       // System.out.println( "left:"+left+" right:"+right);
        return right;
    }
}