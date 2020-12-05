import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/

- Given a pattern containing only I’s and D’s. I for increasing and D for decreasing. Devise an algorithm 
    to print the minimum number following that pattern.
- If pattern has a length of n, the output should be of length n + 1.
- Examples: 
   Input: D        Output: 21
   Input: I        Output: 12
   Input: DD       Output: 321
   Input: II       Output: 123
   Input: DIDI     Output: 21435
   Input: IIDDD    Output: 126543
   Input: DDIDDIID Output: 321654798

- When we encounter I, we got numbers in increasing order. When we encounter D, we want to have numbers in 
    decreasing order. 
- Length of the output string is always one more than the input string. So loop is from 0 till the length 
    of the sting. 
- We always push (i+1) to our stack. Then we check what is the resulting character at the specified index:
    Case 1: If we have encountered I or we are at the last character of input string,then pop from the stack 
        and add it to the end of the output string until the stack gets empty. 
    Case 2: If we have encountered D, then we just push (i+1) to our stack.
- Time Complexity: O(n) 
    Auxiliary Space: O(n)
*/
public class Stack_04_FindPermutation {
    static String nextPermutation(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        
        int n = input.length();
        Deque<Integer> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int counter = 1;

        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == 'I') {
                sb.append(counter++);
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            } else {
                stack.push(counter++);
            }
        }
        // Since there is an extra character in the output
        sb.append(counter);
        
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String inputs[] = { "IDID", "I", "DD", "II", "DIDI", "IIDDD", "DDIDDIID" };
        for(String input : inputs) {
            System.out.println("Pattern: " + input + ", Number: " 
                + nextPermutation(input));
        }
    }
}