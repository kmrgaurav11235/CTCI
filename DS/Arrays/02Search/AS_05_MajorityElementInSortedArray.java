/*
https://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/

- Check if a given integer num appears more than n/2 times in a sorted array of n integers. 
- Basically, we need to write a function say isMajority() that takes an array: arr[], array’s size
    : n and a number to be searched: num as parameters. It returns true if num is a majority element 
    (present more than n/2 times).
- Examples: 
    Input: arr[] = {1, 2, 3, 3, 3, 3, 10}, x = 3
    Output: True (x appears more than n/2 times in the given array)

    Input: arr[] = {1, 1, 2, 4, 4, 4, 6, 6}, x = 4
    Output: False (x doesn't appear more than n/2 times in the given array)

    Input: arr[] = {1, 1, 1, 2, 2}, x = 1
    Output: True (x appears more than n/2 times in the given array)
- Approach: Use binary search methodology to find the first occurrence of the given number. Then, check 
    element at index i + n/2. If num is present at i + n/2 then return true else return false.
*/
import java.util.Arrays;

public class AS_05_MajorityElementInSortedArray {

    private static int binarySearchFirstIndex(int[] arr, int left, int right, int num) {
        /* If x is present in arr[low...high] then returns the index of first occurrence of num, 
        otherwise returns -1 */
        if (left <= right) {
            int mid = (left + right) / 2;
            /* 
            Check if arr[mid] is the first occurrence of x.
            arr[mid] is first occurrence if num is one of the following is true:
                (i)  mid == 0 and arr[mid] == num
                (ii) arr[mid-1] < x and arr[mid] == num
            */
            if (num == arr[mid] && (mid == 0 || arr[mid - 1] < num)) {
                return mid;
            } else if (num > arr[mid]) {
                return binarySearchFirstIndex(arr, mid + 1, right, num);
            } else {
                return binarySearchFirstIndex(arr, left, mid - 1, num);
            }
        }
        return -1;
    }
    static boolean isMajority(int[] arr, int n, int num) {
        // Find the index of first occurrence of num in arr[]
        int firstIndex = binarySearchFirstIndex(arr, 0, n - 1, num);

        // If element is present, check if it is present more than n/2 times
        if (firstIndex != -1 && firstIndex + (n / 2) < n && arr[firstIndex + (n / 2)] == num) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 3, 3, 3, 10};
        int n = arr.length;
        int num = 3;
        System.out.println("arr[]: " + Arrays.toString(arr));
        if (isMajority(arr, n, num)) {
            System.out.println(num + " appears more than " + n/2 + " times in arr[].");
        } else {
            System.out.println(num + " does not appear more than " + n/2 + " times in arr[].");
        }
    }
}
