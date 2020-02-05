/*
https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
Given two sequences, find the length of longest subsequence present in both of them. A subsequence is 
a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, 
“abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
*/
class DP_06_LongestCommonSubsequence_BU {
    static int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        Integer [][] memo = new Integer [m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            // When the second string has ended
            memo[i][n] = 0;
        }
        for (int j = 0; j <= n; j++) {
            // When the first string has ended
            memo[m][j] = 0;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    // Choice 1: Increase indices of both strings if you have the same char 
                    memo[i][j] = 1 + memo[i + 1][j + 1];
                } else {
                    // Choice 2: Increase the index of 1st string
                    // Choice 3: Increase the index of 2nd string
                    memo[i][j] = Math.max(memo[i + 1][j], memo[i][j + 1]);    
                }
            }
        }
        return memo[0][0];
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("String 1: " + s1 + "\nString 2: " + s2);
        System.out.println("Length of LCS is: " + longestCommonSubsequence(s1, s2));
    }
}