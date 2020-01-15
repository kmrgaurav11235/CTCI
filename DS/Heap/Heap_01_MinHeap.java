import java.util.Arrays;

class Heap_01_MinHeap {
    static class MinHeap {
        /*
        A Binary Heap is a Binary Tree with following properties.
        1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has
        all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array.
        2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at root must be minimum among all 
        keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary 
        Heap is similar to MinHeap.

        Methods:
        1. getMin() -> Returns the Minimum Element in Heap. Time Complexity = O(1).
        2. extractMin() -> Extracts and returns the Minimum Element in Heap. Time Complexity = O(Logn) as this operation 
        needs to maintain the heap property (by calling heapify()) after removing root.
        3. decreaseKey() -> Decreases the given element in Heap. Time complexity = O(Logn). If the decreased key value of 
        a node is greater than the parent of the node, then we don’t need to do anything. Otherwise, we need to traverse 
        up to fix the violated heap property.
        4. insert() -> Inserts a new element in Heap. Time complexity = O(Logn). We add a new key at the end of the tree. 
        If new key is greater than its parent, then we don’t need to do anything. Otherwise, we need to traverse up to fix 
        the violated heap property.
        5. delete() -> Deletes the element at a given index in Heap. Time complexity = O(Logn). We replace the key to be 
        deleted with negative infinity by calling decreaseKey(). After decreaseKey(), the minus infinite value must reach 
        root, so we call extractMin() to remove the key. 
        */
        private int minHeap[];
        private int capacity;
        private int size;

        MinHeap(int capacity) {
            this.capacity = capacity;
            size = 0;
            minHeap = new int[capacity];
        }

        int getSize() {
            return size;
        }

        boolean isOverflow() {
            return (capacity == size);
        }

        private int parent(int index) {
            // The index of parent of element at 'index'. Always check: 'index' should not be 0.
            return (index - 1) / 2;
        }

        private int left(int index) {
            // The index of left-child of element at 'index'. Always check: 'index' should be less than 'size'.
            return (2 * index + 1);
        }

        private int right(int index) {
            // The index of right-child of element at 'index'. Always check: 'index' should be less than 'size'.
            return (2 * index + 2);
        }

        private void swap(int firstIndex, int secondIndex) {
            // Swap elements of heap at indices firstIndex and secondIndex
            int temp = minHeap[firstIndex];
            minHeap[firstIndex] = minHeap[secondIndex];
            minHeap[secondIndex] = temp;
        }

        private void minHeapify (int index) {
            // If the min-heap property is broken at 'index', fix it. Cascade the changes below.
            int smallest = index;

            if (left(index) < size && minHeap[smallest] > minHeap[left(index)]) {
                smallest = left(index);
            }
            if (right(index) < size && minHeap[smallest] > minHeap[right(index)]) {
                smallest = right(index);
            }
            if (smallest != index) {
                swap(smallest, index);
                minHeapify(smallest);
            }
        }

        int getMin() {
            return minHeap[0];
        }

        int extractMin() {
            if (size == 0) {
                System.out.println("Underflow!!");
                return Integer.MIN_VALUE;
            }
            if (size == 1) {
                size = 0;
                return minHeap[0];
            }
            int minElement = minHeap[0];
            minHeap[0] = minHeap[size - 1];
            size--;
            minHeapify(0);

            return minElement;
        }

        void decreaseKey(int index, int newValue) {
            if (minHeap[index] < newValue) {
                System.out.println("New value cannot be greater than old value.");
                return;
            }
            minHeap[index] = newValue;
            while (index > 0 && minHeap[index] < minHeap[parent(index)]) {
                swap(index, parent(index));
                index = parent(index);
            }
        }

        void insert(int key) {
            if (isOverflow()) {
                System.out.println("Overflow!! Cannot insert new entry.");
                return;
            }
            size++;
            int index = size - 1;
            minHeap[index] = key;
            while (index > 0 && minHeap[index] < minHeap[parent(index)]) {
                swap(index, parent(index));
                index = parent(index);
            }
        }

        void delete(int index) {
            decreaseKey(index, Integer.MIN_VALUE);
            extractMin();
        }

        void print() {
            for (int i = 0; i < size; i++) {
                System.out.print(minHeap[i] + " ");
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
 
        System.out.println("The Min Heap is ");
        minHeap.print();
        System.out.println("The Min val is " + minHeap.extractMin());
        System.out.println("The Min Heap after extraction is ");
        minHeap.print();
        
    }
}