/*
https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
https://www.geeksforgeeks.org/printing-solutions-n-queen-problem/

- The N Queen is the problem of placing N chess queens on an N × N chessboard so that no two queens 
    attack each other. For example, following is a solution for 4 Queen problem.
    { 0,  1,  0,  0}
    { 0,  0,  0,  1}
    { 1,  0,  0,  0}
    { 0,  0,  1,  0}
- Algorithm: 
1) Start in the leftmost column
2) If all queens are placed return true
3) Try all rows in the current column. 
   Do following for every tried row.
    a) If the queen can be placed safely in this row then mark this [row, column] as part of the 
       solution and recursively check if placing queen here leads to a solution.
    b) If placing the queen in [row, column] leads to a solution then return true.
    c) If placing queen doesn't lead to a solution then unmark this [row, column] (Backtrack) and 
        go to step (a) to try other rows.
3) If all rows have been tried and nothing worked, return false to trigger backtracking in the caller.
*/
public class BackTracking_08_NQueen {
    
    private static String getSolution(int[][] board, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    /*
    Checks if a queen can be placed on board[row][col]. Note that this method is called when "row" 
    queens are already placeed in rows from 0 to row - 1. So, we need to check only the top side 
    for attacking queens.
    */
    private static boolean isSafe(int[][] board, int n, int row, int column) {
        // Check this column
        for (int i = 0; i < row; i++) {
            if (board[i][column] == 1) {
                return false;
            }
        }
        // Check top-left diagonal
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        // Check top-right diagonal
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean solveNQueensProblemUtil(int[][] board, int n, int row) {
        if (row == n) {
            // If all queens are placed, then return true
            return true;
        }
        // Consider this row and try placing a queen in all columns one by one
        for (int column = 0; column < n; column++) {
            if (isSafe(board, n, row, column)) {
                // Queen can be placed on board[row][column]
                board[row][column] = 1;
                if (solveNQueensProblemUtil(board, n, row + 1)) {
                    return true;
                }
                // If placing queen in board[row][column] doesn't lead to a solution then, backtrack.
                board[row][column] = 0;
            }
        }
        return false;
    }

    static void solveNQueensProblem(int n) {
        int [][] board = new int [n][n];
        if (solveNQueensProblemUtil(board, n, 0)) {
            System.out.println("Solution for N-Queens problem when n = " + n + ":\n" + getSolution(board, n));
        } else {
            System.out.println("No solution found for N-Queens problem when n = " + n + ".");
        }
    }

    public static void main(String[] args) {
        solveNQueensProblem(4);
        solveNQueensProblem(8);
    }
}