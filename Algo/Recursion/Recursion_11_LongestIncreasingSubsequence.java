/*
https://leetcode.com/problems/longest-increasing-subsequence/solution/
*/
class Recursion_11_LongestIncreasingSubsequence {
    private static int longestIncreasingSubsequenceUtil(int [] arr, int index, int prevElement) {
        if (index == arr.length) {
            return 0;
        }
        int includingCurrentIndex = 0, excludingCurrentIndex = 0;
        if (prevElement < arr[index]) {
            includingCurrentIndex = 1 + longestIncreasingSubsequenceUtil(arr, index + 1, arr[index]);
        }
        excludingCurrentIndex = longestIncreasingSubsequenceUtil(arr, index + 1, prevElement);
        return Math.max(includingCurrentIndex, excludingCurrentIndex);
    }
    static int longestIncreasingSubsequence(int [] arr) {
        return longestIncreasingSubsequenceUtil(arr, 0, Integer.MIN_VALUE);
    }
    public static void main(String[] args) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }; 
        System.out.println("Length of lis is " + longestIncreasingSubsequence(arr) + "\n" ); 
    }
}