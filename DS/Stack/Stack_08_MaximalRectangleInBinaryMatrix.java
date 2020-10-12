import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/

- Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.
- Input:
        0 1 1 0
        1 1 1 1
        1 1 1 1
        1 1 0 0
    Output :
        1 1 1 1
        1 1 1 1
    Explanation : The largest rectangle with only 1's is from (1, 0) to (2, 3).

- Input:
        0 1 1
        1 1 1
        0 1 1      
    Output:
        1 1
        1 1
        1 1
    Explanation : 
    The largest rectangle with only 1's is from (0, 1) to (2, 2).

- One method of solving this for "Maximal Square" is discussed in the Dynamic Programming section.
- Stack-based approach: This approach uses largest rectangle under histogram as a subroutine.
- If the height of bars of the histogram is given then the largest area of the histogram can be found. 
    This way, in each row, the largest area of bars of the histogram can be found. 
- To get the largest rectangle full of 1’s, update the next row with the previous row and find the largest 
    area under the histogram. i.e. consider each 1’s as filled squares and 0’s as an empty square and 
    consider each row as the base.
- Illustration:
    Input :
        0 1 1 0
        1 1 1 1
        1 1 1 1
        1 1 0 0
    Step 1: 
        0 1 1 0  maximum area  = 2
    Step 2:
        row 1  1 2 2 1  area = 4, maximum area becomes 4
        row 2  2 3 3 2  area = 8, maximum area becomes 8
        row 3  3 4 0 0  area = 6, maximum area remains 8
- Algorithm:
    1) Run a loop to traverse through the rows.
    2) Now If the current row is not the first row then update the row as follows, if matrix[i][j] is 
        not zero then matrix[i][j] = matrix[i-1][j] + matrix[i][j].
    3) Find the maximum rectangular area under the histogram, consider the ith row as heights of bars 
        of a histogram.
    4) Do the previous two steps for all rows and print the maximum area of all the rows.
- Time Complexity: O(m x n).
    Only one traversal of the matrix is required.
- Space Complexity: O(n).
    Stack is required to store the columns.

*/
public class Stack_08_MaximalRectangleInBinaryMatrix {
    static class HistogramResult {
        int left, right, height, area;

        public HistogramResult(int left, int right, int height, int area) {
            this.left = left;
            this.right = right;
            this.height = height;
            this.area = area;
        }
    }

    private static HistogramResult maxAreaUnderHistogram(int [] hist, int n) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0, currArea = 0;
        int left = -1, right = -1, height = -1;

        int i = 0;
        while (i < n) {
            if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int leftBoundary = (stack.isEmpty()) ? (-1) : (stack.peek());
                int rightBoundary = i;
                currArea = hist[top] * (rightBoundary - leftBoundary - 1);

                if (currArea > maxArea) {
                    maxArea = currArea;
                    left = leftBoundary + 1;
                    right = rightBoundary - 1;
                    height = hist[top];
                }
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int leftBoundary = (stack.isEmpty()) ? (-1) : (stack.peek());
            int rightBoundary = i;
            currArea = hist[top] * (rightBoundary - leftBoundary - 1);

            if (currArea > maxArea) {
                maxArea = currArea;
                left = leftBoundary + 1;
                right = rightBoundary - 1;
                height = hist[top];
            }
        }

        return new HistogramResult(left, right, height, maxArea);
    }

    static void printMaxRectangle(int[][] mat, int m, int n) {
        int maxArea = 0, left = -1, right = -1, height = 0, base = 0;
        int [] currRowHistogram = new int [n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0) {
                    // First row: base case
                    currRowHistogram[col] = mat[row][col];
                } else {
                    // Other rows
                    if (mat[row][col] == 0) {
                        currRowHistogram[col] = 0;
                    } else {
                        currRowHistogram[col] += mat[row][col];
                    }
                }
            } // end of for-col

            // Max area under histogram for this column
            HistogramResult histogramResult = maxAreaUnderHistogram(currRowHistogram, n);

            if (histogramResult.area > maxArea) {
                maxArea = histogramResult.area;
                left = histogramResult.left;
                right = histogramResult.right;
                height = histogramResult.height;
                base = row;
            }
        } // end of for-row

        System.out.println("Maximum size rectangle sub-matrix with all 1s:");
        displayMatrix(mat, base - height + 1, base, left, right);
        System.out.println("Max Area: " + maxArea);
    }
    
    private static void displayMatrix(int[][] mat, int top, int bottom, int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                sb.append(mat[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void main(String[] args) {
  
        int [][] mat1 = { 
            { 0, 1, 1, 0 }, 
            { 1, 1, 1, 1 }, 
            { 1, 1, 1, 1 }, 
            { 1, 1, 0, 0 }, 
        }; 
        printMaxRectangle(mat1, mat1.length, mat1[0].length);

        int [][] mat2 = {
            { 0, 1, 1 },
            { 1, 1, 1 },
            { 0, 1, 1 }  
        };
        printMaxRectangle(mat2, mat2.length, mat2[0].length);
    }
}
