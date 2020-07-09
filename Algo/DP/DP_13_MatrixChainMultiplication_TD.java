/*
https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/Dynamic/chainMatrixMult.htm
https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/

- Given a sequence of matrices A[1]A[2]...A[n], find the most efficient way to multiply these matrices 
    together. The problem is not actually to perform the multiplications, but merely to decide in which 
    order to perform the multiplications.
- We have many options to multiply a chain of matrices because matrix multiplication is associative. 
    In other words, no matter how we parenthesize the product, the result will be the same. 
- For example, if the chain of matrices is A[1]A[2]A[3]A[4], then we can fully parenthesize the product
    in five distinct ways:
    1) (A[1] x (A[2] x (A[3] x A[4])))
    2) (A[1] x ((A[2] x A[3]) x A[4]))
    3) ((A[1] x A[2]) x (A[3] x A[4]))
    4) ((A[1] x (A[2] x A[3])) x A[4])
    5) (((A[1] x A[2]) x A[3]) x A[4])
- However, the order in which we parenthesize the product affects the number of simple arithmetic 
    operations needed to compute the product, or the efficiency. For example, suppose A[1] is a 10 × 30 
    matrix, A[2] is a 30 × 5 matrix, and A[3] is a 5 × 60 matrix. Then,
    * (A[1] x A[2]) x A[3] = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
    * A[1] x (A[2] x A[3]) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
    Clearly the first parenthesization requires less number of operations.
- Given an array p[] which represents the chain of matrices such that the i-th matrix A[i] is of dimension 
    p[i-1] x p[i]. We need to write a function matrixChainOrder() that should return the minimum number 
    of scalar multiplications needed to multiply the chain.

    1) 
    Input: p[] = {40, 20, 30, 10, 30}
    Output: 26000  
    There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30. The minimum number of multiplications 
    are obtained by putting parenthesis in following way:
    (A[1] x (A[2] x A[3])) x A[4] --> 20 * 30 * 10 + 40 * 20 * 10 + 40 * 10 * 30

    2)
    Input: p[] = {10, 20, 30, 40, 30} 
    Output: 30000 
    There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30. The minimum number of multiplications 
    are obtained by putting parenthesis in following way:
    ((A[1] x A[2]) x A[3]) x A[4] --> 10 * 20 * 30 + 10 * 30 * 40 + 10 * 40 * 30

    3)
    Input: p[] = {10, 20, 30}
    Output: 6000  
    There are only two matrices of dimensions 10 x 20 and 20 x 30. So there is only one way to multiply the 
    matrices, cost of which is 10 * 20 * 30

- Solution:
    Step 1) The structure of an optimal parenthesization: For convenience, let us adopt the notation A[i..j], 
        where i < j, for the matrix that results from evaluating the product A[i]A[i+1]...A[j]. If the problem 
        is nontrivial, i.e., i < j, then to parenthesize the product A[i]A[i+1]...A[j], we must split the product 
        between A[k] and A[k+1] for some integer k in the range i <= k < j. 
        * That is, for some value of k, we first compute the matrices A[i..k] and A[k+1..j] and then multiply 
            them together to produce the final product A[i..j].
        * The cost of parenthesizing this way is the cost of computing the matrix A[i..k], plus the cost of 
            computing A[k+1..j], plus the cost of multiplying them together.
        * The optimal substructure of this problem is as follows: Suppose that to optimally parenthesize 
            A[i]A[i+1]..A[j], we split the product between A[k] and A[k+1]. Then, the way we parenthesize the 
            "prefix" subchain A[i]A[i+1]..A[k] within this optimal parenthesization of A[i]A[i+1]..A[j] must be 
            an optimal parenthesization of A[i]A[i+1]..A[k]. Same is true for the "suffix".
    Step 2) Recursive solution: Let m[i,j] be the minimum number of scalar multiplications needed to compute 
        the matrix A[i..j]. We can define m[i,j] recursively as follows. 
        * If i == j, the problem is trivial. The chain consists of just one matrix, so that no scalar multiplications 
            are necessary to compute the product.
        * When i < j, we use the optimal substructure property from Step 1. To optimally parenthesize, we split 
            the product A[i]A[i+1]..A[j] between A[k] and A[k+1], where i <= k < j. Then, m[i,j] equals the minimum 
            cost for computing the subproducts A[i..k] and A[k+1..j], plus the cost of multiplying these two matrices 
            together. Recalling that each matrix A[i] has dimension p[i-1] x p[i], we see that computing the matrix 
            product A[i..k]A[k+1..j] takes p[i-1] * p[k] * p[j] scalar multiplications. 
        * Thus, we obtain:
                    0 --- if i == j,
        m[i,j] = 
                    Min ( m[i,k] + m[k+1,j] + p[i-1] * p[k] * p[j] ) for all k, where i <= k < j --- if i < j
    Step 3) Using Dynamic Programming: We have relatively few distinct subproblems -- one subproblem for each 
        choice of i and j satisfying 0 <= i <= j < n. As we have this property of overlapping subproblems, we 
        can compute the optimal cost by using a tabular, bottom-up approach.
        * Input: Sequence p[] = {p[0], p[1], p[n - 1]} where the matrix A[i] has dimensions p[i-1] x p[i] for 
            i = (0,1,2...n - 1). 
        * The procedure uses an auxiliary table m[][] for storing the costs.
        * And another auxiliary table s[][] that records which index of k achieved the optimal cost in computing 
            m[i][j].
        * The algorithm should fill in the table min a manner that corresponds to solving the parenthesization 
            problem on matrix chains of increasing length.
        * Recursive Algorithm:
        let memo[0,n-1][0,n-1] and parenthesis[0,n-1][0,n-1] be new tables
        matrixChainOrderRecursive (p[], i, j, memo[][], parenthesis[][])
            if i == j
                return 0
            if memo[i][j] is already calculated
                return memo[i][j]
            memo[i][j] = Infinity
            for k = i to j - 1
                q = matrixChainOrderRecursive(p, i, k, memo, parenthesis) 
                    + matrixChainOrderRecursive(p, k + 1, j, memo, parenthesis) 
                    + p[i - 1] * p[k] * p[j]
                if q < memo[i][j]
                    memo[i][j] = q
                    parenthesis[i][j] = k

    Step 4) Constructing an optimal solution
        Each entry s[i][j] records a value of k such that an optimal parenthesization of A[i]A[i+1]..Aj 
        splits the product between A[k] and A[k+1]. We can determine the earlier matrix multiplications 
        recursively.
        * printOptimalParenthesis(parenthesis[][], i, j)
            if i == j
                print A[i]
            else 
                print "("
                printOptimalParenthesis(parenthesis, i, parenthesis[i][j])
                print " x "
                printOptimalParenthesis(parenthesis, parenthesis[i][j] + 1, j)
                print ")"
*/
public class DP_13_MatrixChainMultiplication_TD {

