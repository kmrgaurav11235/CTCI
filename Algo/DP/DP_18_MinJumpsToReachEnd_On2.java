/*
https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/

- Given an array of integers where each element represents the max number of steps that can be made 
    forward from that element. Write a function to return the minimum number of jumps to reach the 
    end of the array (starting from the first element). If an element is 0, they cannot move through 
    that element.

- Examples:
    Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
    Output: 3 (1-> 3 -> 8 -> 9)
    Explanation: Jump from 1st element to 2nd element as there is only 1 step. Now there are three 
        options 5, 8 or 9. If 8 or 9 is chosen then the end node 9 can be reached. So 3 jumps are made.

    Input:  arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    Output: 10
    Explanation: In every step a jump is needed so the count of jumps is 10.

- Solution - Bottom-up approach:
    We build jumps[] array from right to left such that jumps[i] indicates the minimum number of jumps 
    needed to reach arr[n-1] from arr[i]. Finally, we return arr[0].

- Time complexity:O(n^2): Nested traversal of the array is needed.
- Space:O(n). To store the DP array linear space is needed.

*/
public class DP_18_MinJumpsToReachEnd_On2 {

    static int minJumps(int[] arr, int n) {
        // jumps[i] represents minimum number of jumps needed to reach arr[n-1] from arr[i]
        int [] jumps = new int[n];

        // Minimum number of jumps needed to reach last element from last elements itself is 0 
        jumps[n - 1] = 0;

        // Start from the second-last element, move from right to left and construct the jumps[] array.
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] == 0) {
                // If arr[i] is 0 then arr[n-1] can't be reached from here 
                jumps[i] = Integer.MAX_VALUE;
            } else if (i + arr[i] >= n - 1) {
                // If we can directly reach to the end point from here then jumps[i] is 1 
                jumps[i] = 1;
            } else {
                /*
                Otherwise, to find out the minimum number of jumps needed to reach arr[n-1], check all 
                the points reachable from here and jumps[] value for those points 
                */
                int minJumps = getMin(jumps, n, i + 1, i + arr[i]);

                // Handle overflow
                if (minJumps == Integer.MAX_VALUE) {
                    jumps[i] = minJumps;
                } else {
                    jumps[i] = 1 + minJumps;
                }
            }
        }
        return jumps[0];
    }
    
    private static int getMin(int[] jumps, int n, int startIndex, int endIndex) {
        int min = Integer.MAX_VALUE;
        for (int i = startIndex; i < n && i <= endIndex; i++) {
            min = Math.min(min, jumps[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 3, 6, 1, 0, 9 }; 
        System.out.println("Minimum number of jumps to reach end is: " + minJumps(arr1, arr1.length)); 

        int[] arr2 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }; 
        System.out.println("Minimum number of jumps to reach end is: " + minJumps(arr2, arr2.length)); 
    }
}
