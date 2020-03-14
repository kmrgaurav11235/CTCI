import java.util.HashMap;

/*
https://medium.com/algorithm-and-datastructure/lfu-cache-in-o-1-in-java-4bac0892bdb3
https://www.javarticles.com/2012/06/lfu-cache.html
https://leetcode.com/problems/lfu-cache/discuss/?currentPage=1&orderBy=most_votes&query=
https://www.geeksforgeeks.org/least-frequently-used-lfu-cache-implementation/
Least Frequently Used (LFU) is a caching algorithm in which the least frequently used cache block 
is removed whenever the cache is overflowed. In LFU we check the old page as well as the frequency 
of that page and if the frequency of the page is larger than the old page we cannot remove it and 
if all the old pages are having same frequency then take last i.e FIFO method for that and remove 
that page.
*/

class Misc_04_LFUCache {
    static class LFUNode {
        int index;
        int frequency;

        LFUNode(int index, int frequency) {
            this.index = index;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "{index: " + index + ", frequency: " + frequency + "}";
        }
    }

    static class LFUMinHeap {
        private LFUNode [] arr;
        private int size;
        private int capacity;

        private HashMap<Integer, Integer> pageIndexToHeapIndexMap;

        LFUMinHeap(int n) {
            capacity = n;
            arr = new LFUNode[n];
            size = 0;
            pageIndexToHeapIndexMap = new HashMap<>();
        }

        private int left (int index) {
            return 2 * index + 1;
        }

        private int right (int index) {
            return 2 * index + 2;
        }

        private int parent (int index) {
            return (index - 1) / 2;
        }

        private void swap (int index1, int index2) {
            LFUNode temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;

            pageIndexToHeapIndexMap.put(arr[index1].index, index1);
            pageIndexToHeapIndexMap.put(arr[index2].index, index2);
        }

        private void heapifyDown(int index) {
            if (index >= size) {
                return;
            }
            int lChild = left(index);
            int rChild = right(index);

            int smallest = index;
            if (lChild < size && arr[lChild].frequency < arr[index].frequency) {
                smallest = lChild;
            }
            if (rChild < size && arr[rChild].frequency < arr[index].frequency) {
                smallest = rChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                heapifyDown(smallest);
            }
        }

        private void heapifyUp(int index) {
            if (index == 0) {
                return;
            }
            int par = parent(index);
            if (arr[par].frequency > arr[index].frequency) {
                swap(index, par);
                heapifyUp(par);
            }
        }

        public int size() {
            return size;
        }

        public LFUNode getMinimum() {
            return arr[0];
        }

        public LFUNode extractMinimum() {
            if (size == 0) {
                return null;
            } else if (size == 1) {
                size--;
                pageIndexToHeapIndexMap.remove(arr[0].index);
                return arr[0];
            }

            LFUNode extracted = arr[0];

            arr[0] = arr[size - 1];
            size--;
            heapifyDown(0);

            return extracted;
        }

        public boolean insert(LFUNode node) {
            if (size == capacity) {
                return false;
            }
            size++;
            arr[size - 1] = node;
            pageIndexToHeapIndexMap.put(node.index, size - 1);
            heapifyUp(size - 1);
            return true;
        }

        public void incrementFrequency(int nodeIndex) {
            int foundIndex = pageIndexToHeapIndexMap.get(nodeIndex);

            arr[foundIndex].frequency = arr[foundIndex].frequency + 1;

            heapifyDown(foundIndex);
        }
    }
    static class LFUCache {
    }
    public static void main(String[] args) {

        /*LFUCache ca = new LRUCache(4); 
        ca.display();

        ca.refer(1); 
        ca.display();

        ca.refer(2); 
        ca.display();

        ca.refer(1); 
        ca.display();

        ca.refer(3); 
        ca.display();

        ca.refer(2); 
        ca.display();
        
        ca.refer(4); 
        ca.display(); 
        
        ca.refer(5); 
        ca.display(); 
        
        ca.refer(2); 
        ca.display(); */
    }
}