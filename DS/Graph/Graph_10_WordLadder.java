import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/

- Given a dictionary, and two words 'start' and 'target' (both of same length). Find the smallest chain 
    from 'start' to 'target' if it exists, such that adjacent words in the chain only differ by one 
    character and each word in the chain is a valid word i.e., it exists in the given dictionary. 
- It may be assumed that the 'target' word exists in dictionary and length of all dictionary words is 
    same. 
- Example: 
- Input: 
    Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
    start = TOON
    target = PLEA
    
    Output:
    OON - POON - POIN - POIE - PLIE - PLEE - PLEA

- Input: 
    Dictionary = {ABCD, EBAD, EBCD, XYZA}
    Start = ABCV
    End = EBAD

    Output:
    ABCV - ABCD - EBCD - EBAD

- Solution: The idea is to use BFS. 
    1) Start from the given start word.
    2) Push the word in the queue
    3) Run a loop until the queue is empty
    4) Traverse all words that adjacent (differ by one character) to it and push the word in a queue 
        (for BFS).
    5) Keep doing so until we find the target word or we have traversed all words.
- Each word in our graph branches to all words in dictionary that are one edit away. We can use a 
    "Backtrack Map" to keep track of these. 
    If backTrackMap.get(v) = w, then you know that you edited w to get v.
- Time complexity: O((n ^ 2) * m), 
    where m is the number of entries originally in the dictionary and n is the size of the string. 
- Space Complexity: O(m * n)
*/
public class Graph_10_WordLadder {
    private static Set<String> getOneLetterEdits(String str, Set<String> dict) {
        Set<String> oneLetterEditSet = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            char [] strArray = str.toCharArray();
            char currChar = strArray[i];

            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != currChar) {
                    strArray[i] = ch;
                    String oneLetterEditStr = String.valueOf(strArray);
                    if (dict.contains(oneLetterEditStr)) {
                        oneLetterEditSet.add(oneLetterEditStr);
                    }
                }
            }
        }

        return oneLetterEditSet; 
    }

    private static List<String> buildWordLadder(String target, Map<String, String> backTrackMap) {
        LinkedList<String> wordLadder = new LinkedList<>();
        String currWord = target;

        while (currWord != null) {
            wordLadder.addFirst(currWord);
            currWord = backTrackMap.get(currWord);
        }
        return wordLadder;
    }

    static List<String> findShortestChain(String start, String target, Set<String> dict) {
        Deque<String> bfsQueue = new LinkedList<>();
        Set<String> visitedSet = new HashSet<>();
        Map<String, String> backTrackMap = new HashMap<>();

        bfsQueue.offer(start);

        while (!bfsQueue.isEmpty()) {
            String currString = bfsQueue.poll();
            if (visitedSet.contains(currString)) {
                // Node was already visited
                continue;
            } else {
                visitedSet.add(currString);
                for (String newString : getOneLetterEdits(currString, dict)) {
                    if (newString.equals(target)) {
                        // build word ladder and return it
                        backTrackMap.put(newString, currString);
                        return buildWordLadder(target, backTrackMap);
                    } else if (!visitedSet.contains(newString)) {
                        backTrackMap.put(newString, currString);
                        bfsQueue.offer(newString);
                    }
                } // end of for-each loop
            } // end of if-else
        } // end of while
        return new LinkedList<>();
    }

    public static void main(String[] args) {
        Set<String> dict = Set.of("poon", "plee", "same", "poie", "plie", "poin", "plea");
        String start = "toon"; 
        String target = "plea"; 

        System.out.println("Shortest chain is: " + findShortestChain(start, target, dict)); 
    }
}
