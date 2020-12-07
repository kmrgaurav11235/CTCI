import java.util.Arrays;

/*
https://leetcode.com/problems/first-missing-positive/discuss/17214/Java-simple-solution-with-documentation
https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/

- Given an unsorted integer array nums, find the smallest missing positive integer.
- Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?
- Example 1:
    Input: nums = [1,2,0]
    Output: 3
- Example 2:
    Input: nums = [3,4,-1,1]
    Output: 2
- Example 3:
    Input: nums = [7,8,9,11,12]
    Output: 1

- Algorithm 1: Use Hashing: O(n) Space
- The basic idea is "For any n positive numbers (duplicates allowed), the first missing positive number 
    must be within [1...n+1]".
- We can use hashing. We can build an new boolean array of length n + 2. The array has indices 1...n+1 
    (ignoring 0). 
- We will go through the input array and mark all elements that lie in [1, n+1] as true in boolean array.
- Once the boolean array is filled, we can look at the indices starting starting from 1. As soon as we 
    find an index which is not marked 'true', we return it. 
- This approach may take O(n) time on average, but it requires O(n) extra space.

- Algorithm 2: A O(n) time and O(1) extra space solution
- Here, we must be allowed to change the input array.
- The numbers outside the range [1...n + 1] cannot be the solution. So, they can be marked with a special 
    . e.g. Change their value to n + 1. 
- Then, we use array elements as index and mark those that we have already visited. To mark presence of 
    an element x, we change the value at the index x to negative. 
- Afterwards, find the first cell which isn't negative (doesn't appear in the array).
- If no positive numbers were found, this means the array contains all numbers 1..n. Return n + 1.
*/
public class AS_08_FirstMissingPositive {
    static int firstMissingPositive(int [] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        // Mark numbers num < 0 and num > n with a special marker number n + 1
        for (int i = 0; i < n; i++) {
            if (arr[i] < 1 || arr[i] > n) {
                arr[i] = n + 1;
            } 
        }
        // All numbers in the array are now positive and in the range [1...n+1]

        // Mark each number appearing in the array as visited by converting the index for that number to (-)ve
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(arr[i]);
            if (abs == n + 1) {
                continue;
            } 
            // 'abs - 1' for zero-index based array
            // Also, we make sure to prevent double (-)ve operations
            arr[abs - 1] = -1 * Math.abs(arr[abs - 1]);
        }

        // Find the first cell that is not negative
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                return i + 1;
            }
        }

        // No positive number found. This means that the array contains all numbers from 1 to n.
        return n + 1;
    }
    public static void main(String[] args) {
        int [] nums1 = {1,2,0};
        System.out.println("Array: " + Arrays.toString(nums1) 
            + ", First Missing Positive: " + firstMissingPositive(nums1));

        int [] nums2 = {3,4,-1,1};
        System.out.println("Array: " + Arrays.toString(nums2) 
            + ", First Missing Positive: " + firstMissingPositive(nums2));

        int [] nums3 = {7,8,9,11,12};
        System.out.println("Array: " + Arrays.toString(nums3) 
            + ", First Missing Positive: " + firstMissingPositive(nums3));
    }
}
