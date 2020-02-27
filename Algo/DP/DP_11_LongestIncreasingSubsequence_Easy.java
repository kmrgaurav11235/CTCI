/*
https://leetcode.com/problems/longest-increasing-subsequence/solution/
The redundant calls can be eliminated by storing the results obtained for a particular call in a 2-d memoization array memo. 
memo[i][j + 1] represents the length of the LIS possible using arr[i] as the current element considered to be included/not included 
in the LIS, with arr[j + 1] as the previous element considered to be included in the LIS. 

- Time complexity : O(n^2)
    Size of recursion tree can go upto n^2
- Space complexity : O(n^2) 
    memo array of n*n is used.
*/
class DP_11_LongestIncreasingSubsequence_Easy {
    private static int longestIncreasingSubsequenceUtil(int [] arr, int index, int prevIndex, int [][] memo) {
        if (index == arr.length) {
            return 0;
        } else if (memo[index][prevIndex + 1] != -1) {
            return memo[index][prevIndex + 1];
        }
        int includingCurrentIndex = 0, excludingCurrentIndex = 0;
        if (prevIndex == -1 || arr[prevIndex] < arr[index]) {
            includingCurrentIndex = 1 + longestIncreasingSubsequenceUtil(arr, index + 1, index, memo);
        }
        excludingCurrentIndex = longestIncreasingSubsequenceUtil(arr, index + 1, prevIndex, memo);
        memo[index][prevIndex + 1] = Math.max(includingCurrentIndex, excludingCurrentIndex);
        return memo[index][prevIndex + 1];
    }
    static int longestIncreasingSubsequence(int [] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int [][] memo = new int [n][n + 1];
        /* 
        n + 1 is needed to account for case where prevIndex = -1. Also, 
        prevIndex == -1 corresponds to memo[][0]
        prevIndex ==  0 corresponds to memo[][1]
        ...etc
        */

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }
        return longestIncreasingSubsequenceUtil(arr, 0, -1, memo);
    }
    public static void main(String[] args) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }; 
        System.out.println("Length of lis is " + longestIncreasingSubsequence(arr) + "." ); 
    }
}