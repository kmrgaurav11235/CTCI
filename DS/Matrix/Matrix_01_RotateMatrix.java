/*
https://www.geeksforgeeks.org/turn-an-image-by-90-degree/

- Given a matrix, turn it by 90 degrees in anti-clockwise direction. OR,
- Given an image, how will you turn it by 90 degrees (An image can be treated as 2D matrix which can be stored 
    in a buffer).
- Examples :
    1) Input Matrix:
        1  2  3
        4  5  6
        7  8  9
        Output:
        3  6  9 
        2  5  8 
        1  4  7 

    2) Input Matrix:
        1  2  3  4 
        5  6  7  8 
        9 10 11 12 
        13 14 15 16 
        Output:
        4  8 12 16 
        3  7 11 15 
        2  6 10 14 
        1  5  9 13
- The idea is simple. Transform each row of source matrix into required column of final image. We will use an auxiliary 
    buffer to transform the image.
- From the above examples, we can observe that
    * first row of source ------> first column of destination in reverse order
    * second row of source ------> second column of destination in reverse order
    and so on...
    Also, 
    * first column of source ------> last row of destination in same order
    * second column of source ------> last but-one row of destination in same order
- So matrix[i][j] ------> rotatedMatrix[n - 1 - j][i]

*/
public class Matrix_01_RotateMatrix {

    static void displayMatrix(int[][] matrix, int m, int n) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < m; i++) {
            sb.append("[ ");
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("]\n");
        }
        sb.append("]\n");
        System.out.println(sb);
    }

    static int[][] rotateMatrix(int[][] matrix, int m, int n) {
        int [][] rotatedMatrix = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotatedMatrix[n - 1 - j][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }
    public static void main(String[] args) {
        // Matrix 1
        int[][] matrix1 = 
                    { { 1, 2, 3, 4 }, 
                    { 5, 6, 7, 8 }, 
                    { 9, 10, 11, 12 } };
        
        System.out.println("Original Matrix 1:");
        displayMatrix(matrix1, 3, 4); 

        int[][] rotatedMatrix1 = rotateMatrix(matrix1, 3, 4);

        System.out.println("Rotated Matrix 1:");
        displayMatrix(rotatedMatrix1, 4, 3);

        // Matrix 2
        int[][] matrix2 = 
                    { {1, 2, 3}, 
                    {4, 5, 6}, 
                    {7, 8, 9} }; 
        
        System.out.println("Original Matrix 2:");
        displayMatrix(matrix2, 3, 3); 

        int[][] rotatedMatrix2 = rotateMatrix(matrix2, 3, 3);
        
        System.out.println("Rotated Matrix 2:");
        displayMatrix(rotatedMatrix2, 3, 3);
        
        // Matrix 3
        int[][] matrix3 = 
                    { {1, 2}, 
                    {4, 5} };
        
        System.out.println("Original Matrix 3:");
        displayMatrix(matrix3, 2, 2); 

        int[][] rotatedMatrix3 = rotateMatrix(matrix3, 2, 2);
        
        System.out.println("Rotated Matrix 3:");
        displayMatrix(rotatedMatrix3, 2, 2);        
    }
}