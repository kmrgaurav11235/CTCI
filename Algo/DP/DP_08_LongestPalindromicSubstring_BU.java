/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
Given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic 
subsequence in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, 
but not the longest ones.
*/
class DP_08_LongestPalindromicSubstring_BU {
    static int longestPalindromicSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int [][] memo = new int[n][n];

        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    memo[i][j] = 0;
                } else if (i == j) {
                    // This is important to make sure that we don't count the same char twice
                    memo[i][j] = 1;
                } else {
                    int sameChar = 0;
                    if (str.charAt(i) == str.charAt(j)) {
                        // Choice 1: Choose both characters if they are the same
                        // Add 2 to include current 2 chars at positions left and right.
                        int internalLongestPalindromicSubstr = memo[i + 1][j - 1];
                        if (internalLongestPalindromicSubstr == j - i - 1) {
                            // This choice is only valid if all the chars inside make up the substring.
                            sameChar = 2 + internalLongestPalindromicSubstr;
                        }
                    }
                    // Choice 2: Move left forward
                    // Choice 3: Move right backward
                    memo[i][j] = Math.max(sameChar, Math.max(memo[i + 1][j], memo[i][j - 1]));
                }
            }
        }
        return memo[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Length of longest Palindromic Substring in \"ABCDCBUA\": " 
            + longestPalindromicSubstring("ABCDCBUA"));
        System.out.println("Length of longest Palindromic Substring in \"FORGEEKSSKEEGFOR\": " 
            + longestPalindromicSubstring("FORGEEKSSKEEGFOR"));
        System.out.println("Length of longest Palindromic Substring in \"abacdfgdcaba\": " 
            + longestPalindromicSubstring("abacdfgdcaba"));
    }
}