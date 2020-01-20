/*
Given two sequences, find the length of longest subsequence present in both of them. A subsequence is 
a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, 
“abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
*/
class Recursion_06_LongestCommonSubsequence {
    static int longestCommonSubsequence(String s1, int index1, String s2, int index2) {
        if (index1 == s1.length() || index2 == s2.length()) {
            return 0;
        } 
        // Choice 1: Increase the index of 1st string
        int firstIndexIncrease = longestCommonSubsequence(s1, index1 + 1, s2, index2);

        // Choice 2: Increase the index of 2nd string
        int secondIndexIncrease = longestCommonSubsequence(s1, index1, s2, index2 + 1);
        int bothIndicesIncrease = 0;
        if (s1.charAt(index1) == s2.charAt(index2)) {
            // Choice 3: Increase indices of both strings if you have the same char 
            bothIndicesIncrease = 1 + longestCommonSubsequence(s1, index1 + 1, s2, index2 + 1);
        }
        return Math.max(firstIndexIncrease, Math.max(secondIndexIncrease, bothIndicesIncrease));
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("String 1: " + s1 + "\nString 2: " + s2);
        System.out.println("Length of LCS is: " + longestCommonSubsequence(s1, 0, s2, 0));
    }
}