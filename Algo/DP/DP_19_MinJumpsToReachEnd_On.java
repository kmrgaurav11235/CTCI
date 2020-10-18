/*
https://www.youtube.com/watch?v=vBdo7wtwlXs
https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/

- Given an array of integers where each element represents the max number of steps that can be made 
    forward from that element. Write a function to return the minimum number of jumps to reach the 
    end of the array (starting from the first element). If an element is 0, they cannot move through 
    that element.

- Examples:
    Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
    Output: 3 (1-> 3 -> 8 -> 9)
    Explanation: Jump from 1st element to 2nd element as there is only 1 step. Now there are three 
        options 5, 8 or 9. If 8 or 9 is chosen then the end node 9 can be reached. So 3 jumps are 
        made.

    Input:  arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    Output: 10
    Explanation: In every step a jump is needed so the count of jumps is 10.

- A O(n ^ 2) solution has already been discussed. Here, we discuss a O(n) solution:

- Variables to be used:
    1. ladder: This keeps track of the farthest reaching ladder that we have.
    2. stairsLeft: This keeps track of the stairs in the current ladder. It stores the number of steps 
        that we can still take.
    3. jump: This stores the number of jumps necessary to reach the destination -- the last element of 
        the array.
*/
public class DP_19_MinJumpsToReachEnd_On {
    static int minJumps(int[] arr, int n) {
        if (n <= 1) {
            return 0;
        }
        int farthestReachingLadder = arr[0];
        int stairsLeftInCurrLadder = arr[0];
        int numOfJumps = 1;
        /*
        Loop from second element (i == 1) to second-to-last element (i == n - 2):
        -> arr[0] is already accounted-for during initialization.
        -> When we reach arr[n - 1], no processing is required, we just need to return the value of 'jump'.
        */
        for (int i = 1; i < n - 1; i++) {
            // Update farthestReachingLadder
            farthestReachingLadder = Math.max(farthestReachingLadder, i + arr[i]);

            // We use a step to get to the current index 
            stairsLeftInCurrLadder--;

            if (stairsLeftInCurrLadder == 0) {
                // No more stairs left on the current ladder, jump to the next ladder
                numOfJumps++;
                stairsLeftInCurrLadder = farthestReachingLadder - i;
                if (stairsLeftInCurrLadder <= 0) {
                    // Cannot reach destination
                    return -1;
                }
            }
        }
        return numOfJumps;
    }
    public static void main(String[] args) {
        int[] arr1 = { 1, 3, 6, 1, 0, 9 }; 
        System.out.println("Minimum number of jumps to reach end is: " + minJumps(arr1, arr1.length)); 

        int[] arr2 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }; 
        System.out.println("Minimum number of jumps to reach end is: " + minJumps(arr2, arr2.length)); 
    }
}
