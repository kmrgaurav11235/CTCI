/*
https://leetcode.com/problems/longest-increasing-subsequence/solution/
The simplest approach is to try to find all increasing subsequences and then returning the maximum 
    length of longest increasing subsequence. In order to do this, we make use of a recursive function 
    which returns the length of the LIS possible from the current element onwards (including the current 
    element). Inside each function call, we consider two cases:
    
    1. The current element is larger than the previous element included in the LIS. In this case, we can 
        include the current element in the LIS. Thus, we find out the length of the LIS obtained by 
        including it. Further, we also find out the length of LIS possible by not including the current 
        element in the LIS. The value returned by the current function call is, thus, the maximum out of 
        the two lengths.
    2. The current element is smaller than the previous element included in the LIS. In this case, we can't 
        include the current element in the LIS. Thus, we find out only the length of the LIS possible by not 
        including the current element in the LIS, which is returned by the current function call.
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