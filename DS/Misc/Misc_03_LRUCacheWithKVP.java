/* 
Design a Key-Value pair cache similar to Redis.
https://www.journaldev.com/32688/lru-cache-implementation-in-java
https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)
*/
import java.util.HashMap;

class Misc_03_LRUCacheWithKVP {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> prev, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }

    // Linked List is created from Node. Structure: lru -> A <-> B <-> C <-> D <-> E <- mru
    static class LRUCache<K, V> {
        private int capacity;
        private int size;
        private Node<K, V> lru;
        private Node<K, V> mru;
        private HashMap<K, Node<K, V>> container;

        LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            lru = new Node<K, V>(null, null, null, null);
            mru = lru;
            container = new HashMap<>();
        }

        public void display() {
            System.out.print("LRU Cache: [lru -> ");
            Node<K, V> p = lru;
            while (p != null) {
                System.out.print("{ " + p.key + ": " + p.value + " } ");
                p = p.next;
            }
            System.out.println(" <- mru]");
        }

        public V get(K key) {
            Node<K, V> keyNode = container.get(key);
            if (keyNode == null) {
                // Key is not present in Cache: Cache miss
                return null;
            }
            if (keyNode.key.equals(mru.key)) {
                // Key is mru. No changes required in LinkedList
                return mru.value;
            }
            Node<K, V> prevNode = keyNode.prev;
            Node<K, V> nextNode = keyNode.next;

            if (keyNode.key.equals(lru.key)) {
                // Key is in lru. Update lru.
                nextNode.prev = null;
                lru = nextNode;
            } else {
                // Key is in the middle. Update prev and next nodes.
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }

            // Bring keyNode to mru.
            mru.next = keyNode;
            keyNode.next = null;
            keyNode.prev = mru;
            mru = keyNode;
            return keyNode.value;
        }

        public void put(K key, V value) {
            if (container.containsKey(key)) {
                // Cache already contains key. Bring it to mru.
                Node<K, V> keyNode = container.get(key);
                keyNode.value = value;
                get(key);
            } else {
                // Add new node at mru and in the HashMap
                Node<K, V> keyNode = new Node<>(key, value, mru, null);
                mru.next = keyNode;
                mru = keyNode;
                container.put(key, keyNode);

                if (size == capacity) {
                    // Remove the node at lru and its entry in HashMap
                    container.remove(lru.key);
                    lru = lru.next;
                    lru.prev = null;
                } else {
                    if (size == 0) {
                        // Empty Linked List
                        lru = keyNode;
                    }
                    size++;
                }
            }
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> ca = new LRUCache<>(2);
        ca.display();

        ca.put("1", "1");
        System.out.println("get(1): Expected 1, Got: " + ca.get("1"));
        System.out.println("get(2): Expected null, Got: " + ca.get("2"));
        ca.put("2", "4");
        System.out.println("get(1): Expected 1, Got: " + ca.get("1"));
        System.out.println("get(2): Expected 4, Got: " + ca.get("2"));
        ca.display();

        System.out.println("\n*****************\n");

        ca.put("Key1", "Value1");
        ca.put("Key2", "Value4");
        ca.put("Key3", "Value9");
        ca.display();
        System.out.println("get(Key1): Expected null, Got: " + ca.get("Key1"));
        System.out.println("get(Key2): Expected Value4, Got: " + ca.get("Key2"));
        System.out.println("get(Key3): Expected Value9, Got: " + ca.get("Key3"));
        ca.display();

        System.out.println("\n*****************\n");

        ca.put("Key1", "Value1");
        ca.put("Key2", "Value4");
        ca.display();
        System.out.println("get(Key1): Expected Value1, Got: " + ca.get("Key1"));
        ca.put("Key3", "Value9");
        ca.display();
        System.out.println("get(Key1): Expected Value1, Got: " + ca.get("Key1"));
        System.out.println("get(Key2): Expected null, Got: " + ca.get("Key2"));
        System.out.println("get(Key3): Expected Value9, Got: " + ca.get("Key3"));
        ca.display();
        ca.put("Key1", "Value11");
        ca.put("Key4", "Value14");
        System.out.println("get(Key1): Expected Value11, Got: " + ca.get("Key1"));
        System.out.println("get(Key4): Expected Value14, Got: " + ca.get("Key4"));
        System.out.println("get(Key3): Expected null, Got: " + ca.get("Key3"));
        ca.display();
    }
}