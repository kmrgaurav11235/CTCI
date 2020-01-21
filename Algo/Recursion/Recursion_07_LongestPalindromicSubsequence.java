/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
Given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic 
subsequence in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, 
but not the longest ones.
*/
class Recursion_07_LongestPalindromicSubsequence {
    static int longestPalindromicSubsequenceUtil(String str, int left, int right) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            // This is important to make sure that we don't count the same char twice
            return 1;
        }
        int sameChar = 0;
        if (str.charAt(left) == str.charAt(right)) {
            // Choice 1: Choose both characters if they are the same
            sameChar = 2 + longestPalindromicSubsequenceUtil(str, left + 1, right - 1);
            // Increase length of subsequence by 2 select both
        }

        // Choice 2: Move left forward
        int leftForward = longestPalindromicSubsequenceUtil(str, left + 1, right);

        // Choice 3: Move right backward
        int rightBackward = longestPalindromicSubsequenceUtil(str, left, right - 1);
        return Math.max(sameChar, Math.max(leftForward, rightBackward));
    }

    static int longestPalindromicSubsequence(String str) {
        return longestPalindromicSubsequenceUtil(str, 0, str.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println("Length of longest Palindromic Sequence in \"ELRMEENMET\": " 
            + longestPalindromicSubsequence("ELRMEENMET"));
        System.out.println("Length of longest Palindromic Sequence in \"GEEKSFORGEEKS\": " 
            + longestPalindromicSubsequence("GEEKSFORGEEKS"));
    }
}