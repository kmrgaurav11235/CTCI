import java.util.Arrays;
/*
https://www.geeksforgeeks.org/stable-selection-sort/
*/
class Sorting_05_SelectionSortStable {
    static void stableSelectionSort(int [] a, int n) {
    }
    public static void main(String[] args) {
        int a[] = { 64, 34, 25, 12, 22, 11, 90 }; 
        System.out.println("Original array: " + Arrays.toString(a)); 

        int n = a.length; 
        stableSelectionSort(a, n); 
        System.out.println("Sorted array: "+ Arrays.toString(a)); 
    }
}