import java.util.ArrayDeque;
import java.util.Deque;

/*
https://leetcode.com/problems/decode-string/discuss/87534/Simple-Java-Solution-using-Stack
https://techdevguide.withgoogle.com/resources/compress-decompression/?types=coding-interview-question#!

- Given an encoded string, return its decoded string.
- The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being 
    repeated exactly k times. Note that k is guaranteed to be a positive integer.
- You may assume that the input string is always valid. No extra white spaces, square brackets are 
    well-formed etc.
- Furthermore, you may assume that the original data does not contain any digits and that digits are 
    only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
- Example 1:
    Input: s = "3[a]2[bc]"
    Output: "aaabcbc"
- Example 2:
    Input: s = "3[a2[c]]"
    Output: "accaccacc"
- Example 3:
    Input: s = "2[abc]3[cd]ef"
    Output: "abcabccdcdcdef"
- Example 4:
    Input: s = "abc3[cd]xyz"
    Output: "abccdcdcdxyz"

*/
public class String_02_Decompression {
    public static String decompress(String compressedStr) {
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();

        StringBuilder currSb = new StringBuilder();
        int currNum = 0;

        for (char ch: compressedStr.toCharArray()) {
            if (isNumber(ch)) {
                // get the number and store it in currNum
                currNum = 10 * currNum + (ch - '0');
            } else if (ch == '[') {
                // start of new pattern: save the old ones
                intStack.push(currNum);
                stringStack.push(currSb);
                currSb = new StringBuilder();
                currNum = 0;
            } else if (ch == ']') {
                // end of a part: combine the contents of string stack (prefix) with currSb (repeat currSb n times).
                StringBuilder tempSb = currSb;
                currSb = stringStack.pop(); // prefix
                int repetitions = intStack.pop();
                for (int i = 0; i < repetitions; i++) {
                    currSb.append(tempSb);
                }
            } else {
                // get the character and store it in currSb
                currSb.append(ch);
            }
        }
        return currSb.toString();
    }
    private static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
    public static void main(String[] args) {
        String compressedStr1 = "3[a]2[bc]";
        System.out.println("Decompressing " + compressedStr1 + ": " + decompress(compressedStr1));
        
        String compressedStr2 = "3[a2[c]]";
        System.out.println("Decompressing " + compressedStr2 + ": " + decompress(compressedStr2));
        
        String compressedStr3 = "2[abc]3[cd]ef";
        System.out.println("Decompressing " + compressedStr3 + ": " + decompress(compressedStr3));
        
        String compressedStr4 = "abc3[cd]xyz";
        System.out.println("Decompressing " + compressedStr4 + ": " + decompress(compressedStr4));
    }
}
