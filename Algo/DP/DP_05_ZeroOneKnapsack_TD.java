import java.util.Arrays;

/*
https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
Given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with 
n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum 
value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You 
cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

For this problem, it is possible that we do not get many overlapping sub-problems. Still, we can use DP
and it will re-use solutions in the few cases that it can.
*/
class DP_05_ZeroOneKnapsack_TD {
    private static int knapSackUtil(int[] weight, int[] value, int n, int index, int capacity, Integer [][] memo) {
        if (index == n) {
            return 0;
        } else if (capacity == 0) {
            // No more capacity left -> no more value can be added
            return 0;
        } else if (memo[index][capacity] == null) {
            // Choice 1: Include the item at this index
            int includeValue = 0;
            if (weight[index] <= capacity) {
                includeValue = value[index] + knapSackUtil(weight, value, n, index + 1, capacity - weight[index], memo);
            }

            // Choice 2: Don't include the item at this index
            int excludeValue = knapSackUtil(weight, value, n, index + 1, capacity, memo);
            
            memo[index][capacity] = Math.max(includeValue, excludeValue);
        }
        return memo[index][capacity];
    }
    static int knapSack(int[] weight, int[] value, int n, int capacity) {
        Integer [][] memo = new Integer [n][capacity + 1];
        // capacity + 1 is used as the weight 'capacity' must be included in the array

        int result = knapSackUtil(weight, value, n, 0, capacity, memo);
        
        // Printing the memo array to view the sub-problems solved
        // System.out.println(Arrays.deepToString(memo));

        return result;
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