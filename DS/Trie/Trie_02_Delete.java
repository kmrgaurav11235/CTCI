import java.util.HashMap;
import java.util.Map;

/*
https://www.geeksforgeeks.org/trie-delete/
- During delete operation we delete the key in bottom up manner using recursion. 
- The recursive method returns a boolean: true if the parent recursion call should delete the mapping 
    to the child TrieNode.
- The following are possible conditions when deleting key from trie,
		1) Some other word's prefix is same as Prefix of this word (BCDE, BCKG)
		2) We are at last character of this word and This word is a Prefix of some other word (BCDE, BCDEFG)
		3) Some other word is a Prefix of this word (BCDE, BC)
		4) No one is dependent on this Word (BCDE, only this word is in trie)
*/
class Trie_02_Delete {
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

        private boolean deleteUtil(String str, TrieNode node, int index) {
            if (index < str.length()) {
                // Not reached the end yet
                char ch = str.charAt(index);
                TrieNode next = node.children.get(ch);

                if (next == null) {
                    System.out.println("Deletion failed! " + str + " not found in Trie.");
                    return false;
                }

                boolean shouldChildBeDeleted = deleteUtil(str, next, index + 1);
                if (shouldChildBeDeleted) {
                    node.children.remove(ch);
                }

                if (node.isEndOfWord || node.children.size() > 0) {
                    // A word ends here or there are still children. Don't delete this node.
                    return false;
                } else {
                    // No children left. Delete this node.
                    return true;
                }

            } else {
                // We have already reached the end of str
                if (!node.isEndOfWord) {
                    System.out.println("Deletion failed! " + str + " not found in Trie.");
                    return false;
                } else if (node.children.isEmpty()) {
                    // Word ends here and there is no data in children.
                    // We will delete the entire node.
                    return true;
                } else {
                    // Word ends here and there is some data in children.
                    // We will delete just the isEndOfWord marker.
                    node.isEndOfWord = false;
                    return false;
                }
            }
        }

        public void delete(String str) {
            deleteUtil(str, root, 0);
        }
    }

    static void searchAndDisplay(Trie trie, String str) {
        if (trie.search(str) == true) {
            System.out.println(str + ": is present in trie");
        } else {
            System.out.println(str + ": is not present in trie");
        }
    }

    public static void main(String[] args) {
        Trie trie1 = new Trie();

        System.out.println("***********  CASE 1  ***********");
        trie1.insert("bcde");
        trie1.insert("bckg");

        System.out.println("Before Deletion...");
        searchAndDisplay(trie1, "bcde");
        searchAndDisplay(trie1, "bckg");

        trie1.delete("bcde");

        System.out.println("After Deleting bcde...");
        searchAndDisplay(trie1, "bcde");
        searchAndDisplay(trie1, "bckg");

        System.out.println("***********  CASE 2  ***********");
        Trie trie2 = new Trie();
        trie2.insert("bcde");
        trie2.insert("bcdefg");

        System.out.println("Before Deletion...");
        searchAndDisplay(trie2, "bcde");
        searchAndDisplay(trie2, "bcdefg");

        trie2.delete("bcde");

        System.out.println("After Deleting bcde...");
        searchAndDisplay(trie2, "bcde");
        searchAndDisplay(trie2, "bcdefg");

        System.out.println("***********  CASE 3  ***********");
        Trie trie3 = new Trie();
        trie3.insert("bcde");
        trie3.insert("bc");

        System.out.println("Before Deletion...");
        searchAndDisplay(trie3, "bcde");
        searchAndDisplay(trie3, "bc");

        trie3.delete("bcde");

        System.out.println("After Deleting bcde...");
        searchAndDisplay(trie3, "bcde");
        searchAndDisplay(trie3, "bc");
        searchAndDisplay(trie3, "b");

        System.out.println("***********  CASE 4  ***********");
        Trie trie4 = new Trie();
        trie4.insert("bcde");

        System.out.println("Before Deletion...");
        searchAndDisplay(trie4, "bcde");

        trie4.delete("bcde");

        System.out.println("After Deleting bcde...");
        searchAndDisplay(trie4, "bcde");
    }
}