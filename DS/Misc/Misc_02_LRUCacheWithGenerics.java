// https://www.geeksforgeeks.org/lru-cache-implementation/
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class Misc_02_LRUCacheWithGenerics {
    static class LRUCache<T> {
        private int maxSize;
        private HashSet<T> elements;
        private Deque<T> queue;

        LRUCache(int maxSize) {
            this.maxSize = maxSize;
            elements = new HashSet<>();
            queue = new LinkedList<>();
        }

        public void display() {
            System.out.println("LRU Cache: " + queue);
        }

        public void refer(T n) {
            if (!elements.contains(n)) {
                System.out.println("Cache Miss!");
                if (queue.size() == maxSize) {
                    // Remove Least frequently used
                    T discarded = queue.pop();
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
        LRUCache<String> ca = new LRUCache<>(4); 
        ca.display();

        ca.refer("Test1"); 
        ca.display();

        ca.refer("Test2"); 
        ca.display();

        ca.refer("Test3"); 
        ca.display();

        ca.refer("Test1"); 
        ca.display();

        ca.refer("Test4"); 
        ca.display();
        
        ca.refer("Test5"); 
        ca.display(); 
        
        ca.refer("Test4"); 
        ca.display(); 
        
        ca.refer("Test1"); 
        ca.display(); 
    }
}