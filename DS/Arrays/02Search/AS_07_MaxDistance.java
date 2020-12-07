import java.util.Arrays;

/*
https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/

- Given an array arr[], find the maximum j – i such that arr[j] > arr[i]. Examples : 
    Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
    Output: 6  (j = 7, i = 1)

    Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
    Output: 8 ( j = 8, i = 0)

    Input:  {1, 2, 3, 4, 5, 6}
    Output: 5  (j = 5, i = 0)

    Input:  {6, 5, 4, 3, 2, 1}
    Output: -1 
- To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index j. For an 
    element arr[i], we do not need to consider arr[i] for left index if there is an element smaller than arr[i] 
    on left side of arr[i]. 
- Similarly, if there is a greater element on right side of arr[j] then we do not need to consider this j for 
    right index. 
- So we construct two auxiliary arrays LMin[] and RMax[]. LMin[i] holds the smallest element on left side of arr[i] 
    including arr[i]. RMax[j] holds the greatest element on right side of arr[j] including arr[j]. 
- After constructing these two auxiliary arrays, we traverse both of these arrays from left to right. While 
    traversing, LMin[] and RMax[] if we see that LMin[i] is greater than RMax[j], then we must move ahead in LMin[] 
    (do i++) because all elements on left of LMin[i] are greater than or equal to LMin[i]. 
- Otherwise we must move ahead in RMax[j] to look for a greater j – i value.
- Time Complexity: O(n) 
    Auxiliary Space: O(n)
*/
public class AS_07_MaxDistance {
    static int maxDistance(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int n = arr.length;

        int [] lMin = new int[n]; // lMin[i] = smallest element to the left of i, including i
        lMin[0] = arr[0];
        for (int i = 1; i < n; i++) {
            lMin[i] = Math.min(lMin[i - 1], arr[i]);
        }

        int [] rMax = new int[n]; // rMax[i] = largest element to the right of i, including i
        rMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], arr[i]);
        }

        int maxDistance = 0;
        int left = 0, right = 0;
        while (left < n && right < n) {
            if (lMin[left] < rMax[right]) {
                maxDistance = Math.max(maxDistance, right - left);
                // Look for a better solution
                right++;
            } else {
                // No solution here, look for next solution
                left++;
            }
        }
        
        return maxDistance;
    }
    public static void main(String[] args) {
        int arr[] = { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 };

        System.out.println("Array: " + Arrays.toString(arr) 
            + ", Max Distance: " + maxDistance(arr));
    }
}
