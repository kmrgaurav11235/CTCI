import java.util.Arrays;
/*
https://www.geeksforgeeks.org/quick-sort/
https://www.programiz.com/dsa/quick-sort

QuickSort is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given array 
around the picked pivot. There are many different versions of quickSort that pick pivot in different ways.

    1. Always pick first element as pivot.
    2. Always pick last element as pivot (implemented here)
    3 .Pick a random element as pivot.
    4. Pick median as pivot.

The key process in quickSort is partition(). Target of partitions is, given an array and an element x of 
array as pivot, put x at its correct position in sorted array -- put all smaller elements (smaller than x) 
before x, and put all greater elements (greater than x) after x. All this should be done in linear time.

- Worst Case: The worst case occurs when the partition process always picks greatest or smallest element 
    as pivot. If we consider above partition strategy where last element is always picked as pivot, the 
    worst case would occur when the array is already sorted in increasing or decreasing order. This takes
    n ^ 2 time.
- Best Case: The best case occurs when the partition process always picks the middle element as pivot. This
    takes n * log n time.
- Average Case: To do average case analysis, we need to consider all possible permutation of array and calculate 
    time taken by every permutation which is very complicated. We can get an idea of average case by considering 
    the case when partition puts O(n/9) elements in one set and O(9n/10) elements in other set. Following is 
    recurrence for this case.
    T(n) = T(n/9) + T(9n/10)
    Solution of above recurrence is also O(n * log n)
- Although the worst case time complexity of QuickSort is O(n ^ 2) which is more than many other sorting algorithms 
    like Merge Sort and Heap Sort, QuickSort is faster in practice, because its inner loop can be efficiently 
    implemented on most architectures, and in most real-world data. QuickSort can be implemented in different ways by 
    changing the choice of pivot, so that the worst case rarely occurs for a given type of data. However, merge sort 
    is generally considered better when data is huge and stored in external storage.

- Is QuickSort stable? The default implementation is not stable. However any sorting algorithm can be made stable by 
    considering indexes as comparison parameter.
- Is QuickSort In-place? As per the broad definition of in-place algorithm it qualifies as an in-place sorting algorithm 
    as it uses extra space only for storing recursive function calls but not for manipulating the input.
- Space Complexity : O(n) if you consider the system stack space.
*/
class Sorting_07_QuickSort {
    static int partition(int [] a, int left, int right) {
        int pivot = right;
        int i = left - 1;
        for (int j = left; j <= right; j++) {
            if (a[j] <= a[pivot]) {
                // Increment i. Swap a[i] and a[j]
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            // else do nothing
        }
        return i;
    }
    static void quickSort(int [] a, int left, int right) {
        if (left < right) {
            // left == right: Array has 1 element only. Already sorted.
            // left > right: Invalid
            int pivot = partition(a, left, right);
            quickSort(a, left, pivot - 1);
            quickSort(a, pivot + 1, right);
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        quickSort(a, 0, n - 1); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}