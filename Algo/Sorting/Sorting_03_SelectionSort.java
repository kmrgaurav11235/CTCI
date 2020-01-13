import java.util.Arrays;
/*
https://www.geeksforgeeks.org/selection-sort/
 - Selection sort sorts an array by finding the 'minimum' element in every loop and moving it to its
    proper place in front. 
- In this way, the array gets divided into two parts:
    1) The Front part that is Sorted
    2) The Back part that is Unsorted
    After every loop, the size of sorted part increases.
- Time Complexity: O(n^2)
- Space Complexity: O(1)
- In-place sort.
- Stability : The default implementation is not stable. However, it can be made stable. 
- The best thing about Selection Sort is that it never makes more than n swaps. This can be useful when
    memory write is a costly operation.
*/
class Sorting_03_SelectionSort {
    static void selectionSort(int [] a, int n) {
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Find the minimum element
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // Swap
                int temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        selectionSort(a, n); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}