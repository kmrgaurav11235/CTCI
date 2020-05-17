import java.util.Arrays;
/*
https://www.programiz.com/dsa/radix-sort
https://www.geeksforgeeks.org/radix-sort/

- Radix sort is a sorting technique that sorts the elements by first grouping the individual digits 
    of the same place value. Then, sort the elements according to their increasing/decreasing order.
- Suppose, we have an array of 8 elements. First, we will sort elements based on the value of the 
    unit place. Then, we will sort elements based on the value of the tenth place. This process goes 
    on until the most significant place.
- Counting sort is used as an intermediate sort in radix sort.
- The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) 
    is O(n Log n), i.e., they cannot do better than n log n. 
- Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in range 
    from 1 to k.
- What if the elements are in range from 1 to n2?
    - We can’t use counting sort because counting sort will take O(n ^ 2) which is worse than comparison 
        based sorting algorithms.
    - Radix Sort is the answer. The idea of Radix Sort is to do digit by digit sort starting from least 
        significant digit to most significant digit. Radix sort uses counting sort as a subroutine to sort.
- Complexity
    - For the radix sort that uses counting sort as an intermediate stable sort, the time complexity 
        is O(d(n+k)).
        
        Here, d is the number cycle and O(n+k) is the time complexity of counting sort.
        Thus, radix sort has linear time complexity which is better than O(n log n) of comparative 
        sorting algorithms.
- If we take very large digit numbers or the number of other bases like 32-bit and 64-bit numbers 
    then it can perform in linear time however the intermediate sort takes large space. This makes 
    radix sort space inefficient. This is the reason why this sort is not used in software libraries.
 */

public class Sorting_11_RadixSort {
    private static int getMax(int [] a, int n) {
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
    
    private static void countingSort(int[] a, int n, int place) {
        int decimalCount = 10;
        int [] count = new int [decimalCount];

        // Frequency
        for (int num : a) {
            count[(num / place) % decimalCount]++;
        }

        // Cumulative frequency
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Temp array
        int [] temp = Arrays.copyOf(a, n);

        // Sorting
        for (int i = n - 1; i >= 0; i--) {
            a[count[(temp[i] / place) % decimalCount] - 1] = temp[i];
            count[(temp[i] / place) % decimalCount]--;
        }
    }

    public static void radixSort(int[] a, int n) {
        int max = getMax(a, n);

        for (int place = 1; (max / place) > 0; place *= 10) {
            countingSort(a, n, place);
        }
    }

    public static void main(String[] args) {
        int[] a = { 121, 432, 564, 23, 1, 45, 788 };
        int n = a.length;

        System.out.println("Original Array: ");
        System.out.println(Arrays.toString(a));
        
        radixSort(a, n);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(a));
    }
}