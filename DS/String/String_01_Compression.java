/*
https://leetcode.com/problems/string-compression/discuss/92559/Simple-Easy-to-Understand-Java-solution

- Given an array of characters chars, compress it using the following algorithm:
- Begin with an empty string s. For each group of consecutive repeating characters in chars:
    * If the group's length is 1, append the character to s.
    * Otherwise, append the character followed by the group's length.
- The compressed string s should not be returned separately, but instead be stored in the input character array 
    chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
- After you are done modifying the input array, return the new length of the array.
- Examples:
    Input: chars = ["a","a","b","b","c","c","c"]
    Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

    Input: chars = ["a"]
    Output: Return 1, and the first character of the input array should be: ["a"]

    Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
    Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

*/
public class String_01_Compression {
    private static int compress(char[] chArray, int size) {
        int compressedIndex = 0, originalIndex = 0;

        while (originalIndex < size) {
            char currChar = chArray[originalIndex++];
            int currCount = 1;
            while (originalIndex < size && currChar == chArray[originalIndex]) {
                // Count the repetitions of 'currChar'
                originalIndex++;
                currCount++;
            }

            // Add the character
            chArray[compressedIndex++] = currChar;
            if (currCount > 1) {
                // Add the number after the character
                for (char c : String.valueOf(currCount).toCharArray()) {
                    chArray[compressedIndex++] = c;
                }
            } 
        }
        
        return compressedIndex;
    }
    public static void main(String[] args) {
        String str1 = "aabbccc";
        char [] chArray1 = str1.toCharArray();
        int len1 = compress(chArray1, chArray1.length);
        System.out.println("Compressing " + str1 + " - " + getString(chArray1, len1));
        
        String str2 = "a";
        char [] chArray2 = str2.toCharArray();
        int len2 = compress(chArray2, chArray2.length);
        System.out.println("Compressing " + str2 + " - " + getString(chArray2, len2));
        
        String str3 = "abbbbbbbbbbbb";
        char [] chArray3 = str3.toCharArray();
        int len3 = compress(chArray3, chArray3.length);
        System.out.println("Compressing " + str3 + " - " + getString(chArray3, len3));
    }

    private static String getString(char[] chArray, int size) {
        StringBuilder sb = new StringBuilder("Compressed String: ");
        for (int i = 0; i < size; i++) {
            sb.append(chArray[i]);
        }
        return sb.toString();
    }
    
}
