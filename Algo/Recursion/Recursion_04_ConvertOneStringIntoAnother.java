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
*/
class Recursion_04_ConvertOneStringIntoAnother {
    static int min (int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }
    static int findMinOperations(String s1, int index1, String s2, int index2) {
        if (s1.length() == index1) {
            return s2.length() - index2;
        } else if (s2.length() == index2) {
            return s1.length() - index1;
        } else if (s1.charAt(index1) == s2.charAt(index2)) {
            return findMinOperations(s1, index1 + 1, s2, index2 + 1);
        }

        // Insert a char in s2
        int insert = 1 + findMinOperations(s1, index1 + 1, s2, index2);

        // Delete a char from s2
        int delete = 1 + findMinOperations(s1, index1, s2, index2 + 1);

        // Replace a char in s2
        int replace = 1 + findMinOperations(s1, index1 + 1, s2, index2 + 1);

        return min(insert, delete, replace);
    }
    public static void main(String[] args) {
		System.out.println("Min Operations (catch, carch) = " + findMinOperations("catch", 0, "carch", 0));
		System.out.println("Min Operations (table, tbres) = " + findMinOperations("table", 0, "tbres", 0));
		System.out.println("Min Operations (table, tgable) = " + findMinOperations("table", 0, "tgable", 0));

	}
}