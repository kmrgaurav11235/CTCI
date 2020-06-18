import java.util.Stack;

/*
https://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/

- Design a Data Structure SpecialStack that supports all the stack operations like push(), 
    pop(), isEmpty() and an additional operation getMin() which should return minimum element 
    from the SpecialStack. All these operations of SpecialStack must be O(1). 
- To implement SpecialStack, you should only use standard Stack data structure and no other 
    data structure like arrays, list ... etc. 
- Example:
    Consider the following SpecialStack
    16  --> TOP
    15
    29
    19
    18
    When getMin() is called it should return 15, which is the minimum element in the current 
    stack. 

    If we do pop two times on stack, the stack becomes
    29  --> TOP
    19
    18

    When getMin() is called, it should return 18 which is the minimum in the current stack.

- Solution: Use two stacks -- one to store actual stack elements and other as an auxiliary 
    stack to store minimum values. The idea is to do push() and pop() operations in such a 
    way that the top of auxiliary stack is always the minimum.
- Push(int x) 
    // inserts an element x to Special Stack 
    1) push x to the first stack (the stack with actual elements)
    2) compare x with the top element of the second stack (the auxiliary stack). Let the top 
        element be y.
        a) If x is smaller than or equal to y. then push x to the auxiliary stack.
- int Pop() 
    // removes an element from Special Stack and return the removed element 
    1) pop the top element from the actual stack - call it x.
    2) peek the top element from the auxiliary stack and check if it is equal to x.
        a) If they are equal, pop an element from auxiliary stack.
    3) Return x.
- int getMin() 
    // returns the minimum element from Special Stack 
    1) Return the top element of auxiliary stack.

- Complexity Analysis:
    Time Complexity: 
    * For Insert operation: O(1) 
    * For Delete operation: O(1)
    * For ‘Get Min’ operation: O(1)
    Auxiliary Space: O(n).
*/
public class Stack_06_StackWithGetMin {
    static class SpecialStack<T extends Comparable<T>>{
        private Stack<T> mainStack;
        private Stack<T> minItemStack;

        public SpecialStack() {
            mainStack = new Stack<>();
            minItemStack = new Stack<>();
        }
        public void push(T item) {
            mainStack.push(item);
            if (minItemStack.isEmpty()) {
                minItemStack.push(item);
            } else {
                if (item.compareTo(minItemStack.peek()) <= 0) {
                    minItemStack.push(item);
                }
            }
        }
        
        public T pop() throws Exception {
            if (mainStack.isEmpty()) {
                throw new Exception("Stack is Empty. Underflow.");
            }
            T item = mainStack.pop();
            if (item.equals(minItemStack.peek())) {
                minItemStack.pop();
            }
            return item;
        }

        public T getMin() {
            return minItemStack.peek();
        }

        @Override
        public String toString() {
            return mainStack.toString() + " <-- Top.";
        }
    }
    public static void main(String[] args) throws Exception {
        SpecialStack<Integer> s = new SpecialStack<>(); 
        s.push(18); 
        s.push(19); 
        s.push(29); 
        s.push(15); 
        s.push(26); 
        System.out.println("Stack: " + s); 
        System.out.println("Min Element: " + s.getMin());
        System.out.println("Popped Element: " + s.pop());
        System.out.println("Stack: " + s); 
        System.out.println("Min Element: " + s.getMin());
        System.out.println("Popped Element: " + s.pop());
        System.out.println("Stack: " + s); 
        System.out.println("Min Element: " + s.getMin());
    }
}