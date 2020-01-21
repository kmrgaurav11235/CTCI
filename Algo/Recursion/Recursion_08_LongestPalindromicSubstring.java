/*
If the given string is "forgeeksskeegfor", the output should be 10 ("geeksskeeg").
*/
class Recursion_08_LongestPalindromicSubstring {

    static int longestPalindromicSubstringUtil(String str, int left, int right) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            // This is necessary to make sure that we don't count the same char twice.
            return 1;
        }
        int sameChar = 0;
        if (str.charAt(left) == str.charAt(right)) {
            // Choice 1: Choose both characters if they are the same
            int internalLongestPalindromicSubstr = longestPalindromicSubstringUtil(str, left + 1, right - 1); 
            if (internalLongestPalindromicSubstr == right - left - 1) {
                // This choice is only valid if all the chars inside make up the substring.
                // Add 2 to include current 2 chars at positions left and right.
                sameChar = 2 + internalLongestPalindromicSubstr;
            }
        }

        // Choice 2: Move left forward
        int incrementLeftIndex = longestPalindromicSubstringUtil(str, left + 1, right);

        // Choice 3: Move right backward
        int decrementLeftIndex = longestPalindromicSubstringUtil(str, left, right - 1);

        return Math.max(sameChar, Math.max(incrementLeftIndex, decrementLeftIndex));
    }

    static int longestPalindromicSubstring(String str) {
        return longestPalindromicSubstringUtil(str, 0, str.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println("Length of longest Palindromic Substring in \"ABCDCBUA\": " 
            + longestPalindromicSubstring("ABCDCBUA"));
        System.out.println("Length of longest Palindromic Substring in \"FORGEEKSSKEEGFOR\": " 
            + longestPalindromicSubstring("FORGEEKSSKEEGFOR"));
    }
}