import java.util.Arrays;

/*
https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent-set-2/

- There are n houses built in a line, each of which contain some value in it. A thief is trying to steal 
    maximum value from these. But, he cannot steal from two adjacent houses. What is the maximum value 
    that he can steal?
- Check out the TD solution first.
*/
class DP_03_HouseThief_BU {
    static int maxValueFromNonAdjacentHouses(int [] housesNetWorth) {
        int n = housesNetWorth.length;
        // last index = n - 1. But we will check n + 1 and n + 2 in recursive calls.
        // So, last index of memo[] is n + 1 (size = n + 2).
        // Base cases, memo[n] = memo[n + 1] = 0;
        int [] memo = new int[n + 2];

        for (int i = n - 1; i >= 0; i--) {
            memo[i] = Math.max(memo[i + 1], memo[i + 2] + housesNetWorth[i]);
        }
        return memo[0];
    }
    public static void main(String[] args) {
        int [] housesNetWorth1 = {6, 7, 1, 30, 8, 4, 2};
        System.out.println("Max value possible from Houses : " + Arrays.toString(housesNetWorth1) 
            + " = " + maxValueFromNonAdjacentHouses(housesNetWorth1) + ".");
        int [] housesNetWorth2 = {20, 5, 1, 13, 6, 11, 40};
        System.out.println("Max value possible from Houses : " + Arrays.toString(housesNetWorth2) 
            + " = " + maxValueFromNonAdjacentHouses(housesNetWorth2) + ".");
    }
}
// Another Efficient solution is under Arrays -> MaxSumNonAdjacent