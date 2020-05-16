import java.util.Arrays;

/*
https://www.programiz.com/dsa/counting-sort

- 
*/
public class Sorting_10_CountingSort {
    
    private static int getMax(int[] a, int n) {
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public static void countingSort(int[] a, int n) {
        // Find the max and create an array
        int max = getMax(a, n);
        int [] count = new int [max + 1];

        // Fill count with frequency
        for (int i = 0; i < n; i++) {
            count[a[i]]++;
        }

        // Change count to record cumulative frequency
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        /*
        If count[2] == 5, it tells us this: the position of element '2' in the sorted array
        should be 4 (= 5 - 1, we subtract 1 as the index starts at 0).
        */

        // Create a temp array
        int [] temp = Arrays.copyOf(a, n);

        // Sort a using the temp and count arrays
        // To make it stable we are operating in reverse order
        for (int i = n - 1; i >= 0; i--) {
            a[count[temp[i]] - 1] = temp[i];
            count[temp[i]]--; // So that the next element is placed at a smaller index
        }
    }

    public static void main(String[] args) {
        int[] a = { 4, 2, 2, 8, 3, 3, 1 };
        int n = a.length;

        System.out.println("Original Array: ");
        System.out.println(Arrays.toString(a));

        countingSort(a, n);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(a));
        
    }
}