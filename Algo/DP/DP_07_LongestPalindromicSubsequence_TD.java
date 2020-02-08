/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
Given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic 
subsequence in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, 
but not the longest ones.
*/
class DP_07_LongestPalindromicSubsequence_TD {
    static int longestPalindromicSubsequenceUtil(String str, int left, int right, Integer [][] memo) {
        if (left > right) {
            return 0;
        } else if (memo[left][right] != null) {
            return memo[left][right];
        } else if (left == right) {
            // This is important to make sure that we don't count the same char twice
            memo[left][right] = 1;
        } else if (str.charAt(left) == str.charAt(right)) {
            // Choice 1: Choose both characters if they are the same
            // Increase length of subsequence by 2 selecting both
            memo[left][right] = 2 + longestPalindromicSubsequenceUtil(str, left + 1, right - 1, memo);
        } else {
            // Choice 2: Move left forward
            int leftForward = longestPalindromicSubsequenceUtil(str, left + 1, right, memo);
    
            // Choice 3: Move right backward
            int rightBackward = longestPalindromicSubsequenceUtil(str, left, right - 1, memo);
            memo[left][right] = Math.max(leftForward, rightBackward);
        }
        return memo[left][right];        
    }

    static int longestPalindromicSubsequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        Integer [][] memo = new Integer[n][n];
        return longestPalindromicSubsequenceUtil(str, 0, str.length() - 1, memo);
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