/*
https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/

- The knight is placed on the first block of an empty board and, moving according to the rules of chess, must 
    visit each square exactly once. 
- We start from an empty solution vector and one by one add items (a Knight’s move). When we add an item, we 
    check if adding the current item violates the problem constraint. If it does then we remove the item and 
    try other alternatives. 
- If none of the alternatives work out then we go to previous stage and remove the item added in the previous 
    stage. If we reach the initial stage back then we say that no solution exists. 
- If adding an item doesn’t violate constraints then we recursively add items one by one. If the solution vector 
    becomes complete then we print the solution.
- Algorithm:
    1) If all squares are visited 
        print the solution
    2) Else
        a) Add one of the next moves to solution vector and recursively check if this move leads to a solution. 
            (A Knight can make maximum eight moves. We choose one of the 8 moves in this step).
        b) If the move chosen in the above step doesn't lead to a solution then remove this move from the solution 
            vector and try other alternative moves.
        c) If none of the alternatives work then return false (Returning false will remove the previously added item 
            in recursion and if false is returned by the initial call of recursion then "no solution exists" )

*/
public class BackTracking_07_KnightsTour {

    private static void printSolution(int[][] chessBoard, int n) {
        System.out.println("Knight's Tour Problems -- Solution:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static boolean isValid(int[][] chessBoard, int n, int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < n && chessBoard[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean solveKnightsTourUtil(int[][] chessBoard, int n, int moves, int x, int y, int[] moveX,
            int[] moveY) {
        
        moves++;
        chessBoard[x][y] = moves;
        if (moves == n * n) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            int newX = x + moveX[i];
            int newY = y + moveY[i];

            if (isValid(chessBoard, n, newX, newY) && solveKnightsTourUtil(chessBoard, n, moves, newX, newY, moveX, moveY)) {
                return true;
            }
        }
        // Backtracking
        chessBoard[x][y] = 0;
        return false;
    }

    static void solveKnightsTour() {
        int n = 8;
        int[][] chessBoard = new int[n][n];

        int[] moveX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] moveY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (solveKnightsTourUtil(chessBoard, n, 0, 0, 0, moveX, moveY)) {
                    printSolution(chessBoard, n);
                    return;
                }
            }
        }

        System.out.println("Knight's Tour Problems -- No solution found.");
    }

    public static void main(String[] args) {
        solveKnightsTour();
    }
}