/*
Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function that returns 
the number of paths that reach the last cell with a cost that is equal to a given cost. The
set-up is same as the previous problem. 
You can only traverse down and right (removed diagonal traversal from this one just for variance) 
from a given cell, i.e., from a given cell (i, j), cells (i+1, j) and (i, j+1) can be traversed. 
You may assume that all costs are positive integers.
*/
class DP_10_NumberOfPathsToReachTheLastCellAtAGivenCost_TD {
    static String toString(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder("\n[\n");
        for (int[] row : matrix) {
            stringBuilder.append("[");
            for (int cell : row) {
                stringBuilder.append(cell + " ");
            }
            stringBuilder.append("]\n");
        }
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

    static String toString(Integer [][] matrix) {
        StringBuilder stringBuilder = new StringBuilder("\n[\n");
        for (Integer[] row : matrix) {
            stringBuilder.append("[");
            for (Integer cell : row) {
                stringBuilder.append(cell + " ");
            }
            stringBuilder.append("]\n");
        }
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

    static int numberOfPathsToReachLastCellUtil(int[][] cost, int m, int n, int i, int j, int costAvailable, Integer [][] memo) {
        if (i > m || j > n) {
            // outside the matrix
            return 0;
        } else if (costAvailable < 0) {
            // Exhausted all cost that was permitted: no path found
            return 0;
        } else if (i == m && j == n) {
            // reached last cell
            if (costAvailable == cost[i][j]) {
                // reached last cell: with the correct amount of cost: 1 path found
                return 1;
            } else {
                // reached last cell: but NOT with the correct amount of cost: no path found
                return 0;
            }
        } else if (memo[i][j] != null) {
            return memo[i][j];
        }
            // Choice 1
            int traverseRightCell = numberOfPathsToReachLastCellUtil(cost, m, n, i, j + 1, costAvailable - cost[i][j], memo);

            // Choice 2
            int traverseBottomCell = numberOfPathsToReachLastCellUtil(cost, m, n, i + 1, j, costAvailable - cost[i][j], memo);

            memo[i][j] = traverseRightCell + traverseBottomCell;
        return memo[i][j];        
    }

    static int numberOfPathsToReachLastCell(int[][] cost, int m, int n, int minCost) {
        Integer[][] memo = new Integer[m][n];
        int num = numberOfPathsToReachLastCellUtil(cost, m - 1, n - 1, 0, 0, minCost, memo);
        System.out.println(toString(memo));
        return num;
    }

    public static void main(String[] args) {
        int[][] cost1 = { { 1, 2, 3 }, 
                        { 4, 8, 2 }, 
                        { 1, 5, 3 } };
        int minCost1 = 8;

        System.out.println("Cost Matrix 1: " + toString(cost1));
        System.out.println("The minimum number of paths with a cost less than equal to " + minCost1 + " are: "
                + numberOfPathsToReachLastCell(cost1, 3, 3, minCost1));

        int[][] cost2 = { { 4, 7, 8, 6, 4 }, 
                        { 6, 7, 3, 9, 2 }, 
                        { 3, 8, 1, 2, 4 }, 
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 } };
        int minCost2 = 36;

        System.out.println("\nCost Matrix 2: " + toString(cost2));
        System.out.println("The minimum number of paths with a cost less than equal to " + minCost2 + " are: "
                + numberOfPathsToReachLastCell(cost2, 5, 5, minCost2));

        int[][] cost3 = { { 4, 7, 1, 6 }, 
                        { 5, 7, 3, 9 }, 
                        { 3, 2, 1, 2 }, 
                        { 7, 1, 6, 3 } };
        int minCost3 = 25;

        System.out.println("\nCost Matrix 3: " + toString(cost3));
        System.out.println("The minimum number of paths with a cost less than equal to " + minCost3 + " are: "
                + numberOfPathsToReachLastCell(cost3, 4, 4, minCost3));
    }
}