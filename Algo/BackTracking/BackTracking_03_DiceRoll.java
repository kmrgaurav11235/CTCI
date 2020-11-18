import java.util.ArrayList;

/*
Write a program to print all possible dice rolls with a particular number of dice
Lecture: https://www.youtube.com/watch?v=Cl9U027Rb9s

In the previous two solution, we did recursion, but there was no backtracking.
BackTracking:
Finding solutions by trying partial solutions and abandoning them if they are not suitable.
- A brute force application technique (tries all path).
- Often uses recursion.

Used for:
- Producing all permutations of a set of values.
- Combinatorics 
- Logical programming
- Escaping a maze.
etc.

Pseudo Code:
Explore (decisions):
    - If there are no more decisions to make, stop.
    - Else, handle one decision here and rest by recursion. 
        For each available choice C for this decision:
            - choose C.
            - Explore the remaining choices that could follow C through recursion.
            - Un-choose C (BACKTRACK!).
*/
class BackTracking_03_DiceRoll {
    static void diceRollUtil(ArrayList<Integer> resultOfDiceRoll, int numOfDiceLeftToRoll) {
        if  (numOfDiceLeftToRoll == 0) {
            // When no dice is left to fix, print the result
            System.out.print(resultOfDiceRoll + " ");
            return;
        }
        // At each step, we roll one die (to all possible values - 1 to 6)
        for (int i = 1; i <= 6; i++) {
            // Choose 
            resultOfDiceRoll.add(i);

            // Explore
            diceRollUtil(resultOfDiceRoll, numOfDiceLeftToRoll - 1);

            // Backtrack
            resultOfDiceRoll.remove(resultOfDiceRoll.size() - 1);
        }
    }

    static void diceRoll(int numOfDice) {
        ArrayList<Integer> resultOfDiceRoll = new ArrayList<>();
        diceRollUtil(resultOfDiceRoll, numOfDice);
        // Since this time around, we are using a data-structure, which is passed by reference (a list),
        // we will have to backtrack. Compare this to last problem, where we used string which is passed by
        // value and the last step of backtracking is not required (as the original string doesn't gets modified).
    }
    public static void main(String[] args) {
        System.out.println("All rolls with 1 dice:");
        diceRoll(1); 

        System.out.println("\n\nAll rolls with 2 dice:");
        diceRoll(2); 
    }

}