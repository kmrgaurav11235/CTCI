import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/group-anagrams/discuss/19176/Share-my-short-JAVA-solution
https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/

- Given an array of words, print all anagrams together. For example, if the given array is ["cat", "dog", 
    "tac", "god", "act"], then output may be [["cat", "tac", "act"], ["dog", "god"]].
- To check if two strings are anagrams, we have two options:
    1) Sort the strings and check if they are the same. Time: O(n log n).
    2) Count the frequency of distinct character. Time: O(n), with some extra space.

- Approach 1: 
    * Here, we first sort each string. Then we create a Map, use sorted word as key and then put original 
        word in the value list. The value of the map will be a list containing all the words which have 
        same word after sorting.
    * Lastly, we will print all values from the hashmap where size of values will be greater than 1.
    * Time Complexity: Let there be 'n' words and each word has maximum 'm' characters. The upper bound is 
        O(n * m * log m + m * n * log n).
    * Step 2 takes O(n * m log m) time. Sorting a word takes maximum O(m * log m) time. So sorting n words 
        takes O(n * m * log m) time. 
    * Step 3 takes O(m * n * log n). Sorting array of words takes n Log n comparisons. A comparison may take 
        maximum O(m) time. So time to sort array of words will be O(m * n * log n).

- Approach 2:
    * We don't actually need to sort all the Strings. We can instead use an int[26] as bucket to count the 
        frequency instead of sorting. 
    * This can reduce the O(n log n) to O(n) when calculating the key.

*/
public class String_03_GroupAnagrams {
    static List<List<String>> groupAnagramsUsingSort(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String string : strings) {
            // Sort string
            char [] charArray = string.toLowerCase().toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);

            // Store in map
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new LinkedList<>());
            }
            map.get(sortedStr).add(string);
        }
        return new ArrayList<>(map.values());
    }

    static List<List<String>> groupAnagramsUsingFrequency(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String string : strings) {
            char [] charArray = string.toLowerCase().toCharArray();
            char [] frequency = new char[26];
            for (char ch : charArray) {
                frequency[ch - 'a']++;
            }

            String mapKey = String.valueOf(frequency);
            if (!map.containsKey(mapKey)) {
                map.put(mapKey, new LinkedList<>());
            }
            map.get(mapKey).add(string);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String [] strings1 = { "Cat", "dog", "Tac", "god", "act" };
        List<List<String>> anagrams1 = groupAnagramsUsingSort(strings1);
        System.out.println(anagrams1);

        String [] strings2 = { "Cat", "dog", "Tac", "god", "act" };
        List<List<String>> anagrams2 = groupAnagramsUsingFrequency(strings2);
        System.out.println(anagrams2);
    }
}