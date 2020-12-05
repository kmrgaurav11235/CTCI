/*
https://www.geeksforgeeks.org/next-greater-element/
https://www.geeksforgeeks.org/next-greater-element-in-same-order-as-input/

- Given an array, find the Next Greater Element (NGE) for every element. The NGE for an element x is 
    the first greater element on the right side of x in array. 
- Elements for which no greater element exist, consider next greater element as -1.

- Examples:
    * For any array, rightmost element always has next greater element as -1.
    * For an array which is sorted in decreasing order, all elements have next greater element as -1.
    * For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
        Element       NGE
        4      -->    5
        5      -->   25
        2      -->   25
        25     -->   -1
    * For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.
        Element        NGE
        13      -->     -1
        7       -->     12
        6       -->     12
        12      -->     -1
- Algorithm:
    1) In this approach we have started iterating from the last element(nth) to the first(1st) element.
    2) The benefit is that when we arrive at a certain index its next greater element will be already 
        in stack and we can directly get this element while at the same index.
    3) After reaching a certain index we will pop the stack till we get the greater element on top from 
        the current element and that element will be the answer for current element
    4) If stack gets empty while doing the pop operation then the answer would be -1
    5) Then, we will store the answer in an array for the current index.

*/
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Stack_05_NextGreaterElement {

    private static int[] getNextGreaterElements(int[] arr, int n) {
        int [] nge = new int [n];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = stack.peek();
            }

            stack.push(arr[i]);
        }
        return nge;
    }

    public static void main(String[] args) {
        int arr[] = { 11, 13, 21, 3 }; 
        int n = arr.length; 
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Next Greater Element Array: " + Arrays.toString(getNextGreaterElements(arr, n)));
    }
}