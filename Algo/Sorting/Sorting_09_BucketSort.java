import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
https://www.programiz.com/dsa/bucket-sort

- Visualization: https://www.cs.usfca.edu/~galles/visualization/BucketSort.html
- Bucket Sort is a sorting technique that sorts the elements by first dividing the elements into 
    several groups called buckets. The elements inside each bucket are sorted using any of the 
    suitable sorting algorithms or recursively calling the same algorithm.
- Finally, the elements of the bucket are gathered to get the sorted array.
- The process of bucket sort can be understood as a scatter-gather approach. The elements are first 
    scattered into buckets then the elements of buckets are sorted. Finally, the elements are gathered 
    in order.
- Worst Case Complexity: O(n^2)
    When there are elements of close range in the array, they are likely to be placed in the same bucket. 
    This may result in some buckets having more number of elements than others.
- Best Case Complexity: O(n+k)
    It occurs when the elements are uniformly distributed in the buckets with a nearly equal number of 
    elements in each bucket.
- Average Case Complexity: O(n)
    It occurs when the elements are distributed randomly in the array. Even if the elements are not 
    distributed uniformly, bucket sort runs in linear time. It holds true until the sum of the squares of 
    the bucket sizes is linear in the total number of elements.
- Bucket sort is used when:
    * Input is uniformly distributed over a range.
    * There are floating point values
- e.g. Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly 
    distributed across the range. 
*/
public class Sorting_09_BucketSort {
    
    static int getBucketIndex(double num) {
        int intNum = (int) (num * 10);
        return intNum;
    }

    static void bucketSort(double[] arr, int n) {
        if (n <= 0) {
            // Base case
            return;
        }
        // create 'numBuckets' buckets each of which can hold a range of values. Initialize each bucket with 0 values.
        int numBuckets = 10;
        @SuppressWarnings("unchecked")
        ArrayList<Double> lists [] = (ArrayList<Double> []) new ArrayList[numBuckets];
        
        for (int i = 0; i < numBuckets; i++) {
            lists[i] = new ArrayList<>();
        }
        // Put elements into buckets matching the range
        for (double num : arr) {
            int bucketIndex = getBucketIndex(num);
            lists[bucketIndex].add(num);
        }
        // Sort elements in each bucket
        for (ArrayList<Double> numList : lists) {
            Collections.sort(numList);
            // System.out.println(numList);
        }

        // Gather elements from each bucket
        int index = 0;
        for (int i = 0; i < lists.length; i++) {
            for (double num : lists[i]) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        double [] arr = { 0.42, 0.92, 0.33, 0.52, 0.37, 0.47, 0.51 };

        System.out.println("Original array: " + Arrays.toString(arr));
        bucketSort(arr, 7);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}