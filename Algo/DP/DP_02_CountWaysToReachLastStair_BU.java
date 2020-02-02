/*
https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
There are n stairs, a person standing at the bottom wants to reach the top. The person can climb 
1, 3 or 4 stairs at a time (these numbers can change). Count the number of ways, the person can 
reach the top.

We can easily find recursive nature in above problem. The person can reach n’th stair from either 
(n-1)’th stair, from (n-3)’th stair or from (n-4)’th stair. Let the total number of ways to reach 
n’th stair be ‘ways(n)’. The value of ‘ways(n)’ can be written as following.
    ways(n) = ways(n-1) + ways(n-3) + ways(n-4)
The time complexity of the above implementation is exponential (golden ratio raised to power n). 
It can be Optimized using Dynamic Programming.
*/
class DP_02_CountWaysToReachLastStair_BU {

    static int countWaysToReachLastStair(int n) {
        int memo[] = new int [n + 1];
        memo[0] = 1;

        for (int i = 1; i <= n; i++) {
            int choice1 = i - 1, choice2 = i - 3, choice3 = i - 4;
            if (choice1 >= 0) {
                memo[i] += memo[choice1];
            }
            if (choice2 >= 0) {
                memo[i] += memo[choice2];
            }
            if (choice3 >= 0) {
                memo[i] += memo[choice3];
            }
        }

        return memo[n];
    }
    public static void main(String[] args) {
        System.out.println("When total number of stairs = 4, number of ways = " + countWaysToReachLastStair(4));
		System.out.println("When total number of stairs = 5, number of ways = " + countWaysToReachLastStair(5));
        System.out.println("When total number of stairs = 6, number of ways = " + countWaysToReachLastStair(6));
    }
}