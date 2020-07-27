import java.util.Arrays;

/*
https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
Also see House Thief problem under Dynamic Programming

- Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 
    two numbers in the sequence should be adjacent in the array. 
- Examples :
    Input : arr[] = {5, 5, 10, 100, 10, 5}
    Output : 110

    Input : arr[] = {1, 2, 3}
    Output : 4

    Input : arr[] = {1, 20, 3}
    Output : 20

- Algorithm:
    1) Loop for all elements in arr[] and maintain two sums incl and excl where incl = Max sum including 
        the previous element and excl = Max sum excluding the previous element.
    2) Max sum excluding the current element will be max(incl, excl) and max sum including the current 
        element will be excl + current element (Note that only excl is considered because elements cannot 
        be adjacent).
    3) At the end of the loop return max of incl and excl.
*/
public class AO_02_MaximumSumNonAdjacent {

    static int maxValueFromNonAdjacentElements(int[] arr) {
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        int maxExcluding = arr[0];
        int maxIncluding = arr[1];

        for (int i = 2; i < arr.length; i++) {
            int newMaxExcluding = Math.max(maxExcluding, maxIncluding);
            int newMaxIncluding = Math.max(arr[i], arr[i] + maxExcluding);

            maxExcluding = newMaxExcluding;
            maxIncluding = newMaxIncluding;
        }
        return Math.max(maxExcluding, maxIncluding);
    }
    public static void main(String[] args) {
        int [] arr1 = {6, 7, 1, 30, 8, 4, 2};
        System.out.println("Max value possible from Array : " + Arrays.toString(arr1) 
            + " = " + maxValueFromNonAdjacentElements(arr1) + ".");
        
        int [] arr2 = {20, 5, 1, 13, 6, 11, 40};
        System.out.println("Max value possible from Array : " + Arrays.toString(arr2) 
            + " = " + maxValueFromNonAdjacentElements(arr2) + ".");
        
        int [] arr3 = {-3 ,-2 ,-1 ,-10};
        System.out.println("Max value possible from Array : " + Arrays.toString(arr3) 
            + " = " + maxValueFromNonAdjacentElements(arr3) + ".");
        
        
        int [] arr4 = {-3,-2,4,5,-3,6};
        System.out.println("Max value possible from Array : " + Arrays.toString(arr4) 
            + " = " + maxValueFromNonAdjacentElements(arr4) + ".");
    }
}