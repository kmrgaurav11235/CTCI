import java.util.Arrays;

/*
https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/

- Given an array of distinct elements. The task is to find triplets in the array whose sum is zero.
- Examples :
    1) 
    Input : arr[] = {0, -1, 2, -3, 1}
    Output : (0 -1 1), (2 -3 1)
    Explanation : The triplets with zero sum are: 0 + -1 + 1 = 0 and 2 + -3 + 1 = 0  

    2)
    Input : arr[] = {1, -2, 1, 0, 5}
    Output : 1 -2  1
    Explanation : The triplets with zero sum is 1 + -2 + 1 = 0   

- The algorithm uses Sorting to arrive at the correct result and is solved in O(n2) time. For every element, 
    check that there is a pair whose sum is equal to the negative value of that element.
- Algorithm:
    1) Sort the array in ascending order.
    2) Traverse the array from start to end.
    3) For every index i, create two variables l = i + 1 and r = n – 1
    4) Run a loop until l is less than r, if the sum of array[l], array[r] is equal to zero then print the 
        triplet.
    5) If the sum is less than zero then increment value of l. By increasing value of l, the sum will increase 
        as the array is sorted, so array[l+1] > array [l].
    6) If the sum is greater than zero then decrement value of r. By increasing value of l, the sum will decrease 
        as the array is sorted, so array[r-1] < array [r].
- Time Complexity : O(n ^ 2).
    only two nested loops is required, so the time complexity is O(n2).
- Auxiliary Space : O(1), no extra space is required, so the time complexity is constant.

*/
public class AS_07_TripletsWithZeroSum {
    static void findTriplets(int[] arr, int n) {
        if (n < 3) {
            System.out.println("Error!! Length of array should be >= 3.");
            return;
        }
        Arrays.sort(arr);
        for (int i = 0; i <= n - 3; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (arr[i] + arr[left] + arr[right] == 0) {
                    System.out.println(arr[i] + ", " + arr[left] + ", " + arr[right]);
                    left++;
                    right--;
                } else if (arr[i] + arr[left] + arr[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = {0, -1, 2, -3, 1}; 
        int n = arr.length; 
        System.out.println("Triplets with Zero Sum:");
        findTriplets(arr, n); 
    }
}
