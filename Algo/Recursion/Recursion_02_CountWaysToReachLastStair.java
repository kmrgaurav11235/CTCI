import java.util.ArrayList;
import java.util.List;

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
class Recursion_02_CountWaysToReachLastStair {
    static int countWaysToReachLastStair(int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        } else {
            return countWaysToReachLastStair(n - 1) + countWaysToReachLastStair(n - 3)
                + countWaysToReachLastStair(n - 4);
        }
    }
    static int countAndPrintWaysToReachLastStair(int n, List<Integer> steps) {
        if (n == 0) {
            System.out.println(steps);
            return 1;
        } else if (n < 0) {
            return 0;
        } else {
            // Choose 1
            steps.add(1);
            int n1 = countAndPrintWaysToReachLastStair(n - 1, steps);
            // Un-choose 1
            steps.remove(steps.size() - 1);

            // Choose 3
            steps.add(3);
            int n2 = countAndPrintWaysToReachLastStair(n - 3, steps);
            // Un-choose 3
            steps.remove(steps.size() - 1);

            // Choose 4
            steps.add(4);
            int n3 = countAndPrintWaysToReachLastStair(n - 4, steps);
            // Un-choose 4
            steps.remove(steps.size() - 1);

            return n1 + n2 + n3;
        }
    }
    public static void main(String[] args) {
        System.out.println("When total number of stairs = 4, number of ways = " + countWaysToReachLastStair(4));
		System.out.println("When total number of stairs = 5, number of ways = " + countWaysToReachLastStair(5));
        System.out.println("When total number of stairs = 6, number of ways = " + countWaysToReachLastStair(6));

        List<Integer> steps = new ArrayList<>();
        int ways;
        
        System.out.println("\nWhen total number of stairs = 4, ways: ");
        ways = countAndPrintWaysToReachLastStair(4, steps);
        System.out.println("Number of ways = " + ways);

        steps = new ArrayList<>();

        System.out.println("\nWhen total number of stairs = 5, ways: ");
        ways = countAndPrintWaysToReachLastStair(5, steps);
        System.out.println("Number of ways = " + ways);
        
        steps = new ArrayList<>();

		System.out.println("\nWhen total number of stairs = 6, ways: ");
        ways = countAndPrintWaysToReachLastStair(6, steps);
        System.out.println("Number of ways = " + ways);
    }
}