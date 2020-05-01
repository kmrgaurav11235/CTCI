import java.util.ArrayList;

/*
Write a program to print all possible dice rolls with a given number of dice that add to a given sum
Lecture: https://www.youtube.com/watch?v=jHLz-9RxlhE
*/
class BackTracking_04_DiceSum {
    static void diceSumUtil (ArrayList<Integer> resultOfDiceRoll, int numOfDiceLeftToRoll, int sumLeft) {
        if (numOfDiceLeftToRoll == 0 && sumLeft == 0) {
            System.out.print(resultOfDiceRoll + " ");
            return;
        } 
        for (int i = 1; i <= 6; i++) {
            if (sumLeft > 0) {
                // sumLeft <= 0 will not lead to any solutions:
                //      1. sumLeft < 0 is obvious
                //      2. sumLeft == 0 with numOfDiceLeftToRoll != 0 (as checked in base case above) will also not lead to solution.

                // Choose
                resultOfDiceRoll.add(i);
                // Explore
                diceSumUtil(resultOfDiceRoll, numOfDiceLeftToRoll - 1, sumLeft - i);
                // Backtrack
                resultOfDiceRoll.remove(resultOfDiceRoll.size() - 1);
            }            
        }
    }

    static void diceSum (int numOfDice, int sum) {
        ArrayList<Integer> resultOfDiceRoll = new ArrayList<>();
        diceSumUtil(resultOfDiceRoll, numOfDice, sum);
    }
    static void diceSumImprovedUtil (ArrayList<Integer> resultOfDiceRoll, int numOfDiceLeftToRoll, int sumLeft) {

        if (numOfDiceLeftToRoll == 0) {
            // No need to test if sumLeft == 0 as we have already eliminated all those recursion trees 
            // that don't allow for solution. As such, any time we reach leaf node, it must be a solution.
            System.out.print(resultOfDiceRoll + " ");
            return;
        } 
        for (int i = 1; i <= 6; i++) {
            int minSumPossible = i + 1 * (numOfDiceLeftToRoll - 1); // 1 on every roll that is left
            int maxSumPossible = i + 6 * (numOfDiceLeftToRoll - 1); // 6 on every roll that is left

            if (minSumPossible <= sumLeft && sumLeft <= maxSumPossible) {
                // Choose
                resultOfDiceRoll.add(i);
                // Explore
                diceSumUtil(resultOfDiceRoll, numOfDiceLeftToRoll - 1, sumLeft - i);
                // Backtrack
                resultOfDiceRoll.remove(resultOfDiceRoll.size() - 1);
            }            
        }
    }

    static void diceSumImproved (int numOfDice, int sum) {
        ArrayList<Integer> resultOfDiceRoll = new ArrayList<>();
        diceSumImprovedUtil(resultOfDiceRoll, numOfDice, sum);
    }

    public static void main(String[] args) {
        System.out.println("All rolls with 2 dice and sum 7:");
        diceSum(2, 7); 

        System.out.println("\n\nAll rolls with 2 dice and sum 7 with improved algorithm:");
        diceSumImproved(2, 7);     
    }
}