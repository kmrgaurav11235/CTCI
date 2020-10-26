/*
https://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/
https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
*/
public class DistinctSubsequences {
    static int distinctSubsequences(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        Integer[][] memo = new Integer[m][n];
        return distinctSubsequencesUtil(str1, str2, 0, 0, memo);
    }

    static int distinctSubsequencesUtil(String str1, String str2, int i1, int i2, Integer[][] memo) {
        if (i2 == str2.length()) {
            // We are at the end -- 1 config found
            return 1;
        } else if (i1 == str1.length()) {
            // No config found
            return 0;
        } else if (memo[i1][i2] != null) {
            return memo[i1][i2];
        }
        int totalConfigs = 0;
        if (str1.charAt(i1) == str2.charAt(i2)) {
            // Match the characters
            totalConfigs += distinctSubsequencesUtil(str1, str2, i1 + 1, i2 + 1, memo);
        }

        totalConfigs += distinctSubsequencesUtil(str1, str2, i1 + 1, i2, memo);
        memo[i1][i2] = totalConfigs;
        return memo[i1][i2];
    }

    public static void main(String[] args) {
        System.out.println(distinctSubsequences("rabbbit", "rabbit"));
        System.out.println(distinctSubsequences("banana", "ban"));
        System.out.println(distinctSubsequences("geeksforgeeks", "ge"));
        System.out.println(distinctSubsequences("babgbag", "bag"));
    }
}
