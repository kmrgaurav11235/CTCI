/*
- Given two sorted arrays A and B. A has a large enough buffer at the end to hold B. Write a
    method to merge B into A in sorted order.
- Approach:
    * If we insert merged elements at the start of A, then we will have to shift the remaining
        elements backwards.
    * So, it better to insert the elements into the back of array, where there is space.
*/
public class ASort_01_MergeTwoSortedArrays {

    static void merge(int[] a, int[] b, int m, int n) {
        int indexA = m - 1, indexB = n - 1, indexMerged = m + n - 1; // indices of a, b and mergedA
        while (indexA >= 0 && indexB >= 0) {
            if (a[indexA] >= b[indexB]) {
                a[indexMerged--] = a[indexA--];
            } else {
                a[indexMerged--] = b[indexB--];
            }
        }

        // If elements are left in b
        while (indexB >= 0) {
            a[indexMerged--] = b[indexB--];
        }

        // If elements are left in a, they are already at the right place
    }

    public static void main(String[] args) {
        int [] a = {2, 8, 13, 15, 20, -1, -1, -1, -1}; 
        int m = 5;
        int [] b = {5, 7, 9, 25}; 
        int n = b.length;

        System.out.println("Original Arrays:");
        printArray(a, m);
        printArray(b, n);

        merge(a, b, m, n);

        System.out.println("Merged Array:");
        printArray(a, a.length);
    }

    static void printArray(int[] a, int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(sb);
    }
}