import java.util.Arrays;

/*
https://www.geeksforgeeks.org/median-of-two-sorted-arrays/

- There are 2 sorted arrays ar1 and ar2 of size n each. Write an algorithm to find the median of the array 
    obtained after merging the above 2 arrays(i.e. array of length 2n).
- Algorithm :
    1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
    2) If m1 and m2 both are equal then return m1 (or m2).
    3) If m1 is greater than m2, then median is present in one of the below two sub-arrays.
        a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
        b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
    4) If m2 is greater than m1, then median is present in one of the below two sub-arrays.
        a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
        b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
    5) Repeat the above process until size of both the sub-arrays becomes 2.
    6) If size of the two arrays is 2, then use below formula to get the median.
        Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2

*/
public class AOS_02_MedianOfTwoSortedArraysOfSameSize {
    private static double getMedianOfArray(int[] ar, int low, int high) {
        // Get median of a single array
        int n = high - low + 1;
        if (n % 2 == 0) {
            return (ar[low + (n / 2) - 1] + ar[low + (n / 2)]) / 2.0;
        } else {
            return ar[low + (n / 2)];
        }
    }
    private static double getMedianUtil(int[] ar1, int[] ar2, int low1, int low2, int high1, int high2) {
        if (high1 - low1 == 1) {
            int mid1 = Math.max(ar1[low1], ar2[low2]);
            int mid2 = Math.min(ar1[high1], ar2[high2]);
            return (mid1 + mid2) / 2.0;
        }
        double median1 = getMedianOfArray(ar1, low1, high1);
        double median2 = getMedianOfArray(ar2, low2, high2);

        if (median1 == median2) {
            return median1;
        } else if (median1 > median2) {
            return getMedianUtil(ar1, ar2, low1, (low2 + high2 + 1) / 2, (low1 + high1 + 1) / 2, high2);
        } else {
            return getMedianUtil(ar1, ar2, (low1 + high1 + 1) / 2, low2, high1, (low2 + high2) / 2);
        }
    }

    public static double getMedian(int[] ar1, int[] ar2) throws Exception {
        int m = ar1.length; 
        int n = ar2.length; 

        if (m != n) { 
            throw new Exception("Doesn't work for arrays of unequal size."); 
        } else if (m == 0) { 
            throw new Exception("Arrays are empty."); 
        } else if (m == 1) { 
            return (ar1[0] + ar2[0]) / 2.0;
        } else { 
            return getMedianUtil(ar1, ar2, 0, 0, m - 1, n - 1); 
        }
    }
    public static void main(String[] args) {
        System.out.println("********** Test 1 **********");
        int [] ar1 = { 1, 2, 3, 6 }; 
        int [] ar2 = { 4, 6, 8, 10 }; 

        try {
            System.out.println("Array 1: " + Arrays.toString(ar1));
            System.out.println("Array 2: " + Arrays.toString(ar2));
            System.out.println("Median is " + getMedian(ar1, ar2)); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n********** Test 2 **********");
        int [] ar3 = {1, 12, 15, 26, 38}; 
        int [] ar4 = {2, 13, 17, 30, 45}; 

        try {
            System.out.println("Array 1: " + Arrays.toString(ar3));
            System.out.println("Array 2: " + Arrays.toString(ar4));
            System.out.println("Median is " + getMedian(ar3, ar4)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}