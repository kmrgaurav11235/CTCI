import java.util.Arrays;
/*
https://www.geeksforgeeks.org/insertion-sort/
In Insertion Sort, we divide the array into two parts - Sorted and Unsorted. For every iteration, we
move an element out of unsorted array into sorted array and put it in its proper place.
- Time Complexity: O(n^2)
- Space Complexity: O(1)
- In-place sort.
- Stable sort.
- Worst Performance: Worst case occurs when array is reverse sorted.
- Best Performance: Best performance occurs when array is already sorted.
- Uses:
    1) Insertion sort can be used with good performance if number of elements is small. 
    2) It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array.
    3) It can also be used when we have a continuous inflow of numbers and we need to keep the list sorted.
*/
class Sorting_04_InsertionSort {
    static void insertionSort(int [] a, int n) {
        for (int i = 1; i < n; i++) {
            // Skipping the first element since a list with 1 element is always sorted.

            int currentElement = a[i];
            int index = i - 1;

            while (index >= 0 && a[index] > currentElement) {
                a[index + 1] = a[index];
                index--;
            }
            // Found the correct place
            a[index + 1] = currentElement;
        }
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        insertionSort(a, n); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}