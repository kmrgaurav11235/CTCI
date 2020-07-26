import java.util.Arrays;

/*
https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/

- Consider a row of n coins of values v1...vn, where n is even. We play a game against an opponent by 
    alternating turns. In each turn, a player selects either the first or last coin from the row, 
    removes it from the row permanently, and receives the value of the coin. Determine the maximum 
    possible amount of money we can definitely win if we move first.
- Note: The opponent is as clever as the user.
- Examples:
    5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
    8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)

- Does choosing the best at each move gives an optimal solution? No.
- In the second example, this is how the game can be finished:
        * User chooses 8.
        * Opponent chooses 15.
        * User chooses 7.
        * Opponent chooses 3.
    Total value collected by user is 15(8 + 7)
    Vs,
        * User chooses 7.
        * Opponent chooses 8.
        * User chooses 15.
        * Opponent chooses 3.
    Total value collected by user is 22(7 + 15)

- So, if the user follows the second game state, the maximum value can be collected although the first 
    move is not the best.
- Approach: As both the players are equally strong, both will try to reduce the possibility of winning 
    of each other.
- We are trying to solve: optimalStrategyOfGame(value[], i , j).
    There are two choices:
    1) The user chooses the i-th coin with value value[i]: 
        The opponent either chooses (i+1)-th coin or j-th coin. The opponent intends to choose the coin 
        which leaves the user with minimum value.
            i.e. The user can collect the value: 
            value[i] + min(optimalStrategyOfGame(value, i+2, j), optimalStrategyOfGame(value, i+1, j-1))
    2) The user chooses the j-th coin with value value[j]: 
        The opponent either chooses i-th coin or (j-1)-th coin. The opponent intends to choose the coin 
        which leaves the user with minimum value. 
            i.e. the user can collect the value: 
            value[j] + min(optimalStrategyOfGame(value, i+1, j-1), optimalStrategyOfGame(value, i, j-2))

- Following is the recursive solution that is based on the above two choices:
    optimalStrategyOfGame(value, i, j) = Max(
        value[i] + min(optimalStrategyOfGame(value, i+2, j), optimalStrategyOfGame(value, i+1, j-1)), 
        value[j] + min(optimalStrategyOfGame(value, i+1, j-1), optimalStrategyOfGame(value, i, j-2)))
    As user wants to maximize the number of coins. 
- Base Cases:
    optimalStrategyOfGame(value, i, j) = value[i]                   If j == i
    optimalStrategyOfGame(value, i, j) = max(value[i], value[j])    If j == i + 1

- Complexity Analysis:
    Time Complexity: O(n ^ 2).
        Use of a nested for loop brings the time complexity to n ^ 2.
    Auxiliary Space: O(n ^ 2).
        As a 2D table is used for storing states.

*/
public class DP_16_OptimalStrategyForAGame_BU {

    static int optimalStrategyOfGame(int[] value, int n) {
        int [][] memo = new int[n][n];

        // Base cases
        for (int i = 0; i < n; i++) {
            memo[i][i] = value[i];
        }
        for (int i = 0; i < n - 1; i++) {
            memo[i][i + 1] = Math.max(value[i], value[i + 1]);
        }

        // Fill table using the above recursive formula. Note that the table is filled in diagonal 
        // fashion (similar to Palindrome Partitioning).
        for (int gap = 2; gap <= n; gap++) {
            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;
                memo[i][j] = Math.max(
                    value[i] + Math.min(memo[i + 2][j], memo[i + 1][j - 1]), 
                    value[j] + Math.min(memo[i + 1][j - 1], memo[i][j - 2])
                );
            }
        }

        return memo[0][n - 1];
    }

    public static void main(String[] args) {
        int arr1[] = { 8, 15, 3, 7 };
        int n = arr1.length;
        System.out.println("Coins: " + Arrays.toString(arr1) + ", maximum value: " + optimalStrategyOfGame(arr1, n));

        int arr2[] = { 2, 2, 2, 2 };
        n = arr2.length;
        System.out.println("Coins: " + Arrays.toString(arr2) + ", maximum value: " + optimalStrategyOfGame(arr2, n));

        int arr3[] = { 20, 30, 2, 2, 2, 10 };
        n = arr3.length;
        System.out.println("Coins: " + Arrays.toString(arr3) + ", maximum value: " + optimalStrategyOfGame(arr3, n));
    }
}