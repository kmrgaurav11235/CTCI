/*
https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/

- Given a square matrix, turn it by 90 degrees in clockwise direction without using any extra space.
- Examples :
    1) Input Matrix:
        1  2  3
        4  5  6
        7  8  9
        Output:
        7  4  1 
        8  5  2 
        9  6  3 

    2) Input Matrix:
        1   2  3  4 
        5   6  7  8 
        9  10 11 12 
        13 14 15 16 
        Output:
        13  9  5  1 
        14 10  6  2 
        15 11  7  3 
        16 12  8  4
- Approach: To solve the question without any extra space, rotate the matrix in form of squares, dividing the matrix into 
    squares or cycles.
- For example, a 4 X 4 matrix will have 2 cycles. The first cycle is formed by its 1st row, last column, last row and 1st 
    column. The second cycle is formed by 2nd row, second-last column, second-last row and 2nd column. 
- The idea is that, for each square cycle, swap the elements involved with the corresponding cell in the matrix in anti-clockwise 
    direction i.e. from top to left, left to bottom, bottom to right and from right to top one at a time using nothing but 
    a temporary variable to achieve this.
*/
public class Matrix_02_RotateSquareMatrixInplace {

    static void displayMatrix(int[][] matrix, int n) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < n; i++) {
            sb.append("[ ");
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("]\n");
        }
        sb.append("]\n");
        System.out.println(sb);
    }

    static void rotateMatrix(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // Save offset
                int temp = matrix[first][i];
                // left -> top
                matrix[first][i] = matrix[last - offset][first];
                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];
                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                // top -> right
                matrix[i][last] = temp;
            }
        }
    }
    public static void main(String[] args) {
        // Matrix 1
        int[][] matrix1 = 
                    { { 1, 2, 3, 4 }, 
                    { 5, 6, 7, 8 }, 
                    { 9, 10, 11, 12 },
                    { 13, 14, 15, 16} };
        
        System.out.println("Original Matrix 1:");
        displayMatrix(matrix1, 4); 

        rotateMatrix(matrix1, 4);

        System.out.println("Rotated Matrix 1:");
        displayMatrix(matrix1, 4); 

        // Matrix 2
        int[][] matrix2 = 
                    { {1, 2, 3}, 
                    {4, 5, 6}, 
                    {7, 8, 9} }; 
        
        System.out.println("Original Matrix 2:");
        displayMatrix(matrix2, 3); 

        rotateMatrix(matrix2, 3);
        
        System.out.println("Rotated Matrix 2:");
        displayMatrix(matrix2, 3); 
        
        // Matrix 3
        int[][] matrix3 = 
                    { {1, 2}, 
                    {4, 5} };
        
        System.out.println("Original Matrix 3:");
        displayMatrix(matrix3, 2); 

        rotateMatrix(matrix3, 2);
        
        System.out.println("Rotated Matrix 3:");
        displayMatrix(matrix3, 2);      
    }
}