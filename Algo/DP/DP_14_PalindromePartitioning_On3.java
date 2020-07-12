/*
https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/

- Given a string, a partitioning of the string is a palindrome partitioning if every substring of the 
    partition is a palindrome. For example, "aba|b|bbabb|a|b|aba" is a palindrome partitioning of 
    "ababbbabbababa". 
- Determine the fewest cuts needed for palindrome partitioning of a given string. For example, minimum 
    3 cuts are needed for "ababbbabbababa". The three cuts are “a|babbbab|b|ababa”. If a string is a 
    palindrome, then minimum 0 cuts are needed. If a string of length n containing all different 
    characters, then minimum n-1 cuts are needed.
- This problem is a variation of Matrix Chain Multiplication problem. If the string is a palindrome, 
    then we simply return 0. Else, like the Matrix Chain Multiplication problem, we try making cuts at 
    all possible places, recursively calculate the cost for each cut and return the minimum value.
- Let the given string be str and minPalindromePartition() be the function that returns the fewest cuts 
    needed for palindrome partitioning, following is the optimal substructure property:

    // i is the starting index and j is the ending index. i must be passed as 0 and j as n-1
    minPalindromePartition(str, i, j) = 0 if i == j. // When string is of length 1.
    minPalindromePartition(str, i, j) = 0 if str[i..j] is palindrome.

    // If none of the above conditions is true, then minPalindromePartition(str, i, j) can be 
    // calculated recursively using the following formula:
    minPalindromePartition(str, i, j) = Min { 1 + minPalindromePartition(str, i, k) 
        + minPalindromePartition(str, k+1, j) } 
        where k varies from i to j-1
- Time Complexity: O(n ^ 3)
*/
public class DP_14_PalindromePartitioning_On3 {

    static int minPalindromePartition(String str) {
        int n = str.length();

        boolean[][] isPalindrome = new boolean[n][n]; 
        // true if substring str[i..j] is palindrome, else false

        int[][] memo = new int[n][n]; 
        // Minimum number of cuts needed for palindrome partitioning of str[i..j]. Note that memo[i][j] 
        // is 0 if isPalindrome[i][j] is true

        // Initialization of isPalindrome
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
            // memo elements are all 0s by default
        }

        // Filling up isPalindrome and memo. Building the solution in bottom up manner by considering all 
        // substrings of length starting from 2 to n. The loop structure is same as Matrx Chain Multiplication
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                // i -> starting index, j -> ending index
                if ((str.charAt(i) == str.charAt(j)) && (l == 2 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
                if (isPalindrome[i][j]) {
                    // No cuts needed if it is already a palindrome.
                    memo[i][j] = 0;
                } else {
                    // If not a palindrome, get the minimum value
                    memo[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int numPartitions = 1 + memo[i][k] + memo[k + 1][j];
                        if (memo[i][j] > numPartitions) {
                            memo[i][j] = numPartitions;
                        }
                    }
                }
            }
        }
        return memo[0][n - 1];
    }

    public static void main(String[] args) {
        String str0 = "ababbbabbababa";
        System.out.println("Min cuts needed for Palindrome Partitioning of " + str0 + " is: " + minPalindromePartition(str0));

        String str1 = "aab"; 
        System.out.println("Min cuts needed for Palindrome Partitioning of " + str1 + " is: " + minPalindromePartition(str1));
    }
}