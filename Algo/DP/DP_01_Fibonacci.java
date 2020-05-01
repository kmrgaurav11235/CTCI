/* 
Lecture: https://www.youtube.com/watch?v=OQ5jsbhAv_M
Geeks4Geeks: 
  - https://www.geeksforgeeks.org/overlapping-subproblems-property-in-dynamic-programming-dp-1/
  - https://www.geeksforgeeks.org/optimal-substructure-property-in-dynamic-programming-dp-2/

Dynamic Programming: 
  - Generally useful when we need to search the solution space to find optimal solutions.
      DP ~ Careful Brute-search with solution re-use.
  - Break the problem into sub-problems, solve sub-problems and re-use results.
  - Overlapping sub-problems: DP is useful when the solutions of the same sub-problems are needed 
      again and again. We can then, solve the problem once and memoize it (save it in memo for later).
  - Optimal sub-structure: DP is useful when optimal solution to problem can be found using optimal 
    solution to sub-problems.

Fibonacci Sequence: Has overlapping sub-problems property.
*/
public class DP_01_Fibonacci {
  int memo [] = new int [100];

  int naiveFibonacci (int n) {
    int fib;
    if (n <= 0) {
      fib = 0;
    } else if (n == 1 || n == 2) {
      fib = 1;
    } else {
      fib = naiveFibonacci(n - 1) + naiveFibonacci(n - 2);
    }
    return fib;
  }
  // Memoized Version: This is same as above but we don't solve the same problem more than once. Instead 
  // we store the solution in an memo.
  int memoizedFibonacci (int n) {
    if (memo[n] != 0) {
      return memo[n];
    }

    int fib;
    if (n <= 0) {
      fib = 0;
    } else if (n == 1 || n == 2) {
      fib = 1;
    } else {
      fib = memoizedFibonacci(n - 1) + memoizedFibonacci(n - 2);
    }
    memo[n] = fib;
    return fib;
  }
  /* 
  Bottom-up Version (tabulation): Builds a solution in bottom-up fashion. Memoization is top-down.  
  For this we have to figure out the topological sort of the sub-problems, so that by the time we solve
  a sub-problem, all the sub-problems that it depends upon are already solved.
  This brings another characteristic of DP. The sub-problems should be acyclic. If we start with P(n), and
  get P(n) = x + ... + P(n), we got a cycle. So, we can't solve it using DP. That is why we cannot find
  Single-source shortest path in a Graph with Cycle using DP. Instead we have use Bellman-Ford.
  Basically, the recursive calls should have a topological order.

  This version generally saves space as only save the solutions we need. But sometimes, it solves problems
  that we don't need.
  */
  int bottomUpFibonacci (int n) {
    int [] bottomUp = new int [100];
    for (int i = 1; i <= n; i++) {
      if (i == 1 || i == 2) {
        bottomUp[i] = 1;
      } else {
        bottomUp[i] = bottomUp[i - 1] + bottomUp[i - 2];
      }
    }
    return bottomUp[n];
  }

  public static void main(String[] args) {
    DP_01_Fibonacci fibonacci = new DP_01_Fibonacci();

    System.out.println("Fibonacci 3 = " + fibonacci.naiveFibonacci(3));
    System.out.println("Fibonacci 4 = " + fibonacci.naiveFibonacci(4));
    System.out.println("Fibonacci 5 = " + fibonacci.memoizedFibonacci(5));
    System.out.println("Fibonacci 7 = " + fibonacci.memoizedFibonacci(7));
    System.out.println("Fibonacci 12 = " + fibonacci.bottomUpFibonacci(12));
    System.out.println("Fibonacci 15 = " + fibonacci.bottomUpFibonacci(15));
  }
}
