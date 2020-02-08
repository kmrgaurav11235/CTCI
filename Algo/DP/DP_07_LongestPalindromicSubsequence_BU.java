/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
Given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic 
subsequence in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, 
but not the longest ones.
*/
class DP_07_LongestPalindromicSubsequence_BU {
    static int longestPalindromicSubsequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int [][] memo = new int[n][n];
        /* 
        No need to declare size n + 1 as:
            1. i goes from n - 1 to 0, and in the formula, i + 1 is present. So, i == -1 will never
                happen. Also, we a barrier of i >= j, where the formula is not used a and memo is 
                set to a constant. So, i == n will never happen.
            2) j goes from 0 to n - 1, and in the formula, j - 1 is present. So, j == n will never 
                happen. Also, we a barrier of j <= i, where the formula is not used a and memo is 
                set to a constant. So, j == -1 will never happen.
        */

        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    memo[i][j] = 0;
                } else if (i == j) {
                    // This is important to make sure that we don't count the same char twice
                    memo[i][j] = 1;
                } else if (str.charAt(i) == str.charAt(j)) {
                    // Choice 1: Choose both characters if they are the same
                    // Increase length of subsequence by 2 selecting both
                    memo[i][j] = 2 + memo[i + 1][j - 1];
                } else {
                    // Choice 2: Move left forward
                    // Choice 3: Move right backward
                    memo[i][j] = Math.max(memo[i + 1][j], memo[i][j - 1]);
                }
            }
        }
        return memo[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Length of longest Palindromic Sequence in \"ELRMEENMET\": " 
            + longestPalindromicSubsequence("ELRMEENMET"));
        System.out.println("Length of longest Palindromic Sequence in \"GEEKSFORGEEKS\": " 
            + longestPalindromicSubsequence("GEEKSFORGEEKS"));
        System.out.println("Length of longest Palindromic Sequence in \"abacdfgdcaba\": " 
            + longestPalindromicSubsequence("abacdfgdcaba"));
    }
}