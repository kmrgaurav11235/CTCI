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
class DP_09_MinimumCostPath_TD {
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

    static int minCostPathUtil(int[][] cost, int m, int n, int i, int j, Integer [][] memo) {
        if (i > m || j > n) {
            // outside the matrix
            return Integer.MAX_VALUE;
        } else if (memo[i][j] != null) {
            return memo[i][j];
        } else if (i == m && j == n) {
            // last cell
            memo[i][j] = cost[i][j];
        } else {
            // Choice 1
            int traverseRightCell = minCostPathUtil(cost, m, n, i, j + 1, memo);

            // Choice 2
            int traverseBottomCell = minCostPathUtil(cost, m, n, i + 1, j, memo);

            // Choice 3
            int traverseDiagonalCell = minCostPathUtil(cost, m, n, i + 1, j + 1, memo);

            memo[i][j] = cost[i][j] + Math.min(traverseRightCell, Math.min(traverseBottomCell, traverseDiagonalCell));
        }
        return memo[i][j];
    }

    static int minCostPath(int[][] cost, int m, int n) {
        Integer [][] memo = new Integer[m][n];
        return minCostPathUtil(cost, m - 1, n - 1, 0, 0, memo);
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