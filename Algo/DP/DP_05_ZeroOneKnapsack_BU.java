import java.util.Arrays;

/*
https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
Given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with 
n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum 
value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You 
cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

For this problem, it is possible that we do not get many overlapping sub-problems. Still, we can use DP
and it will re-use solutions in the few cases that it can.

The bottom-up case also solves too many unnecessary sub-problems, especially if the gap between weights is
big.
*/
class DP_05_ZeroOneKnapsack_BU {
    static int knapSack(int[] weight, int[] value, int n, int capacity) {
        int [][] memo = new int [n + 1][capacity + 1];
        // capacity + 1 is used as the weight 'capacity' must be included in the array
        // n + 1 is used to avoid array index issues. This dummy row will be full of zeros.

        for (int i = 0; i <= n; i++) {
            // base case: capacity == 0
            memo[i][0] = 0;
        }
        for (int j = 0; j <= capacity; j++) {
            // Insert 0 in last row as it is dummy column to avoid array index issues
			memo[n][j] = 0;
		}

        for (int i = n - 1; i >= 0; i--) {
            // Work backwards for weight/value indexes
            for (int j = 1; j <= capacity; j++) {
                // Work forwards in capacity index

                // Choice 1: Include the item at this index
                int includeValue = 0;
                if (weight[i] <= j) {
                    includeValue = value[i] + memo[i + 1][j - weight[i]];
                }

                // Choice 2: Don't include the item at this index
                int excludeValue = memo[i + 1][j];
                
                memo[i][j] = Math.max(includeValue, excludeValue);
            }
        }

        // Printing the memo array to view the sub-problems solved
        // System.out.println(Arrays.deepToString(memo));

        return memo[0][capacity];
    }

    public static void main(String[] args) {
        int value[] = new int[] { 60, 100, 120 };
        int weight[] = new int[] { 10, 20, 30 };
        int capacity = 50;
        System.out.println("Weights: " + Arrays.toString(weight));
        System.out.println("Values: " + Arrays.toString(value));
        System.out.println("Capacity of Knapsack: " + capacity);
        System.out.println("Total value of Knapsack: " + knapSack(weight, value, 3, capacity));
    }
}