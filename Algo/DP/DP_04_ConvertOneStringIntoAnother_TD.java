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
class DP_04_ConvertOneStringIntoAnother_TD {
    private static int findMinOperationsUtil(String s1, String s2, int index1, int index2, int [][] memo) {
        if (index1 == s1.length()) {
            return s2.length() - index2;
            // You can also store this in memo. Though you will have to add an extra row and column.
            // In fact, we will do this in bottom up.
        } else if (index2 == s2.length()) {
            return s1.length() - index1;
        } else if (memo[index1][index2] == -1) {
            // Choice 1: equate
            if (s1.charAt(index1) == s2.charAt(index2)) {
                memo[index1][index2] = findMinOperationsUtil(s1, s2, index1 + 1, index2 + 1, memo);
            } else {
                // Choice 2: replace
                int replace = 1 + findMinOperationsUtil(s1, s2, index1 + 1, index2 + 1, memo);

                // Choice 3: insert
                int insert = 1 + findMinOperationsUtil(s1, s2, index1, index2 + 1, memo);

                // Choice 4: delete
                int delete = 1 + findMinOperationsUtil(s1, s2, index1 + 1, index2, memo);

                memo[index1][index2] = Math.min(replace, Math.min(insert, delete));
            }
        }
        return memo[index1][index2];
    }
    static int findMinOperations(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int [][] memo = new int [m][n];
        for (int [] a: memo) {
            Arrays.fill(a, -1);
        }

        return findMinOperationsUtil(s1, s2, 0, 0, memo);
    }
    public static void main(String[] args) {
		System.out.println("Min Operations (catch, carch) = " + findMinOperations("catch", "carch"));
		System.out.println("Min Operations (table, tbres) = " + findMinOperations("table", "tbres"));
		System.out.println("Min Operations (table, tgable) = " + findMinOperations("table", "tgable"));
		System.out.println("Min Operations (sunday, saturday) = " + findMinOperations("sunday", "saturday"));
	}
}