/*
https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/

- Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.
- Input:
        0  1  1  0  1
        1  1  0  1  0
        0  1  1  1  0
        1  1  1  1  0
        1  1  1  1  1
        0  0  0  0  0
    Output :
        1 1 1 
        1 1 1 
        1 1 1 
    Explanation : The largest square with only 1's is from (2, 1) to (4, 3).

- One method of solving Maximal "Rectangle" is discussed in the Stack section.
- DP Algorithm for Maximal Square:
    * Let the given binary matrix be M[R][C]. The idea of the algorithm is to construct an auxiliary 
        size matrix S[][] in which each entry S[i][j] represents size of the square sub-matrix with 
        all 1s including M[i][j] where M[i][j] is the rightmost and bottommost entry in sub-matrix.

    1) Construct a sum matrix S[R][C] for the given M[R][C].
        a) Copy first row and first columns as it is from M[][] to S[][]
        b) For other entries, use following expressions to construct S[][]
            If M[i][j] is 1 then
                S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
            Else // If M[i][j] is 0
                S[i][j] = 0
    2) Find the maximum entry in S[R][C]
    3) Using the value and coordinates of maximum entry in S[i], print sub-matrix of M[][].

- Time Complexity: O(m*n)
- Auxiliary Space: O(m*n)
*/
public class DP_17_MaximalSquareInBinaryMatrix {
    static void printMaxSubSquare(int[][] mat, int m, int n) {
        int [][] sum = new int [m][n];

        // Set first row of sum[][]
        for (int i = 0; i < m; i++) {
            sum[i][0] = mat[i][0];
        }

        // Set first column of sum[][]
        for (int i = 0; i < n; i++) {
            sum[0][i] = mat[0][i];
        }

        // Construct other entries of sum[][]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][j] == 0) {
                    sum[i][j] = 0;
                } else {
                    sum[i][j] = 1 + Math.min(sum[i][j - 1], Math.min(sum[i - 1][j], sum[i - 1][j - 1]));
                }
            }
        }

        // Find the maximum entry, and indexes of maximum entry in sum[][]
        int maxSum = -1, maxI = -1, maxJ = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (sum[i][j] > maxSum) {
                    maxSum = sum[i][j];
                    maxI = i;
                    maxJ = j;
                } 
            }
        }

        StringBuilder maxSubSquare = new StringBuilder();
        for (int i = maxI; i > maxI - maxSum; i--) {
            for (int j = maxJ; j > maxJ - maxSum; j--) {
                maxSubSquare.append(mat[i][j] + " ");
            }
            maxSubSquare.append("\n");
        }
        System.out.print(maxSubSquare);
    }

    public static void main(String[] args) {
        int mat[][] = {{0, 1, 1, 0, 1},  
                    {1, 1, 0, 1, 0},  
                    {0, 1, 1, 1, 0}, 
                    {1, 1, 1, 1, 0}, 
                    {1, 1, 1, 1, 1}, 
                    {0, 0, 0, 0, 0}}; 
              
        printMaxSubSquare(mat, mat.length, mat[0].length); 
    }
}
