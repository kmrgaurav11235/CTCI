import java.util.Arrays;
/*
https://www.geeksforgeeks.org/bubble-sort/
- Repeatedly steps through the list to be sorted, compares each pair of adjacent items and swaps them if
    they are in the wrong order.
- Also called Sinking Sort as it 'Sinks' the 'last element of loop' to its proper place on every iteration.
- Time Complexity: O(n^2)
- Space Complexity: O(1)
- In-place sort.
- Stable sort.
- Worst Performance: Worst case occurs when array is reverse sorted.
- Best Performance: Best performance occurs when array is already sorted.
*/

class Sorting_02_BubbleSort {
    static void bubbleSort(int [] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            // Iterate till second-to-last element, as we are comparing pairs.

            boolean hasExchangeHappened = false;
            for (int j = 0; j < n - 1 - i; j++) {
                // Iterate till second-to-last - i, as the last i elements have already 'sinked' to their place.
                if (a[j] > a[j + 1]) {
                    // Swap
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    hasExchangeHappened = true;
                }
            }

            if (!hasExchangeHappened) {
                // No exchange happened in this iteration. So, array is sorted.
                return;
            }
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        bubbleSort(a, n); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}