import java.util.Arrays;
/*
https://www.geeksforgeeks.org/heap-sort/
- Heap sort is a comparison based sorting technique based on Binary Heap data structure. 
- Heap sort is an in-place algorithm.
- Its typical implementation is not stable, but can be made stable.
- Time Complexity: Time complexity of heapify is O(log n). Time complexity of buildHeap() is 
    O(n) and overall time complexity of Heap Sort is O(n * log n).
*/
class Sorting_08_HeapSort {
    static class MaxHeap {
        private int a[];
        private int capacity;
        private int size;

        MaxHeap() {
            this (0);
        }

        MaxHeap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
        }

        private int parent (int index) {
            // Always check if index == 0 before calling this
            return (index - 1) / 2;
        }

        private int left (int index) {
            // Always check if index < size before calling this
            return (2 * index + 1);
        }

        private int right (int index) {
            // Always check if index < size before calling this
            return (2 * index + 2);
        }

        private void swap (int i, int j) {
            if (i != j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        private void heapifyDown(int index) {
            int leftChild = left(index);
            int rightChild = right(index);

            int maxElement = index;

            if (leftChild < size && a[leftChild] > a[maxElement]) {
                maxElement = leftChild;
            }
            if (rightChild < size && a[rightChild] > a[maxElement]) {
                maxElement = rightChild;
            }
            if (maxElement != index) {
                swap(maxElement, index);
                heapifyDown(maxElement);
            }
        }

        public void buildHeap(int [] arr, int n) {
            if (n <= 1) {
                return;
            }
            a = arr;
            capacity = arr.length;
            size = n;

            int lastLeafNode = n - 1;
            int lastNonLeafNode = parent(lastLeafNode);

            for (int i = lastNonLeafNode; i >= 0; i--) {
                heapifyDown(i);
            }
        }

        public int extractMax() {
            if (size == 0) {
                return Integer.MIN_VALUE;
            } else if (size == 1) {
                size--;
                return a[0];
            }
            int temp = a[0];
            a[0] = a[size - 1];
            size--;
            heapifyDown(0);
            return temp;
        }

        public void heapSort(int [] arr, int n) {
            buildHeap(arr, n);

            for (int i = 0; i < n; i++) {
                a[n - 1 - i] = extractMax();
            }
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.heapSort(a, n);

        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}