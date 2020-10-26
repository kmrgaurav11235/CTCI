import java.util.Set;

/*
https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways

- Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine 
    if s can be segmented into a space-separated sequence of one or more dictionary words.

- Note:
    * The same word in the dictionary may be reused multiple times in the segmentation.
    * You may assume the dictionary does not contain duplicate words.

- Example 1:
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".

- Example 2:
    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.

- Example 3:
    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false

- The idea is similar to Rod cutting: we can divide the string into two parts. Then search one part in 
    dictionary while get the other part from already calculated memo[] array.
- Unlike Rod-cutting, we take the suffix (instead of the prefix) and search it in dictionary. This allows
    us to look for prefix in the memo[] array.
- We move from left to right, so smaller indices of memo will get filled-up first.
- memo[i] stands for whether sub-array[0, i[ can be segmented into words from the dictionary. Note 
    that the first index is inclusive, whereas the 2nd index is exclusive. 
- So memo[0] means whether sub-array[0, 0[ (which is an empty string) can be segmented, and of course 
    the answer is yes.
*/
public class DP_13_WorkBreak_Simple {
    static boolean wordBreak(String str, Set<String> dict) {
        int n = str.length();
        if (n == 0) {
            return true;
        }

        boolean [] memo = new boolean[n + 1];
        memo[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String subString = str.substring(j, i);
                if (memo[j] && dict.contains(subString)) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }
    public static void main(String[] args) {
        Set<String> dict = Set.of("mobile","samsung","sam","sung", "man","mango","icecream","and", 
            "go","i","like","ice","cream");
        
        String str1 = "ilikesamsung";
        System.out.println(str1 + ": Is word break possible: " + wordBreak(str1, dict));

        String str2 = "iiiiiiii";
        System.out.println(str2 + ": Is word break possible: " + wordBreak(str2, dict));

        String str3 = "";
        System.out.println(str3 + ": Is word break possible: " + wordBreak(str3, dict));

        String str4 = "ilikelikeimangoiii";
        System.out.println(str4 + ": Is word break possible: " + wordBreak(str4, dict));

        String str5 = "samsungandmango";
        System.out.println(str5 + ": Is word break possible: " + wordBreak(str5, dict));

        String str6 = "samsungandmangok";
        System.out.println(str6 + ": Is word break possible: " + wordBreak(str6, dict));
    }
}
