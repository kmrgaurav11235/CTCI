import java.util.Arrays;

/*
https://www.geeksforgeeks.org/window-sliding-technique/
- 
Window Sliding technique shows how a nested for-loop in some problems can be converted to a 
    single for-loop to reduce the time complexity.
- e.g. Given an array of integers of size n, our aim is to calculate the maximum sum of ‘k’ 
    consecutive elements in the array.
- Applying sliding window technique :
    1. We compute the sum of first k elements out of n terms using a linear loop and store the 
        sum in variable windowSum.
    2. Then we will graze linearly over the array till it reaches the end and simultaneously keep 
        track of maximumSum.
    3. To get the current sum of block of k elements just subtract the first element from the 
        previous block and add the last element of the current block.

*/
public class Arrays_06_SlidingWindow {
    private static int getMaximumSum(int[] arr, int n, int k) {
        if (n < k) {
            System.out.println("Bad Request. Array size cannot be smaller than k.");
            return -1;
        }
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maximumSum = windowSum;

        for (int left = 0, right = k; right < n; left++, right++) {
            windowSum = windowSum + arr[right] - arr[left];
            if (maximumSum < windowSum) {
                maximumSum = windowSum;
            }
        }
        return maximumSum;
    }
    public static void main(String[] args) {
        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 }; 
        int k = 4; 
        int n = arr.length; 

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("k: " + k);
        
        System.out.println("Maximum Sum: " + getMaximumSum(arr, n, k)); 
    }
}