/*
https://www.geeksforgeeks.org/sudoku-backtracking-7/

- Given a partially filled 9 x 9 2D array grid[][], the goal is to assign digits (from 1 to 9) to the 
    empty cells so that every row, column, and subgrid of size 3 x 3 contains exactly one instance of 
    the digits from 1 to 9. 
- Example:
    Input:
    grid = { 
            {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
            }
    Output:
            3 1 6 | 5 7 8 | 4 9 2
            5 2 9 | 1 3 4 | 7 6 8
            4 8 7 | 6 2 9 | 5 3 1
            ---------------------
            2 6 3 | 4 1 5 | 9 8 7
            9 7 4 | 8 6 3 | 1 2 5
            8 5 1 | 7 9 2 | 6 4 3
            ---------------------
            1 3 8 | 9 4 7 | 2 5 6
            6 9 2 | 3 5 1 | 8 7 4
            7 4 5 | 2 8 6 | 3 1 9

- Algorithm:
    1) Create a function that checks if, after assigning the current index, the grid becomes unsafe or 
        not. Keep Hashmap for a row, column and boxes. If any number has a frequency greater than 1 in 
        the hashMap return false else return true; hashMap can be avoided by using loops.
    2) Create a recursive function which takes a grid.
    3) Check for any unassigned location. If present then assign a number from 1 to 9. Check if assigning 
        the number to current index makes the grid unsafe or not. If safe, then recursively call the 
        function for all safe cases from 0 to 9. If any recursive call returns true, end the loop and 
        return true. If no recursive call returns true then return false.
    4) If there is no unassigned location then return true.

- Complexity Analysis:
    * Time complexity: O(9 ^ (n x n)).
        For every unassigned index, there are 9 possible options so the time complexity is O(9^(n x n)). 
        The time complexity remains the same but there will be some early pruning. So, the time taken will 
        be much less than the naive algorithm but the upper bound time complexity remains the same.
    * Space Complexity: O(n x n).
        To store the output array a matrix is needed.

*/
public class BackTracking_09_Sudoku {

    static String getSolutionString(int[][] board, int n) {
        int m = (int) Math.sqrt(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // For horizontal division
            if (i != 0 && i % m == 0) {
                sb.append("---------------------\n");
            }

            for (int j = 0; j < n; j++) {
                if (j != 0 && j % m == 0) {
                    // For vertical division
                    sb.append("| ");
                }
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private static boolean isSafe(int[][] board, int n, int row, int column, int num) {
        for (int i = 0; i < n; i++) {
            // Check row
            if (board[row][i] == num) {
                return false;
            }
            // Check column
            if (board[i][column] == num) {
                return false;
            }
        }
        // Check the small-box matrix
        int m = (int) Math.sqrt(n);
        int startRow = row - (row % m);
        int startColumn = column - (column % m);
        for (int i = startRow; i < startRow + m; i++) {
            for (int j = startColumn; j < startColumn + m; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        // if there is no clash, it is safe.
        return true;
    }

    static boolean solveSudoku(int[][] board, int n) {
        // Find one empty cell and fill it with all choices one by one until we get the recursively
        // correct solution
        int row = -1, column = -1;
        boolean isSolved = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    // Found an empty cell
                    row = i;
                    column = j;
                    isSolved = false;
                    break;
                }
            }
            if (!isSolved) {
                break;
            }
        }

        if (isSolved) {
            return true;
        }

        // Solve for the cell: board[row][column]
        for (int num = 1; num <= n; num++) {
            if (isSafe(board, n, row, column, num)) {
                board[row][column] = num;
                if (solveSudoku(board, n)) {
                    return true;
                }
                // Backtrack
                board[row][column] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] { 
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, 
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } 
        }; 
        int n = board.length; 
  
        if (solveSudoku(board, n)) { 
            System.out.println("Solution:\n" + getSolutionString(board, n)); 
        } 
        else { 
            System.out.println("No solution."); 
        } 
    }
}