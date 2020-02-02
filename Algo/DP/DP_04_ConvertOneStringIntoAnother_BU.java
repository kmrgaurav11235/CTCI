import java.util.Arrays;

/*
https://www.geeksforgeeks.org/edit-distance-dp-5/
Given two strings s1 and s2, convert s2 into s1 by deleting, inserting and replacing
characters. The number of edit operations should be minimized.

1. Base Case: If the index of one of the strings (s1 or s2) has reached till end, while 
    the other has not, it will require "num of chars left in the other string" number of 
    operations (insert/delete), so just return that number.
2. If the char at current index in both is the same, just increase both indices and recurse.
3. If the char at current index are different, we have 3 choices. Make all 3 choices by 
    adding 1 (representing the number of operation performed) and select the one that returns 
    the minimum number:
    i) Insert a char in s2 - Inserting a char in s2 will match the current char in s1 with the
        new char in s2. So, increase the index in s1.
    ii) Delete a char from s2 - Deleting a char from s2 will match the current char in s1 with
        the next char in s2. So, increase the index in s1.
    iii) Replace a char in s2 - Replacing a char in s2 will match chars both in s1 and s2. So, 
        increase the index in both.

Time Complexity: O(m x n)
Auxiliary Space: O(m x n)
Space Complex Solution: In the above-given method we require O(m x n) space. This will not be suitable 
if the length of strings is greater than 2000 as it can only create 2D array of 2000 x 2000. To fill a 
row in DP array we require only one row the upper row. For example, if we are filling the i = 10 rows 
in DP array we require only values of 9th row. So we simply create a DP array of 2 x str1 length. We
can do this only for bottom-up, not top-down.
*/
class DP_04_ConvertOneStringIntoAnother_BU {
    static int findMinOperations(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int [][] memo = new int [m + 1][n + 1];
        // We need extra row and column for the condition where one of the string has exhausted while other
        // is still being iterated - the base cases

        // Filling the Base cases
        for (int i = 0; i <= m; i++) {
            memo[i][n] = m - i;            
        }
        for (int i = 0; i <= n; i++) {
            memo[m][i] = n - i;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    memo[i][j] = memo[i + 1][j + 1];
                } else {
                    memo[i][j] = 1 + Math.min(memo[i + 1][j + 1], Math.min(memo[i + 1][j], memo[i][j +1]));
                }
            }
        }

        return memo[0][0];
    }
    public static void main(String[] args) {
		System.out.println("Min Operations (catch, carch) = " + findMinOperations("catch", "carch"));
		System.out.println("Min Operations (table, tbres) = " + findMinOperations("table", "tbres"));
		System.out.println("Min Operations (table, tgable) = " + findMinOperations("table", "tgable"));
		System.out.println("Min Operations (sunday, saturday) = " + findMinOperations("sunday", "saturday"));
	}
}