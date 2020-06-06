/*
https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/

- Check for balanced parentheses in an expression: Given an expression string exp, write a 
    program to examine whether the pairs and the orders of "{", "}", "(", ")", "[", "]" are 
    correct in exp.

- Example:
    Input: exp = "[()]{}{[()()]()}"
    Output: Balanced

    Input: exp = "[(])"
    Output: Not Balanced 

- Algorithm:
    1) Declare a character stack S.
    2) Now traverse the expression string exp.
        a) If the current character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) then push it to stack.
        b) If the current character is a closing bracket (‘)’ or ‘}’ or ‘]’) then pop from stack and 
            if the popped character is the matching starting bracket then fine else parenthesis are 
            not balanced.
    3) After complete traversal, if there is some starting bracket left in stack then "not balanced"

*/
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Stack_03_BalancedParenthesesCheck {
    private static boolean isParenthesisBalanced(String expression) {
        Deque<Character> stack = new LinkedList<>();

        Map<Character, Character> parenthesisMap = new HashMap<>();
        parenthesisMap.put('[', ']');
        parenthesisMap.put('{', '}');
        parenthesisMap.put('(', ')');

        for (int i = 0; i < expression.length(); i++) {
            char exprChar = expression.charAt(i);

            if (exprChar == '[' || exprChar == '{' || exprChar == '(') {
                stack.push(exprChar);
            } else if (exprChar == ']' || exprChar == '}' || exprChar == ')') {
                if (stack.isEmpty() || parenthesisMap.get(stack.pop()) != exprChar) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {

        String expression1 = "{()}[]";
        if (isParenthesisBalanced(expression1)) {
            System.out.println(expression1 + ": Balanced ");
        } else {
            System.out.println(expression1 + ": Not Balanced ");
        }

        String expression2 = "[()]{}{[()()]()}";
        if (isParenthesisBalanced(expression2)) {
            System.out.println(expression2 + ": Balanced ");
        } else {
            System.out.println(expression2 + ": Not Balanced ");
        }

        String expression3 = "[(])";
        if (isParenthesisBalanced(expression3)) {
            System.out.println(expression3 + ": Balanced ");
        } else {
            System.out.println(expression3 + ": Not Balanced ");
        }

        String expression4 = "{{()}[]";
        if (isParenthesisBalanced(expression4)) {
            System.out.println(expression4 + ": Balanced ");
        } else {
            System.out.println(expression4 + ": Not Balanced ");
        }
    }
}