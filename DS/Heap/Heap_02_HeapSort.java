import java.util.Arrays;
/*
Heap Sort Algorithm for sorting in increasing order:
1. Build a max heap from the input data.
2. At this point, the largest item is stored at the root of the heap. Replace it with the last 
item of the heap followed by reducing the size of heap by 1. Finally, heapify the root of tree.
3. Repeat above steps while size of heap is greater than 1.
*/

class Heap_02_HeapSort {
    static class MaxHeap {
        private int maxHeap[];
        private int capacity;
        private int size;

        MaxHeap(int capacity) {
            this.capacity = capacity;
            size = 0;
            maxHeap = new int [capacity];
        }

        private int parent(int index) {
            // 'index' should not be 0.
            return (index - 1) / 2;
        }

        private int left(int index) {
            // 'index' should be less than size.
            return (2 * index + 1);
        }

        private int right(int index) {
            // 'index' should be less than size.
            return (2 * index + 2);
        }

        private void swap (int firstIndex, int secondIndex) {
            int temp = maxHeap[firstIndex];
            maxHeap[firstIndex] = maxHeap[secondIndex];
            maxHeap[secondIndex] = temp;
        }

        private void maxHeapify (int index) {
            // Check if Max-Heap property holds at 'index'. If not, fix it and cascade down.
            int largest = index;
            if (left(index) < size && maxHeap[largest] < maxHeap[left(index)]) {
                largest = left(index);
            }
            if (right(index) < size && maxHeap[largest] < maxHeap[right(index)]) {
                largest = right(index);
            }
            if (largest != index) {
                swap(largest, index);
                maxHeapify(largest);
            }
        }

        private void buildHeap() {
            int lastIndex = size - 1;
            int lastNonLeafIndex = parent(lastIndex);

            for (int i = lastNonLeafIndex; i >= 0; i--) {
                maxHeapify(i);
            }
        }

        void heapSort (int arr[]) {
            maxHeap = arr;
            size = arr.length;

            buildHeap();

            for (int i = size - 1; i > 0; i--) {
                swap(i, 0);
                size--;
                maxHeapify(0);
            }
        }
    }
    public static void main(String[] args) {
        
        int arr[] = {12, 11, 13, 5, 6, 7}; 
        int n = arr.length; 
  
        MaxHeap ob = new MaxHeap(n); 
        
        ob.heapSort(arr); 
  
        System.out.println("Sorted array is"); 
        System.out.println(Arrays.toString(arr)); 
    }
}