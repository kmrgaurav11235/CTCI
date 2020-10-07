import java.util.Arrays;

/*
https://leetcode.com/problems/gas-station/discuss/42568/Share-some-of-my-ideas.

- There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
- You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to 
    its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
- Return the starting gas station's index if you can travel around the circuit once in the clockwise 
    direction, otherwise return -1.
- Notes:
    * If there exists a solution, it is guaranteed to be unique.
    * Both input arrays are non-empty and have the same length.
    * Each element in the input arrays is a non-negative integer.
- Example 1:
    Input: 
        gas  = [1,2,3,4,5]
        cost = [3,4,5,1,2]
    Output: 3
    Explanation:
        * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
        * Travel to station 4. Your tank = 4 - 1 + 5 = 8
        * Travel to station 0. Your tank = 8 - 2 + 1 = 7
        * Travel to station 1. Your tank = 7 - 3 + 2 = 6
        * Travel to station 2. Your tank = 6 - 4 + 3 = 5
        * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
        * Therefore, return 3 as the starting index.
- Example 2:
    Input: 
        gas  = [2,3,4]
        cost = [3,4,3]
    Output: -1
    Explanation:
        * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
        * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
        * Travel to station 0. Your tank = 4 - 3 + 2 = 3
        * Travel to station 1. Your tank = 3 - 3 + 3 = 3
        * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
        * Therefore, you can't travel around the circuit once no matter where you start.

- Important Observations to be considered:
    1. If the total amount of gas is greater than the total amount of cost, there must be a solution. 
        And the question guarantees that the solution is unique (The first one we found is the right 
        one).
    2. If car starts at A and cannot reach B, then any station between A and B cannot reach B.
    3. The tank should never be negative. So, restart whenever there is a negative number.

- Proof of Observation 1: 
    * If there is only one gas station, it’s true.
    * If there are two gas stations a and b, and gas(a) cannot afford cost(a), i.e., gas(a) < cost(a), then 
        gas(b) must be greater than cost(b), since gas(a) + gas(b) > cost(a) + cost(b). So there must be a 
        here way too.
    * If there are three gas stations a, b, and c, where gas(a) < cost(a), i.e. we cannot travel from a to b 
        directly, then:
        
        i) Either if gas(b) < cost(b), i.e., we cannot travel from b to c directly, then gas(c) > cost(c), so 
        we can start at c and travel to a. Since gas(b) < cost(b), gas(c) + gas(a) must be greater than cost(c) 
        + cost(a), so we can continue traveling from a to b. 
        Key Point: this can be considered as there is one station at c’ with gas(c’) = gas(c) + gas(a) and the
        cost from c’ to b is cost(c’) = cost(c) + cost(a), and the problem reduces to a problem with two stations. 
        This in turn becomes the problem with two stations above.
        
        ii) or if gas(b) >= cost(b), we can travel from b to c directly. Similar to the case above, this problem 
        can be reduced to a problem with two stations b’ and a, where gas(b’) = gas(b) + gas(c) and cost(b’) = 
        cost(b) + cost(c). Since gas(a) < cost(a), gas(b’) must be greater than cost(b’), so it’s solved too.

    * For problems with more stations, we can reduce them in a similar way. 

- Proof of Observation 2: Say there is a point C between A and B, so that is A can reach C but cannot 
    reach B. Since A cannot reach B, the gas collected between A and B is short of the cost. Starting 
    from A, at the time when the car reaches C, it brings in gas >= 0, and the car still cannot reach B. 
    Thus if the car just starts from C (with gas == 0), it definitely cannot reach B.
*/
public class GA_04_GasStation {
    static int canCompleteCircuit(int[] gas, int[] cost, int n) {
        int totalGasAvailable = 0, totalGasRequired = 0;
        int startAt = 0;
        int gasInTank = 0;

        for (int i = 0; i < n; i++) {
            totalGasAvailable += gas[i];
            totalGasRequired += cost[i];
            gasInTank += (gas[i] - cost[i]);

            if (gasInTank < 0) {
                // No gas left. Observation 2 tells us that we should not 'startAt' gas station 'i + 1' as
                // there is no point checking any station between 'startAt' and 'i'
                startAt = i + 1;
                gasInTank = 0;
            }
        }

        if (totalGasAvailable < totalGasRequired) {
            // Observation 1
            return -1;
        } else {
            return startAt;
        }
    }
    public static void main(String[] args) {
        int [] gas1 = {1,2,3,4,5};
        int [] cost1 = {3,4,5,1,2};
        System.out.println("Gas: " + Arrays.toString(gas1) + "\nCost: " + Arrays.toString(cost1));
        System.out.println("Starting Gas station index: " + canCompleteCircuit(gas1, cost1, gas1.length));
        
        int [] gas2 = {2,3,4};
        int [] cost2 = {3,4,3};
        System.out.println("\nGas: " + Arrays.toString(gas2) + "\nCost: " + Arrays.toString(cost2));
        System.out.println("Starting Gas station index: " + canCompleteCircuit(gas2, cost2, gas2.length));
    }
}
