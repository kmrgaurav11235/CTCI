/*
https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
https://leetcode.com/problems/longest-increasing-subsequence/solution/

- This method relies on the fact that the longest increasing subsequence possible upto the i'th 
    index in a given array is independent of the elements coming later on in the array. Thus, 
    if we know the length of the LIS upto i'th index, we can figure out the length of the LIS 
    possible by including the (i+1)'th element based on the elements with indices j such that 
    0 <= j < (i+1)
- We make use of a memo array to store the required data. memo[i] represents the length of the 
    longest increasing subsequence possible considering the array elements upto the i'th index 
    only, by necessarily including the i'th element. In order to find out memo[i], we need to try 
    to append the current element(arr[i]) in every possible increasing subsequences upto the 
    (i−1)'th index(including the (i−1)'th index), such that the new sequence formed by adding the 
    current element is also an increasing subsequence. Thus, we can easily determine memo[i] using:

    memo[i] = max( memo[j] ) + 1, for all 0 <= j < i

- At the end, the maximum out of all the memo[i]'s to determine the final result.

    LIS length = max( memo[i] ),for all 0 <= i < n

- Time complexity : O(n^2). Two loops of n are there.
- Space complexity : O(n). memo array of size n is used.

*/
class DP_11_LongestIncreasingSubsequence_Efficient {
    static int longestIncreasingSubsequence(int [] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        /*
        Initialize the memo array. Its i'th element represents the length of the LIS possible considering 
        the array elements upto the i'th index only, by necessarily including the i'th element.
        */
        int [] memo = new int[n];

        // At index 0, LIS = 1
        memo[0] = 1;

        for (int i = 1; i < n; i++) {
            // Outer loop: Will find the value of memo[i]
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                /*
                Inner loop: Goes through all elements of memo[] from 0 to i - 1 and assign memo[i] by using 
                this formula:
                memo[i] = 1 + max( memo[j] ) where 0 <= j < i and arr[j] < arr[i]
                */
                if (arr[j] < arr[i]) {
                    maxVal = Math.max(maxVal, memo[j]);
                }
                memo[i] = maxVal + 1;
            }
        }

        // Pick maximum of all memo[] values
        int lis = 0;
        for (int i = 0; i < n; i++) {
            lis = Math.max(lis, memo[i]);
        }
        return lis;
    }
    public static void main(String[] args) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }; 
        System.out.println("Length of lis is " + longestIncreasingSubsequence(arr) + "." ); 
    }
}