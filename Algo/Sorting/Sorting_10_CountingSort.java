import java.util.Arrays;

/*
https://www.programiz.com/dsa/counting-sort

- Counting sort is an algorithm that sorts the elements of an array by counting the number 
    of occurrences of each unique element in the array. The count is stored in an auxiliary 
    array and the sorting is done by mapping the count as an index of the auxiliary array.
- Steps:
    1. Find out the maximum element (let it be max) from the given array. 
    2. Initialize an array of length max+1 with all elements 0. This array is used for storing 
        the count of the elements in the array. 
    3. Store the count of each element at their respective index in count array.
    4. Store cumulative sum of the elements of the count array. It helps in placing the elements 
        into the correct index of the sorted array. 
    5. Find the index of each element of the original array in the count array. This gives the 
        cumulative count. Place the element at the index calculated as shown below:

- Time Complexity = O(max) + O(size) = O(max+size)
    Worst Case Complexity: O(n+k)
    Best Case Complexity: O(n+k)
    Average Case Complexity: O(n+k)

- In all the above cases, the complexity is the same because no matter how the elements are placed 
    in the array, the algorithm goes through n+k times.
- There is no comparison between any elements, so it is better than comparison based sorting techniques. 
    But, it is bad if the integers are very large because the array of that size should be made.
- Space Complexity:
    The space complexity of Counting Sort is O(max). Larger the range of elements, larger is the space 
    complexity.
- Counting Sort Applications: 
    * There are smaller integers with multiple counts.
    * Linear complexity is the need.
    * It is often used as a sub-routine to another sorting algorithm like radix sort.
- The problem with this counting sort algorithm is that we cannot sort the elements if we have negative 
    numbers in it (as there are no negative array indices). So, we find the minimum element and we store 
    count of that minimum element at zero index.
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