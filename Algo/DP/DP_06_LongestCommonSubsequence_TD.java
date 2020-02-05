/*
https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
Given two sequences, find the length of longest subsequence present in both of them. A subsequence is 
a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, 
“abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
*/
class DP_06_LongestCommonSubsequence_TD {
    static int longestCommonSubsequence(String s1, String s2) {
        Integer [][] memo = new Integer [s1.length()][s2.length()];
        return longestCommonSubsequenceUtil(s1, 0, s2, 0, memo);
    }
    private static int longestCommonSubsequenceUtil(String s1, int m, String s2, int n, Integer [][] memo) {
        if (m == s1.length() || n == s2.length()) {
            return 0;
        } else if (memo[m][n] == null) {
            if (s1.charAt(m) == s2.charAt(n)) {
                // Choice 1: Increase indices of both strings if you have the same char 
                memo[m][n] = 1 + longestCommonSubsequenceUtil(s1, m + 1, s2, n + 1, memo);
            } else {
                // Choice 2: Increase the index of 1st string
                int firstIndexIncrease = longestCommonSubsequenceUtil(s1, m + 1, s2, n, memo);
                // Choice 3: Increase the index of 2nd string
                int secondIndexIncrease = longestCommonSubsequenceUtil(s1, m, s2, n + 1, memo);
                memo[m][n] = Math.max(firstIndexIncrease, secondIndexIncrease);    
            }
        }
        return memo[m][n];
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("String 1: " + s1 + "\nString 2: " + s2);
        System.out.println("Length of LCS is: " + longestCommonSubsequence(s1, s2));
    }
}