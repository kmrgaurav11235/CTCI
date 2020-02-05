import java.util.Arrays;

/*
https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
Given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with 
n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum 
value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You 
cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
*/
class Recursion_05_ZeroOneKnapsack {
    static int knapSack(int[] weight, int[] value, int index, int capacity) {
        if (index == weight.length) {
            return 0;
        }

        // Choice 1: Include the item at this index
        int includeValue = 0;
        if (weight[index] <= capacity) {
            includeValue = value[index] + knapSack(weight, value, index + 1, capacity - weight[index]);
        }

        // Choice 2: Don't include the item at this index
        int excludeValue = knapSack(weight, value, index +1, capacity);
        
        return Math.max(includeValue, excludeValue);
    }

    public static void main(String[] args) {
        int value[] = new int[] { 60, 100, 120 };
        int weight[] = new int[] { 10, 20, 30 };
        int capacity = 50;
        System.out.println("Weights: " + Arrays.toString(weight));
        System.out.println("Values: " + Arrays.toString(value));
        System.out.println("Capacity of Knapsack: " + capacity);
        System.out.println("Total value of Knapsack: " + knapSack(weight, value, 0, capacity));
    }
}