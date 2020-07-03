import java.util.Arrays;

/*
https://www.geeksforgeeks.org/two-pointers-technique/
- Two pointers is really an easy and effective technique which is typically used for searching 
    pairs in a sorted array.
- e.g. Given a sorted array A (sorted in ascending order), having N integers, find if there 
    exists any pair of elements (A[i], A[j]) such that their sum is equal to X.
- The two-pointer technique - We take two pointers, one representing the first element and other 
    representing the last element of the array, and then we add the values kept at both the 
    pointers. If their sum is smaller than X then we shift the left pointer to right or if their 
    sum is greater than X then we shift the right pointer to left, in order to get closer to the 
    sum. We keep moving the pointers until we get the sum as X.
- The algorithm basically uses the fact that the input array is sorted. We start the sum of extreme 
    values (smallest and largest) and conditionally move both pointers. We do not miss any pair 
    because the sum is already smaller than X. Same logic applies for right pointer.

- More problems based on two pointer technique.
    Find the closest pair from two sorted arrays
    Find the pair in array whose sum is closest to x
    Find all triplets with zero sum
    Find a triplet that sum to a given value
    Find a triplet such that sum of two equals to third element
    Find four elements that sum to a given value
    The Celebrity Problem
*/
class AS_03_TwoPointerTechnique {
    private static void displayPairSumIndices(int[] sortedArray, int requiredSum) {
        int left = 0, right = sortedArray.length - 1;
        while(left < right) {
            int currentSum = sortedArray[left] + sortedArray[right];
            if (currentSum == requiredSum) {
                System.out.println("Solution:");
                System.out.println("Array[" + left + "] = " + sortedArray[left]);
                System.out.println("Array[" + right + "] = " + sortedArray[right]);
                break;
            } else if (currentSum < requiredSum) {
                left++;
            } else {
                right--;
            }
        }
    }
    public static void main(String[] args) {
        int [] sortedArray = {-3, -1, 2, 4, 8, 12, 18};
        int requiredSum = 7;

        System.out.println("Array: " + Arrays.toString(sortedArray));
        System.out.println("Required Sum: " + requiredSum);
        displayPairSumIndices(sortedArray, requiredSum);
    }
}