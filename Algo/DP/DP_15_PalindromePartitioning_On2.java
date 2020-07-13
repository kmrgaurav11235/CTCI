/*
https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/
- An optimization to the previous approach: In the previous approach, we can calculate the minimum cut 
    while finding all palindromic substring. If we find all palindromic substring 1st and then we calculate 
    minimum cut, time complexity will reduce to O(n ^ 2).
- The isPalindrome boolean array is filled up same as the previous approach.
- The change is to memo array happens in a manner similar to the efficient version of the longest increasing 
    subsequence problem. We realize memo[0..n-1] can be calculated by using memo[0..i], where 0 <= i < n
- Time Complexity: O(n ^ 2)
*/
class DP_15_PalindromePartitioning_On2 {

    static int minPalindromePartition(String str) {
        int n = str.length();

        boolean[][] isPalindrome = new boolean[n][n]; 
        // true if substring str[i..j] is palindrome, else false

        // Initialization of isPalindrome
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        // Filling up isPalindrome. Building the solution in bottom up manner by considering all substrings 
        // of length starting from 2 to n. The loop structure is same as Matrx Chain Multiplication
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                // i -> starting index, j -> ending index
                if ((str.charAt(i) == str.charAt(j)) && (l == 2 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] memo = new int[n]; 
        // Minimum number of cuts needed for palindrome partitioning of str[0..i]. As the 1st index is always
        // 0, we don't need a 2D-array
        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                // No cuts needed if it is already a palindrome.
                memo[i] = 0;
            } else {
                memo[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    // j -> starting index, i -> ending index
                    if (isPalindrome[j + 1][i]) {
                        // str[j + 1..i] is palindome -> no cuts needed
                        // 1 cut needed between indices j and j + 1
                        // memo[j] cuts needed between indices 0 and j
                        memo[i] = Math.min(memo[i], 1 + memo[j]);
                    }
                }
            }
        }

        return memo[n - 1];
    }

    public static void main(String[] args) {
        String str0 = "ababbbabbababa";
        System.out.println("Min cuts needed for Palindrome Partitioning of " + str0 + " is: " + minPalindromePartition(str0));

        String str1 = "aab"; 
        System.out.println("Min cuts needed for Palindrome Partitioning of " + str1 + " is: " + minPalindromePartition(str1));
    }
}