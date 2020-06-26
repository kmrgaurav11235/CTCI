import java.util.Arrays;

/*
https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent-set-2/

- There are n houses built in a line, each of which contain some value in it. A thief is trying to steal 
    maximum value from these. But, he cannot steal from two adjacent houses. What is the maximum value 
    that he can steal?
*/
class DP_03_HouseThief_TD {
    static int maxValueFromNonAdjacentHouses(int [] housesNetWorth) {
        int n = housesNetWorth.length;
        int [] memo = new int[n];
        Arrays.fill(memo, -1);

        return maxValueFromNonAdjacentHousesUtil(housesNetWorth, n, 0, memo);
    }

    private static int maxValueFromNonAdjacentHousesUtil(int [] housesNetWorth, int n, int index, int [] memo) {
        if (index >= n) {
            return 0;
        } else if (memo[index] == -1) {
            // Choose this
            int stealCurrentHouse = maxValueFromNonAdjacentHousesUtil(housesNetWorth, n, index + 2, memo) + housesNetWorth[index]; 

            // Don't choose this
            int skipCurrentHouse = maxValueFromNonAdjacentHousesUtil(housesNetWorth, n, index + 1, memo);
            memo[index] = Math.max(stealCurrentHouse, skipCurrentHouse);
        }
        return memo[index];
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