import java.util.Arrays;

/*
There are n houses built in a line, each of which contain some value in it. A thief
is trying to steal maximum value from these. But, he cannot steal from two adjacent 
houses. What is the maximum value that he can steal?
*/
class Recursion_03_HouseThief {
    static int max(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }
    static int maxValueFromNonAdjacentHouses(int [] housesNetWorth, int index) {
        if (index >= housesNetWorth.length) {
            return 0;
        }
        // Choose this
        int stealCurrentHouse = maxValueFromNonAdjacentHouses(housesNetWorth, index + 2) + housesNetWorth[index]; 

        // Don't choose this
        int skipCurrentHouse = maxValueFromNonAdjacentHouses(housesNetWorth, index + 1);

        return max(stealCurrentHouse, skipCurrentHouse);
    }
    public static void main(String[] args) {
        int [] housesNetWorth1 = {6, 7, 1, 30, 8, 4, 2};
        System.out.println("Max value possible from Houses : " + Arrays.toString(housesNetWorth1) 
            + " = " + maxValueFromNonAdjacentHouses(housesNetWorth1, 0) + ".");
        int [] housesNetWorth2 = {20, 5, 1, 13, 6, 11, 40};
        System.out.println("Max value possible from Houses : " + Arrays.toString(housesNetWorth2) 
            + " = " + maxValueFromNonAdjacentHouses(housesNetWorth2, 0) + ".");
    }
}