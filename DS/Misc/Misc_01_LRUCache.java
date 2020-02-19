// https://www.geeksforgeeks.org/lru-cache-implementation/
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class Misc_01_LRUCache {
    static class LRUCache {
        private int maxSize;
        private HashSet<Integer> elements;
        private Deque<Integer> queue;

        LRUCache(int maxSize) {
            this.maxSize = maxSize;
            elements = new HashSet<>();
            queue = new LinkedList<>();
        }

        public void display() {
            System.out.println("LRU Cache: " + queue);
        }

        public void refer(int n) {
            if (!elements.contains(n)) {
                System.out.println("Cache Miss!");
                if (queue.size() == maxSize) {
                    // Remove Least frequently used
                    int discarded = queue.pop();
                    elements.remove(discarded);
                }
            } else {
                System.out.println("Cache Hit!");
                queue.remove(n);
            }
            // Add new element
            queue.offer(n);
            elements.add(n);            
        }
    }
    public static void main(String[] args) {
        LRUCache ca = new LRUCache(4); 
        ca.display();

        ca.refer(1); 
        ca.display();

        ca.refer(2); 
        ca.display();

        ca.refer(3); 
        ca.display();

        ca.refer(1); 
        ca.display();

        ca.refer(4); 
        ca.display();
        
        ca.refer(5); 
        ca.display(); 
        
        ca.refer(4); 
        ca.display(); 
        
        ca.refer(1); 
        ca.display(); 
    }
}