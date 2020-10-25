/*
https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

- Given a rod of length n and a table of prices p[i] for i = {1, 2, 3..., n}, determine the maximum 
    revenue obtainable by cutting up the rod and selling the pieces. 
- Note that if the price p[n] for a rod of length n is large enough, an optimal solution may require 
    no cutting at all.
- For example, if length of the rod is 8 and the values of different pieces are given as following, 
    then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6):
    length   | 1   2   3   4   5   6   7   8  
    --------------------------------------------
    price    | 1   5   8   9  10  17  17  20

- And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight 
    pieces of length 1):
    length   | 1   2   3   4   5   6   7   8  
    --------------------------------------------
    price    | 3   5   8   9  10  17  17  20

- To solve the original problem of size n, we solve smaller problems of the same type, but of smaller 
    sizes. Once we make the first cut, we may consider the two pieces as independent instances of the 
    rod-cutting problem. 
- The overall optimal solution incorporates optimal solutions to the two related sub-problems, maximizing 
    revenue from each of those two pieces. We say that the rod-cutting problem exhibits the Optimal 
    Substructure property: Optimal solutions to a problem incorporate optimal solutions to related sub-
    problems, which we may solve independently.
- In a related, but slightly simpler, way to arrange a recursive structure for the rod-cutting problem, 
    we view a decomposition as consisting of a first piece of length i cut off the left-hand end, and 
    then a right-hand remainder of length n - i. Only the remainder, and not the first piece, may be 
    further divided. We may view every decomposition of a length-n rod in this way: as a first piece 
    followed by some decomposition of the remainder.
- When doing so, we can couch the solution with no cuts at all as saying that the first piece has size 
    i = n.

- Recursive top-down implementation:

    CUT-ROD(p[], n)
    if n == 0
        return 0
    q = Integer.MIN_VALUE
    for i = 1 to n
        q = max(q, p[i] + CUT-ROD(p, n - i)
    return q

- Bottom-up implementation:

    BOTTOM-UP-CUT-ROD(p[], n)
    let memo[0...n]be a new array
    memo[0] = 0
    for i = 1 to n
        q = Integer.MIN_VALUE
        for j = 1 to i
            q = max(q, p[j] + memo[i - j])
        memo[j] = q
    return memo[n]

- For the bottom-up dynamic-programming approach, BOTTOM-UP-CUT-ROD uses the natural ordering of the 
    sub-problems: a problem of size j is "smaller" than a subproblem of size i if j < i. Thus, the 
    procedure solves sub-problems of sizes i = 0, 1..., n in that order.
- Reconstructing a solution: Our dynamic-programming solutions to the rod-cutting problem return the 
    value of an optimal solution, but they do not return an actual solution: a list of piece sizes.
- We can extend the dynamic-programming approach to record not only the optimal value computed for each 
    subproblem, but also a choice that led to the optimal value. With this information, we can readily 
    print an optimal solution.

- EXTENDED-BOTTOM-UP-CUT-ROD(p[], n)
    let memo[0...n]and cuts[0...n]be the new arrays
    memo[0] = 0
    for i = 1 to n
        q = Integer.MIN_VALUE
        for j = 1 to i
            if q < p[j] + memo[i - j]
                q = p[j] + memo[i - j]
                cuts[i] = j
        memo[j] = q
    return memo and cuts

- The following procedure takes a price table p and a rod size n, and it calls EXTENDED-BOTTOM-UP-CUT-ROD 
    to compute the array memo[1...n] of optimal first-piece sizes and then prints out the complete list 
    of piece sizes in an optimal decomposition of a rod of length n:

    PRINT-CUT-ROD-SOLUTION(p[], n)
    (memo, cuts) = EXTENDED-BOTTOM-UP-CUT-ROD(p, n)
    i = n
    while i > 0
        print cuts[i]
        i = i - cuts[i]
*/
public class DP_12_RodCutting_BU {
    static void cutRod(int[] price, int n, int[] memo, int[] cuts) {
        memo[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxVal = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if (maxVal < price[j] + memo[i - j]) {
                    maxVal = price[j] + memo[i - j];
                    cuts[i] = j;
                }
            }
            memo[i] = maxVal;
        }
    }
    static void printCutRodSolution(int[] price, int n) {
        int [] memo = new int[n + 1];
        int [] cuts = new int[n + 1];

        cutRod(price, n, memo, cuts);

        System.out.print("Cuts are of lengths: ");
        
        int i = n;
        while (i > 0) {
            System.out.print(cuts[i] + " ");
            i = i - cuts[i];
        }
        System.out.println("\nMaximum Obtainable value: " + memo[n]);
    }

    public static void main(String[] args) {
        int [] price = {0, 1, 5, 8, 9, 10, 17, 17, 20};
        int n = price.length - 1;

        printCutRodSolution(price, n);
    }
}