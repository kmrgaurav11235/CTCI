/*
https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

- Write a program to find the sum of contiguous subarray within a one-dimensional 
    array of numbers which has the largest sum. 
- Kadane’s Algorithm:
    1) Initialize:
        maxSoFar = a[0]
        maxEndingHere = a[o]
    2) Loop for each element of the array except the first:
        a) maxEndingHere = max (a[i], maxEndingHere + a[i])
        b) maxSoFar = max (maxSoFar, maxEndingHere)
    3) return maxSoFar
*/
public class AO_01_LargestSumContiguousSubarray {
    static int maxSubArraySum(int[] a) {
        int maxEndingHere = a[0];
        int maxSoFar = a[0];

        for (int i = 1; i < a.length; i++) {
            maxEndingHere = Math.max(a[i], a[i] + maxEndingHere);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }
    public static void main(String[] args) {
        int a[] = {-2, -3, 4, -1, -2, 1, 5, -3}; 
        int maxSum = maxSubArraySum(a); 
        System.out.println("Maximum contiguous sum is " + maxSum); 
    }
}