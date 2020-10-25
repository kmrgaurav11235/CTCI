/*
https://leetcode.com/problems/regular-expression-matching/discuss/191830/Java-DP-solution-beats-100-with-explanation

- Given an input string (str) and a pattern (pattern), implement regular expression matching with support 
    for '.' and '*' where: 
    1) '.' Matches any single character.​​​​
    2) '*' Matches zero or more of the preceding element.
- The matching should cover the entire input string (not partial).
- Example 1:
    Input: str = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

- Example 2:
    Input: str = "aa", pattern = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it 
        becomes "aa".

- Example 3:
    Input: str = "ab", pattern = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".

- Example 4:
    Input: str = "aab", pattern = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

- Example 5:
    Input: str = "mississippi", pattern = "mis*is*p*."
    Output: false

- Solution:
- Consider following example:
    str = 'aab', pattern = 'c*a*b'

          c * a * b 
        0 1 2 3 4 5
      0 t
    a 1
    a 2
    b 3

- t = true, f = false
- memo[i][j] denotes if str.substring(0,i) is valid for pattern.substring(0,j). For example memo[0][0] == true 
    (denoted by t in the matrix) because when str and pattern are both empty they match. So if we somehow base 
    memo[i+1][j+1] on previous memo[i][j]'s then the result will be memo[str.length()][pattern.length()]
- So what about the first column? For empty pattern = "", only thing that is valid is an empty string str = "". 
    That is why our memo[0][0] is already true. That means rest of memo[i][0] is false.

          c * a * b 
        0 1 2 3 4 5
      0 t
    a 1 f
    a 2 f
    b 3 f
- What about the first row? In other words which pattern matches empty string str = ""? The answer is: either an 
    empty pattern = "" or a pattern that can represent an empty string such as pattern = "a*", pattern = "z*" or 
    more interestingly a combination of them as in pattern = "a*b*c*". 
- This for-loop is used to populate memo[0][j]. Note how it uses previous states by checking memo[0][j-2]:

        for (int j=2; j<=pattern.length(); j++) {
            memo[0][j] = pattern.charAt(j-1) == '*' && memo[0][j-2]; 
        }

- At this stage our matrix has become as follows: Notice memo[0][2] and memo[0][4] are both true because pattern = 
    "c*" and pattern = "c*a*" can both match an empty string.
          c * a * b 
        0 1 2 3 4 5
      0 t f t f t f
    a 1 f
    a 2 f
    b 3 f

- So now we can start our main iteration. It is basically the same. We will iterate all possible str lengths (i) for 
    all possible pattern lengths (j) and we will try to find a relation based on previous results. Turns out there are 
    two cases:

    1) (pattern.charAt(j-1) == str.charAt(i-1) || pattern.charAt(j-1) == '.') 
        If the current characters match or pattern has '.', then the result is determined by the previous state:
            memo[i][j] = memo[i-1][j-1]
        Don't be confused by the charAt(j-1) charAt(i-1) indexes using a '-1' offset that is because our memo array is 
        actually one index bigger than our string and pattern lengths to hold the initial state memo[0][0].
    2) if pattern.charAt(j-1) == '*' then :
        a) Either the pattern 'a*' can match with an empty string in str -- the result is memo[i][j] = memo[i][j-2] 
        b) Or (pattern.charAt(j-2) == str.charAt(i-1) || pattern.charAt(j-2) == '.') -- the current char of string 
            equals the char preceding '*' in pattern so the result is:
            memo[i][j] = memo[i-1][j]

- So here is the final state of matrix after we evaluate all elements:
          c * a * b 
        0 1 2 3 4 5
      0 t f t f t f
    a 1 f f f t t f
    a 2 f f f f t f
    b 3 f f f f f t
- Time and space complexity are O(m * n).
*/
public class DP_20_RegularExpressionMatching {
    static boolean isMatch(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();

        boolean [][] memo = new boolean[m + 1][n + 1];
        memo[0][0] = true;

        for (int i = 1; i <= m; i++) {
            memo[i][0] = false;
        }
        for (int i = 1; i <= n; i++) {
            if (pattern.charAt(i - 1) == '*') {
                memo[0][i] = memo[0][i - 2]; 
                // memo[0][i - 1] corresponds to the 'a' in 'a*'. So, we look one char before that
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pattern.charAt(j - 1) == str.charAt(i - 1) || pattern.charAt(j - 1) == '.') {
                    memo[i][j] = memo[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    memo[i][j] = memo[i][j - 2] || 
                        ( 
                            (pattern.charAt(j - 2) == str.charAt(i - 1) || pattern.charAt(j - 2) == '.') 
                                && memo[i - 1][j]
                        );
                }
            }
        }

        return memo[m][n];
    }
    public static void main(String[] args) {
        String str1 = "aa";
        String pattern1 = "a";

        System.out.println("Matching string: " + str1 + ", with pattern: " + pattern1 + " = " 
            + isMatch(str1, pattern1));
        
        String str2 = "aa";
        String pattern2 = "a*";

        System.out.println("Matching string: " + str2 + ", with pattern: " + pattern2 + " = " 
            + isMatch(str2, pattern2));
        
        
        String str3 = "ab";
        String pattern3 = ".*";

        System.out.println("Matching string: " + str3 + ", with pattern: " + pattern3 + " = " 
            + isMatch(str3, pattern3));
        
        
        String str4 = "aab";
        String pattern4 = "c*a*b";

        System.out.println("Matching string: " + str4 + ", with pattern: " + pattern4 + " = " 
            + isMatch(str4, pattern4));
        
        String str5 = "mississippi";
        String pattern5 = "mis*is*p*.";

        System.out.println("Matching string: " + str5 + ", with pattern: " + pattern5 + " = " 
            + isMatch(str5, pattern5));
    }
}