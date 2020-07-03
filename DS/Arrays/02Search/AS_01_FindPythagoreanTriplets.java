import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/

- Given an array of integers, write a function that returns true if there is a pythagorean 
    triplet (a, b, c) that satisfies a^2 + b^2 = c^2
- Algorithm: (Using Hashing)
    * The problem can also be solved using hashing. We can use a hash map to mark all the 
        values of the given array. 
    * Using two loops, we can iterate for all the possible combinations of a and b, and then 
        check if there exists the third value c. 
    * If there exists any such value, then there is a Pythagorean triplet. 
*/
public class AS_01_FindPythagoreanTriplets { 
    
    static void findPythagoreanTriplet(int[] arr) {
        if (arr.length < 3) {
            return;
        }
        Set<Integer> squareSet = new HashSet<>();
        for (int num : arr) {
            squareSet.add(num * num);
        }

        for (int i = 0; i < arr.length; i++) {
            int squareNum1 = arr[i] * arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int squareNum2 = arr[j] * arr[j];
                int squareSum = squareNum1 + squareNum2;
                if (squareSet.contains(squareSum)) {
                    System.out.println(arr[i] + " ^ 2 + " + arr[j] + " ^ 2 = " 
                        + (int)Math.sqrt(squareSum) + " ^ 2");
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = { 3, 1, 4, 6, 5 }; 
        System.out.println("Array: " + Arrays.toString(arr));

        System.out.println("Pythagorean Triplets:");
        findPythagoreanTriplet(arr);
    }
}