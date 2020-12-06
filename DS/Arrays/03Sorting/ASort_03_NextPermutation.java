import java.util.Arrays;

/*
https://leetcode.com/problems/next-permutation/solution/
https://www.geeksforgeeks.org/find-next-greater-number-set-digits/

- Implement next permutation, which rearranges numbers into the lexicographically next greater 
    permutation of numbers.
- If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., 
    sorted in ascending order).

- Example 1:
    Input: nums = [1,2,3]
    Output: [1,3,2]
- Example 2:
    Input: nums = [3,2,1]
    Output: [1,2,3]
- Example 3:
    Input: nums = [1,1,5]
    Output: [1,5,1]
- Example 4:
    Input: nums = [1]
    Output: [1]

- First, we observe that for any given sequence that is in descending order, no next larger permutation 
    is possible. In this case, just reverse the array.
- Now, we need to find the first pair of two successive numbers a[i] and a[i−1], from the right, which 
    satisfy a[i] > a[i−1]. 
- No rearrangements to the right of a[i−1] can create a larger permutation since that sub-array consists 
    of numbers in descending order. Thus, we need to rearrange the numbers to the right of a[i−1] including 
    itself.
- Now, what kind of rearrangement will produce the next larger number? We want to create the permutation 
    just larger than the current one. Therefore, we need to replace the number a[i−1] with the number which 
    is just larger than itself among the numbers lying to its right section, say a[j].
- So, we swap the numbers a[i−1] and a[j]. We now have the correct number at index i−1. But still the current 
    permutation isn't the permutation that we are looking for. 
- We need the smallest permutation that can be formed by using the numbers only to the right of a[i−1]. 
    Therefore, we need to place those numbers in ascending order to get their smallest permutation.
- But, recall that while scanning the numbers from the right, we simply kept decrementing the index until we 
    found the pair a[i] and a[i−1]. Thus, all numbers to the right of a[i−1] were already sorted in descending 
    order. Furthermore, swapping a[i−1] and a[j] didn't change that order. 
- Therefore, we simply need to reverse the numbers following a[i−1] to get the next smallest lexicographic 
    permutation.
*/
public class ASort_03_NextPermutation {
    static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int n = nums.length;
        int firstIncrementFromEnd = n - 2;
        while (firstIncrementFromEnd >= 0 && nums[firstIncrementFromEnd] >= nums[firstIncrementFromEnd + 1]) {
            firstIncrementFromEnd--;
        }

        if (firstIncrementFromEnd == -1) {
            // This is the largest possible number
            reverse(nums, 0, n - 1);
            return;
        } 

        // firstIncrementFromEnd is the index where nums[i] < nums[i + 1]
        // find the index with which it can be swapped, i.e. a number just greater than nums[firstIncrementFromEnd]
        int swapIndex = n - 1;
        while (swapIndex >= 0 && nums[firstIncrementFromEnd] >= nums[swapIndex]) {
            swapIndex--;
        }
        swap(nums, firstIncrementFromEnd, swapIndex);

        // Reverse the rest
        reverse(nums, firstIncrementFromEnd + 1, n - 1);
    }
    private static void reverse(int[] nums, int start, int end) {
        // Reverse an array
        for (int i = start, j = end; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        // Swap numbers at index i and j
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int [] nums1 = {1,2,3};
        System.out.println("Current: " + Arrays.toString(nums1));
        nextPermutation(nums1); 
        System.out.println("Next: " + Arrays.toString(nums1));

        int [] nums2 = {5, 3, 4, 9, 7, 6}; 
        System.out.println("\nCurrent: " + Arrays.toString(nums2));
        nextPermutation(nums2); 
        System.out.println("Next: " + Arrays.toString(nums2));

        int [] nums3 = {11,5,3};
        System.out.println("\nCurrent: " + Arrays.toString(nums3));
        nextPermutation(nums3); 
        System.out.println("Next: " + Arrays.toString(nums3));
    }
}
