/*
https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

- Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
- A region is captured by flipping all 'O's into 'X's in that surrounded region.
- Example:
    X X X X
    X O O X
    X X O X
    X O X X
- After running your function, the board should be:
    X X X X
    X X X X
    X X X X
    X O X X
- Explanation: Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the 
    board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the 
    border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally 
    or vertically.
- This is mainly an application of Flood-Fill algorithm:
    https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
- The main difference here is that a ‘O’ is not replaced by ‘X’ if it lies in region that ends on a boundary. 
- Following are simple steps to do this special flood fill.
    1) Traverse the given matrix and replace all ‘O’ with a special character ‘-‘.
    2) Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) for every ‘-‘ on edges. The remaining 
        ‘-‘ are the characters that indicate ‘O’s (in the original matrix) to be replaced by ‘X’.
    3) Traverse the matrix and replace all ‘-‘s with ‘X’s. 
*/
public class Graph_09_SurroundedRegions {
    private static void replace(char[][] mat, int m, int n, char source, char dest) {
        // Replace the char 'source' with the char 'dest'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == source) {
                    mat[i][j] = dest;
                }
            }
        }
    }

    private static void floodFill(char[][] mat, int m, int n, char source, char dest, int i, int j) {
        // Simple Flood-fill
        int [] x = {0, -1, 0, 1};
        int [] y = {-1, 0, 1, 0};
        int len = 4;

        mat[i][j] = dest;

        for (int k = 0; k < len; k++) {
            int newI = i + x[k];
            int newJ = j + y[k];
            // mat[newI][newJ] are all the surrounding cells

            if (0 <= newI && newI < m && 0 <= newJ && newJ < n) {
                // This is a valid cell
                if (mat[newI][newJ] == source) {
                    floodFill(mat, m, n, source, dest, newI, newJ);
                }
            }
        }
    }
    
    static void replaceSurroundedRegions(char[][] mat, int m, int n) {
        // Step 1: Traverse the given matrix and replace all ‘O’ with a special character ‘-‘.
        replace(mat, m, n, 'O', '-');

        /*
        Step 2:
        Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) for every ‘-‘ on edges. The remaining 
        ‘-‘ are the characters that indicate ‘O’s (in the original matrix) to be replaced by ‘X’.
        */
        for (int i = 0; i < m; i++) {
            if (mat[i][0] == '-') {
                floodFill(mat, m, n, '-', 'O', i, 0);
            }
            if (mat[i][n - 1] == '-') {
                floodFill(mat, m, n, '-', 'O', i, n - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (mat[0][i] == '-') {
                floodFill(mat, m, n, '-', 'O', 0, i);
            }
            if (mat[m - 1][i] == '-') {
                floodFill(mat, m, n, '-', 'O', m - 1, i);
            }
        }

        // Step 3: Traverse the matrix and replace all ‘-‘s with ‘X’s. 
        replace(mat, m, n, '-', 'X');
    }

    public static void main(String[] args) {
        char[][] mat = { 
                        { 'X', 'O', 'X', 'O', 'X', 'X' }, 
                        { 'X', 'O', 'X', 'X', 'O', 'X' },
                        { 'X', 'X', 'X', 'O', 'X', 'X' }, 
                        { 'O', 'X', 'X', 'X', 'X', 'X' }, 
                        { 'X', 'X', 'X', 'O', 'X', 'O' },
                        { 'O', 'O', 'X', 'O', 'O', 'O' } };
        int m = mat.length;
        int n = mat[0].length;

        System.out.println("Original Matrix:");
        displayMatrix(mat, m, n);

        replaceSurroundedRegions(mat, m, n);

        System.out.println("\nMatrix after replacing surrounded regions:");
        displayMatrix(mat, m, n);
    }

    private static void displayMatrix(char[][] mat, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
