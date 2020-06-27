/*
https://www.geeksforgeeks.org/the-celebrity-problem/

- The Celebrity Problem
- In a party of N people, only one person is known to everyone. Such a person may be present in the 
    party. if yes, he doesn’t know anyone in the party. We can only ask questions like "does A know B?". 
    Find the stranger (celebrity) in the minimum number of questions.
- We can describe the problem input as a matrix of boolean representing persons in the party. 
- Examples:
    Input:
    MATRIX = { {false, false, true, false},
                {false, false, true, false},
                {false, false, false, false},
                {false, false, true, false} }
    Output:id = 2
    Explanation: The person with ID 2 does not know anyone but everyone knows him

    Input:
    MATRIX = { {false, false, true, false},
                {false, false, true, false},
                {false, true, false, false},
                {false, false, true, false} }
    Output: No celebrity
    Explanation: There is no celebrity.
- Two Pointers technique to solve the above problem.
- Approach: The idea is to use two pointers, one from start and one from the end. Assume the start person is A, 
    and the end person is B. If A knows B, then A must not be the celebrity. Else, B must not be the celebrity. 
    At the end of the loop, only one index will be left as a celebrity. Go through each person again and check 
    whether this is the celebrity.
- The Two Pointer approach can be used where two pointers can be assigned, one at the start and other at the end 
    and the elements can be compared and the search space can be reduced.
- Algorithm :
    1) Create two indices a and b, where a = 0 and b = n-1
    2) Run a loop until a is less than b.
    3) Check if a knows b, then a can be celebrity. so incement a, i.e. a++
    4) Else b cannot be celebrity, so decrement b, i.e. b–-
    5) Assign a as the celebrity
    6) Run a loop from 0 to n-1 and find the count of persons who knows the celebrity and the number of people whom 
        the celebrity knows. if the count of persons who knows the celebrity is n-1 and the count of people whom the 
        celebrity knows is 0 then return the id of celebrity else return -1.
- Complexity Analysis:
    * Time Complexity: O(n).
        Total number of comparisons 2(N-1), so the time complexity is O(n).
    * Space Complexity : O(1).
*/
class Matrix_03_TheCelebrityProblem {

    private static boolean isCelebrity(int celebrityCandidate, boolean[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[celebrityCandidate][i]) {
                // Celebrity Candidate knows someone
                return false;
            }
            if (!matrix[i][celebrityCandidate]) {
                if (i != celebrityCandidate) {
                    return false;
                }
            }
        }
        return true;
    }

    static int findCelebrity(boolean[][] matrix, int n) {
        int left = 0, right = n - 1;
        while (left < right) {
            if (matrix[left][right]) {
                // left knows right
                left++;
            } else {
                // left doesn't knows right
                right--;
            }
        }

        int celebrityCandidate = left;
        if (isCelebrity(celebrityCandidate, matrix, n)) {
            return celebrityCandidate;
        } else {
            // No celebrity
            return -1;
        }
    }

    public static void main(String[] args) {
        boolean [][] matrix1 = { {false, false, true, false},
                                {false, false, true, false},
                                {false, false, false, false},
                                {false, false, true, false} };
        
        int celebrity1 = findCelebrity(matrix1, 4); 
        if (celebrity1 == -1) { 
            System.out.println("No Celebrity 1 is present"); 
        } else {
            System.out.println("Celebrity 1 is : " +  celebrity1); 
        }

        boolean [][] matrix2 = { {false, false, true, false},
                                {false, false, true, false},
                                {false, true, false, false},
                                {false, false, true, false} };

        int celebrity2 = findCelebrity(matrix2, 4); 
        if (celebrity2 == -1) { 
            System.out.println("No Celebrity 2 is present"); 
        } else {
            System.out.println("Celebrity 2 is : " +  celebrity2); 
        }
    }
}