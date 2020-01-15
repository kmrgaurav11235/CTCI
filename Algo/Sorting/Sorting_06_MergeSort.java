import java.util.Arrays;
/*
https://www.geeksforgeeks.org/merge-sort/
Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, calls itself for the 
two halves and then merges the two sorted halves. The merge() function is used for merging two halves. 
- Time Complexity: O(n * log n)
- Time complexity of Merge Sort is 'n * log n' in all 3 cases (worst, average and best) as it always 
    divides the array into two halves and take linear time to merge two halves.
- Space Complexity: O(n)
- Out-place sort: Uses extra memory
- Stable sort.
- Merge Sort is useful for sorting linked lists in O(n * log n) time. In the case of linked lists, the case 
    is different mainly due to the difference in memory allocation of arrays and linked lists. Unlike an array, 
    in the linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge 
    operation of merge sort can be implemented without extra space for linked lists. 
- In arrays, we can do random access as elements are contiguous in memory. Unlike arrays, we can not do random 
    access in the linked list. Quick Sort requires a lot of this kind of access. In linked list to access i’th 
    index, we have to travel each and every node from the head to i’th node as we don’t have a continuous block 
    of memory. Therefore, the overhead increases for quicksort. Merge sort accesses data sequentially and the 
    need of random access is low. 
*/
class Sorting_06_MergeSort {
    static void merge(int [] a, int left, int mid, int right) {
        // Sizes of new arrays
        int sizeOfLeft = mid - left + 1;
        int sizeOfRight = right - mid;

        // Create temp arrays
        int tempLeft[] = new int[sizeOfLeft];
        int tempRight[] = new int[sizeOfRight];

        // Copy Elements
        for (int i = 0; i < sizeOfLeft; i++) {
            tempLeft[i] = a[i + left];
        }

        for (int i = 0; i < sizeOfRight; i++) {
            tempRight[i] = a[i + mid + 1];
        }

        // Merge
        int i = 0, j = 0, k = left;
        while (i < sizeOfLeft && j < sizeOfRight) {
            if (tempLeft[i] <= tempRight[j]) {
                // Equality sign is there to ensure stability
                a[k++] = tempLeft[i++];
            } else {
                a[k++] = tempRight[j++];
            }
        }
        while (i < sizeOfLeft) {
            a[k++] = tempLeft[i++];
        }
        while (j < sizeOfRight) {
            a[k++] = tempRight[j++];
        }
    }
    static void mergeSort(int [] a, int left, int right) {
        if (left < right) {
            // left == right -> 1 element arrays are already sorted.
            // left > right  -> Invalid.
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        mergeSort(a, 0, n - 1); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}