    static void printOptimalParenthesis(Integer[][] parenthesis, int i, int j) {
        if (i == j) {
            System.out.print("A[" + i + "]");
        } else {
            System.out.print("(");
            printOptimalParenthesis(parenthesis, i, parenthesis[i][j]);
            System.out.print(" x ");
            printOptimalParenthesis(parenthesis, parenthesis[i][j] + 1, j);
            System.out.print(")");
        }
    }

    private static int matrixChainOrderUtil(int[] p, int i, int j, Integer[][] memo, Integer[][] parenthesis) {
        if (i == j) {
            // cost is zero when multiplying one matrix
            return 0;
        } else if (memo[i][j] != null) {
            return memo[i][j];
        }
        memo[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int numberOfOperations = matrixChainOrderUtil(p, i, k, memo, parenthesis)
                    + matrixChainOrderUtil(p, k + 1, j, memo, parenthesis) + p[i - 1] * p[k] * p[j];
            if (numberOfOperations < memo[i][j]) {
                memo[i][j] = numberOfOperations;
                parenthesis[i][j] = k;
            }
        }
        return memo[i][j];
    }

    static void matrixChainOrder(int[] p) {
        int n = p.length;

        /* For simplicity of the program, one extra row and one extra column are allocated in memo[][] and 
        parenthesis[][]. 0th row and 0th column are not used */
        Integer[][] memo = new Integer[n][n];
        Integer[][] parenthesis = new Integer[n][n];

        int num = matrixChainOrderUtil(p, 1, n - 1, memo, parenthesis);

        System.out.println("Optimum Number of operations : " + num);

        System.out.print("Optimal Parenthesization is : ");
        printOptimalParenthesis(parenthesis, 1, n - 1);
    }

    public static void main(String[] args) {
        int[] p1 = { 40, 20, 30, 10, 30 };
        System.out.println("Matrix dimensions: ");
        for (int i = 1; i < p1.length; i++) {
            System.out.println("A[" + i + "] : " + p1[i - 1] + " x " + p1[i]);
        }
        matrixChainOrder(p1);

        int[] p2 = { 10, 20, 30, 40, 30 };
        System.out.println("\n\nMatrix dimensions: ");
        for (int i = 1; i < p2.length; i++) {
            System.out.println("A[" + i + "] : " + p2[i - 1] + " x " + p2[i]);
        }
        matrixChainOrder(p2);

        int[] p3 = { 10, 20, 30 };
        System.out.println("\n\nMatrix dimensions: ");
        for (int i = 1; i < p3.length; i++) {
            System.out.println("A[" + i + "] : " + p3[i - 1] + " x " + p3[i]);
        }
        matrixChainOrder(p3);
    }
}