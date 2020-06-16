/*
https://leetcode.com/problems/maximum-product-of-three-numbers/discuss/104729/Java-O(1)-space-O(n)-time-solution-beat-100
https://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/

- Given an integer array, find a maximum product of a triplet in array.
    Examples: 
    Input:  [10, 3, 5, 6, 20]
    Output: 1200
    
    Input:  [-10, -3, -5, -6, -20]
    Output: -90

    Input:  [1, -4, 3, -6, 7, 0]
    Output: 168
- Algorithm:
    1) Scan the array and compute Maximum, second maximum and third maximum element present 
        in the array.
    2) Scan the array and compute Minimum and second minimum element present in the array.
    3) Return the maximum of product of Maximum, second maximum and third maximum and product 
        of Minimum, second minimum and Maximum element.
- Step 1 and Step 2 can be done in single traversal of the array.
*/
public class Arrays_04_MaximumProductOfTriplet {

    static int getMaxProductOfTriplet(int[] arr) {
        if (arr == null || arr.length < 3) {
            return Integer.MIN_VALUE;
        }
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : arr) {
            // Update Maxes
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            // Update Mins
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
    public static void main(String[] args) {
        int arr[] = { 1, -4, 3, -6, 7, 0 }; 
    
        System.out.println("Maximum product of a triplet is " + getMaxProductOfTriplet(arr)); 
    }
}