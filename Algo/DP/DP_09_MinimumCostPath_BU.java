/*
https://www.geeksforgeeks.org/min-cost-path-dp-6/
Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function that returns 
the cost of minimum-cost-path to reach (m, n) from (0, 0). Each cell of the matrix represents 
a cost to traverse through that cell. Total cost of a path to reach (m, n) is sum of all the 
costs on that path (including both source and destination). 
You can only traverse down, right and diagonally lower cells from a given cell, i.e., from a 
given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed. You may assume 
that all costs are positive integers.
*/
class DP_09_MinimumCostPath_BU {
    static String toString(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder("\n[\n");
        for (int[] row: matrix) {
            stringBuilder.append("[");
            for (int cell : row) {
                stringBuilder.append(cell + " ");
            }
            stringBuilder.append("]\n");
        }
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

    static int minCostPath(int[][] cost, int m, int n) {
        Integer [][] memo = new Integer[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            memo[i][n] = Integer.MAX_VALUE;
        }
        for (int j = 0; j <= n; j++) {
            memo[m][j] = Integer.MAX_VALUE;
        }
        memo[m][n] = 0; // To make sure memo[m - 1][n - 1] = cost[m - 1][n - 1]

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Choices: Traverse Diagonal cell, Right cell or Bottom cell.
                memo[i][j] = cost[i][j] + Math.min(memo[i + 1][j + 1], Math.min(memo[i][j + 1], memo[i + 1][j]));
            }
        }
        return memo[0][0];
    }

    public static void main(String[] args) {
        int[][] cost1 = { { 1, 2, 3 }, 
                        { 4, 8, 2 }, 
                        { 1, 5, 3 } };

        System.out.println("Cost Matrix 1: " + toString(cost1));
        System.out.println("The minimum cost is " + minCostPath(cost1, 3, 3));

        int[][] cost2 = { { 4, 7, 8, 6, 4 }, 
                        { 6, 7, 3, 9, 2 }, 
                        { 3, 8, 1, 2, 4 }, 
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 } };
        System.out.println("\nCost Matrix 2: " + toString(cost2));
        System.out.println("The minimum cost is " + minCostPath(cost2, 5, 5));
    }
}