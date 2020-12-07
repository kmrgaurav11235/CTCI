import java.util.Arrays;

/*
https://www.geeksforgeeks.org/majority-element/
https://leetcode.com/problems/majority-element/discuss/51611/Java-solutions-(sorting-hashmap-moore-voting-bit-manipulation).

- Write a method which takes an array and prints the majority element (if it exists), otherwise 
    prints "No Majority Element". A majority element in an array A[] of size n is an element that 
    appears more than n/2 times (and hence there is at most one such element).
- Examples :
    Input : {3, 3, 4, 2, 4, 4, 2, 4, 4}
    Output : 4
    Explanation: The frequency of 4 is 5 which is greater than the half of the size of the array size. 

    Input : {3, 3, 4, 2, 4, 4, 2, 4}
    Output : No Majority Element
    Explanation: There is no element whose frequency is greater than the half of the size of the array 
        size. 

- Moore’s Voting Algorithm: This is a two-step process:
    The first step gives the element that maybe the majority element in the array. If there is a majority 
    element in an array, then this step will definitely return majority element. Otherwise, it will return 
    candidate for majority element. Then, we can check if the element obtained from the above step is majority 
    element. This step is necessary as there might be no majority element.

- Step 1: Finding a Candidate
    The algorithm for the first phase that works in O(n) is known as Moore’s Voting Algorithm. Basic idea of 
    the algorithm is that if each occurrence of an element e can be cancelled with all the other elements that 
    are different from e then e will exist till end if it is a majority element.

- Step 2: Check if the element obtained in step 1 is majority element or not.
    Traverse through the array and check if the count of the element found is greater than half the size of 
    the array, then print the answer else print "No majority element".

- Algorithm:
    1. Loop through each element and maintains a count of majority element, and a majority index, majIndex
    2. If the next element is same then increment the count. If the next element is different then decrement 
        the count.
    3. If the count reaches 0 then changes the majIndex to the current element and set the count again to 1.
    4. Now again traverse through the array and find the count of majority element found.
    5. After the loop ends, start another loop and check if the count is greater than half the size of the 
    array, print the element. Else print that there is no majority element
*/
public class AS_04_MajorityElement {
    static void printMajorityElement(int[] nums, int n) {
        int majorityCandidate = getMajorityCandidate(nums, n);

        int countRequired = (n / 2) + 1;
        int countOfCandidate = 0;
        for (int num : nums) {
            if (num == majorityCandidate) {
                countOfCandidate++;
            }
            if (countOfCandidate == countRequired) {
                System.out.println("Array: " + Arrays.toString(nums) + ". Majority Element in array: " + majorityCandidate);
                return;
            }
        }
        System.out.println("Array: " + Arrays.toString(nums) + ". No Majority Element found.");
    }
    private static int getMajorityCandidate(int [] nums, int n) {
        // Moore’s Voting Algorithm
        int majorityIndex = 0, count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[majorityIndex]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }
        return nums[majorityIndex];
    }
    public static void main(String[] args) {
        int a1[] = {1, 3, 3, 1, 2}; 
        printMajorityElement(a1, a1.length); 
        
        int a2[] = {3, 3, 4, 2, 4, 4, 2, 4, 4}; 
        printMajorityElement(a2, a2.length); 
    }
}
