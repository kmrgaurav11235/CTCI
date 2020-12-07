import java.util.Arrays;

/*
https://www.geeksforgeeks.org/maximum-absolute-difference-value-index-sums/

- Given an unsorted array A[] of N integers, return maximum value of f(i, j) for all 1 ≤ i, j < N.
- f(i, j) or absolute difference of two elements of an array A is defined as:
    |A[i] – A[j]| + |i – j|, where |A| denotes the absolute value of A.

- Examples: We will calculate the value of f(i, j) for each pair of (i, j) and return the maximum value 
    thus obtained.

    Input : A = {1, 3, -1}
    Output : 5
    f(1, 1) = f(2, 2) = f(3, 3) = 0
    f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
    f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
    f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5
    So, we return 5.

    Input : A = {3, -2, 5, -4}
    Output : 10
    f(1, 1) = f(2, 2) = f(3, 3) = f(4, 4) = 0
    f(1, 2) = f(2, 1) = |3 - (-2)| + |1 - 2| = 6
    f(1, 3) = f(3, 1) = |3 - 5| + |1 - 3| = 4
    f(1, 4) = f(4, 1) = |3 - (-4)| + |1 - 4| = 10
    f(2, 3) = f(3, 2) = |(-2) - 5| + |2 - 3| = 8
    f(2, 4) = f(4, 2) = |(-2) - (-4)| + |2 - 4| = 4
    f(3, 4) = f(4, 3) = |5 - (-4)| + |3 - 4| = 10
    So, we return 10
- A solution in O(n) time complexity can be worked out using the properties of absolute values.
- f(i, j) = |A[i] – A[j]| + |i – j| can be written in 4 ways. Since we are looking at max value, we don’t 
    even care if the value becomes negative as long as we are also covering the max value in some way.

    Case 1: A[i] > A[j] and i > j
    |A[i] - A[j]| = A[i] - A[j]
    |i -j| = i - j
    hence, f(i, j) = (A[i] + i) - (A[j] + j)

    Case 2: A[i] < A[j] and i < j
    |A[i] - A[j]| = -(A[i]) + A[j]
    |i -j| = -(i) + j
    hence, f(i, j) = -(A[i] + i) + (A[j] + j)

    Case 3: A[i] > A[j] and i < j
    |A[i] - A[j]| = A[i] - A[j]
    |i -j| = -(i) + j
    hence, f(i, j) = (A[i] - i) - (A[j] - j)

    Case 4: A[i] < A[j] and i > j
    |A[i] - A[j]| = -(A[i]) + A[j]
    |i -j| = i - j
    hence, f(i, j) = -(A[i] - i) + (A[j] - j)
- Algorithm:
    * Calculate the value of A[i] + i and A[i] – i for every element of the array while traversing through 
        the array.
    * Then for the two equivalent cases, we find the maximum possible value. For that, we have to store 
        minimum and maximum values of expressions (A[i] + i) and (A[i] – i) for all i.
    * Hence the required maximum absolute difference is maximum of two values i.e. max((A[i] + i) – (A[j] + j)) 
        and max((A[i] – i) – (A[j] – j)). These values can be found easily in linear time.

        a. For max((A[i] + i) – (A[j] + j)) Maintain two variables max1 and min1 which will store maximum and 
            minimum values of A[i] + i respectively. max((A[i] + i) – (A[j] + j)) = max1 – min1
        b. For max((A[i] – i) – (A[j] – j)). Maintain two variables max2 and min2 which will store maximum and 
            minimum values of A[i] – i respectively. max((A[i] – i) – (A[j] – j)) = max2 – min2

*/
public class AS_06_MaximumAbsoluteDifference {
    static int maxAbsoluteDistance(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int n = arr.length;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            max1 = Math.max(max1, arr[i] + i);
            min1 = Math.min(min1, arr[i] + i);

            max2 = Math.max(max2, arr[i] - i);
            min2 = Math.min(min2, arr[i] - i);
        }
        return Math.max(max1 - min1, max2 - min2);
    }
    public static void main(String[] args) {
        int [] arr = { -70, -64, -6, -56, 64, 61, -57, 16, 48, -98 }; 
        System.out.println("Array: " + Arrays.toString(arr) 
            + "\nMaximum Absolute Difference: " + maxAbsoluteDistance(arr)); 
    }
}
