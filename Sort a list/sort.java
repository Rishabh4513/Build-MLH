
import java.util.Arrays;

public class sort {

    public static void main(String[] args) {
        int[] arr = {4,3,5,7,2};
        int[] arr1={4,7,1,3,5,0};
        insertion(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Using Quick Sort");
        sort s=new sort();
        s.Sort(arr1,0,5);
        System.out.println("sorted array"); 
		printArray(arr1);

    }
    // length is 5, 4 [0, 1, 2, 3, 4]
    // Time Complexity: O(N^2)
    public static void bubble(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j-1]) {
                    swap(nums, j, j-1);
                }
            }
        }
    }

    // Time Complexity: O(N^2)
    public static void selection(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int last = nums.length - 1 - i;
            int max = max_limit(nums, last);
            swap(nums, max, last);
        }
    }

    private static int max_limit(int[] nums, int end) {
        int max = 0;
        for (int i = 0; i <= end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }

    public static void insertion(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j > 0; j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums, j-1, j);
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    }
    void Sort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			Sort(arr, low, pi-1); 
			Sort(arr, pi+1, high); 
		} 
	} 
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        System.out.print("[");
        for (int i=0; i<n-1; ++i) 
            System.out.print(arr[i]+", "); 
        System.out.print(arr[n-1]+"]");
        System.out.println(); 
    }  
}