import java.util.Arrays;

/*
https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/
https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/

- Given an integer array nums[], you need to find one continuous sub-array, so that if you only sort this 
    sub-array in ascending order, then the whole array will be sorted in ascending order.

 - Example 1:
    Input: nums = [2,6,4,8,10,9,15]
    Output: 5
    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted.
- Example 2:
    Input: nums = [1,2,3,4]
    Output: 0
- Example 3:
    Input: nums = [1]
    Output: 0

- Approach 1: Using Sorting
    * We can sort a copy of the given array nums[], say sortedNums[]. 
    * Then, if we compare the elements of nums and sortedNums, we can determine the leftmost and rightmost 
        elements which mismatch. 
    * The sub-array lying between them is the required unsorted sub-array.
    * Time complexity : O(n log ⁡n). Sorting time.
    * Space complexity : O(n). We are making copy of original array. 

- Approach 2: Without Using Extra Space
    * The idea behind this method is that the correct position of the minimum element in the unsorted sub-array 
        helps to determine the required left boundary. 
    * Similarly, the correct position of the maximum element in the unsorted sub-array helps to determine the 
        required right boundary.
    * Thus, firstly we need to determine when the correctly sorted array goes wrong. We keep a track of this by 
        observing rising slope starting from the beginning of the array. Whenever the slope falls, we know that 
        the unsorted array has surely started. 
    * Thus, now we determine the minimum element found till the end of the array nums[], given by min.
    * Similarly, we scan the array nums[] in the reverse order. When the slope starts rising instead of falling, 
        we start looking for the maximum element till we reach the beginning of the array, given by max.
    * Then, we traverse over nums and determine the correct position of min and max by comparing these elements 
        with the other array elements. 
    * To determine the correct position of min, we know the initial portion of nums is already sorted. Thus, we 
        need to find the first element which is just larger than min. Similarly, for max's position, we need to 
        find the first element which is just smaller than max searching in nums backwards.

*/

public class ASort_02_ShortestUnsortedContinuousSubArray {
    static int shortestUnsortedSubArray(int [] nums, int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        int firstDownwardSlope = -1;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                firstDownwardSlope = i + 1;
                break;
            }
        }
        if (firstDownwardSlope == -1) {
            // array is sorted
            return 0;
        }

        int lastDownwardSlope = -1;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] > nums[i]) {
                lastDownwardSlope = i;
                break;
            }
        }

        // Find min and max between firstDownwardSlope and lastDownwardSlope
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = firstDownwardSlope; i <= lastDownwardSlope; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }

        // Find number just bigger than min to left of firstDownwardSlope
        // Remember that nums[0 ... firstDownwardSlope] is sorted
        int startUnsorted = 0;
        for (int i = 0; i < firstDownwardSlope; i++) {
            if (nums[i] > min) {
                startUnsorted = i;
                break;
            }
        }

        // Find number just smaller than max to the right of lastDownwardSlope
        // Remember that nums[lastDownwardSlope ... n - 1] is sorted
        int endUnsorted = n - 1;
        for (int i = n - 1; i >= lastDownwardSlope; i--) {
            if (nums[i] < max) {
                endUnsorted = i;
                break;
            }
        }
        return endUnsorted - startUnsorted + 1;
    }
    public static void main(String[] args) {
        int nums[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60}; 
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Length of Shortest unsorted sub-array: " + shortestUnsortedSubArray(nums, nums.length)); 
    }
}
