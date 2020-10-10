import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/largest-rectangle-under-histogram/

- Find the largest rectangular area possible in a given histogram where the largest rectangle can 
    be made of a number of contiguous bars. For simplicity, assume that all bars have same width 
    and the width is 1 unit.

- Solution 1: Divide and Conquer O(n ^ 2)
    * The idea is to find the minimum value in the given array. Once we have index of the minimum 
        value, the max area is maximum of following three values:
        a) Maximum area in left side of minimum value (Not including the min value)
        b) Maximum area in right side of minimum value (Not including the min value)
        c) Number of bars multiplied by minimum value.
    * The areas in left and right of minimum value bar can be calculated recursively. If we use linear 
        search to find the minimum value, then the worst case time complexity of this algorithm becomes 
        O(n^2). 
    * In worst case, we always have (n-1) elements in one side and 0 elements in other side and if the 
        finding minimum takes O(n) time, we get the recurrence similar to worst case of Quick Sort.

- Solution 2: Stack O(n)
    * For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle. If we 
        calculate such area for every bar ‘x’ and find the maximum of all areas, our task is done. 
    * How to calculate area with ‘x’ as smallest bar? We need to know index of the first smaller (smaller 
        than ‘x’) bar on left of ‘x’ and index of first smaller bar on right of ‘x’. 
    * Let us call these indexes as ‘left index’ and ‘right index’ respectively.
    * We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack 
        once. A bar is popped from stack when a bar of smaller height is seen. When a bar is popped, 
        we calculate the area with the popped bar as smallest bar. 
    * How do we get left and right indexes of the popped bar? The current index tells us the ‘right 
        index’ and index of previous item in stack is the ‘left index’.

- Algorithm:
    1) Create an empty stack.
    2) Start from first bar, and do following for every bar ‘hist[i]’ where ‘i’ varies from 0 to n-1.
        a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
        b) If this bar is smaller than the top of stack, then keep removing the top of stack while top 
            of the stack is greater. Let the removed bar be hist[top]. Calculate area of rectangle with 
            hist[top] as smallest bar. For hist[top], the ‘left index’ is previous (previous to top) 
            item in stack and ‘right index’ is ‘i’ (current index).
    3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every 
        removed bar.
*/
public class Stack_07_LargestRectangularAreaInHistogram {
    static int getMaxArea(int[] hist, int n) {
        int maxArea = 0, currArea = 0;
        Deque<Integer> stack = new LinkedList<>();
        // The bars stored in stack are always in increasing order of their heights. 

        int i = 0;

        // Run through all bars of given histogram 
        while (i < n) {
            if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i++);
            } else {
                /* 
                If this bar is lower than top of stack, then calculate area of rectangle with stack top as 
                the smallest (or minimum height) bar. 'i' is 'right index'. The element before top in the 
                stack is 'left index' 
                */
                int top = stack.pop();
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                currArea = hist[top] * width;
                maxArea = Math.max(currArea, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            // Now pop the remaining bars from stack and calculate area with every popped bar as the smallest bar 
            int top = stack.pop();
            int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
            currArea = hist[top] * width;
            maxArea = Math.max(currArea, maxArea);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 }; 
        System.out.println("Maximum area is " + getMaxArea(hist, hist.length)); 
    }
}
