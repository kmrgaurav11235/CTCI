import java.util.HashMap;
import java.util.Map;

/*
https://www.geeksforgeeks.org/trie-insert-and-search/
- Trie is an efficient information reTrieval data structure. Using Trie, search complexities can be brought 
    to optimal limit (key length). If we store keys in binary search tree, a well balanced BST will need 
    time proportional to M * log N, where M is maximum string length and N is number of keys in tree. 
    Using Trie, we can search the key in O(M) time. However the penalty is on Trie storage requirements.
*/
class Trie_01_InsertAndSearch {
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    static class Trie {
        private final TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String str) {
            TrieNode p = root;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                TrieNode next = p.children.get(ch);
                if (next == null) {
                    next = new TrieNode();
                    p.children.put(ch, next);
                }
                p = next;
            }

            p.isEndOfWord = true;
        }

        public boolean search(String str) {
            TrieNode p = root;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                TrieNode next = p.children.get(ch);
                if (next == null) {
                    return false;
                }
                p = next;
            }
            return p.isEndOfWord;
        }
    }

    public static void main(String[] args) {
        String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

        // Construct Trie
        Trie trie = new Trie();

        // Insert in Trie
        for (int i = 0; i < keys.length; i++) {
            trie.insert(keys[i]);
        }

        // Search for different keys
        if (trie.search("the") == true) {
            System.out.println("the: is present in trie");
        } else {
            System.out.println("the: is not present in trie");
        }

        if (trie.search("these") == true) {
            System.out.println("these: is present in trie");
        } else {
            System.out.println("these: is not present in trie");
        }

        if (trie.search("their") == true) {
            System.out.println("their: is present in trie");
        } else {
            System.out.println("their: is not present in trie");
        }

        if (trie.search("thaw") == true) {
            System.out.println("thaw: is present in trie");
        } else {
            System.out.println("thaw: is not present in trie");
        }
    }
}