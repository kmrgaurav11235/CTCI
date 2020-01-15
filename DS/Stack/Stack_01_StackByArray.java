/*
https://www.geeksforgeeks.org/stack-data-structure-introduction-program/
*/

class Stack_01_StackByArray {
    static class Stack {
        private int size;
        private int [] a;
        private int top;

        Stack(int size) {
            this.size = size;
            a = new int [size];
            top = -1;
        }

        boolean isEmpty() {
            return (top == -1);
        }

        boolean isFull() {
            return (top == size - 1);
        }

        void push(int info) {
            if (isFull()) {
                System.out.println("Push failed! Stack is full.");
                return;
            }
            a[++top] = info;
        }

        int pop() {
            if (isEmpty()) {
                System.out.println("Pop failed! Stack is Empty.");
                return 0;
            }
            int temp = a[top--];
            return temp;
        }

        int peek() {
            if (isEmpty()) {
                System.out.println("Peek failed! Stack is Empty.");
                return 0;
            }
            return a[top];
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ ");
            for (int i = 0; i <= top; i++) {
                stringBuilder.append(a[i] + " ");
            }
            stringBuilder.append("] <- Top\n");
            return stringBuilder.toString();
        }
    }
    public static void main(String[] args) {
        Stack s = new Stack(3);
        System.out.println("Initial Stack: " + s);
        System.out.println("Inserting 10 into stack."); 
        s.push(10); 
        System.out.println("Current Stack: " + s);
        System.out.println("Inserting 20 into stack."); 
        s.push(20); 
        System.out.println("Current Stack: " + s);
        System.out.println("Inserting 30 into stack."); 
        s.push(30); 
        System.out.println("Current Stack: " + s);
        System.out.println("Inserting 80 into stack."); 
        s.push(80); 
        System.out.println("Current Stack: " + s);
        
        System.out.println(s.pop() + " Popped from stack."); 
        System.out.println("Stack: " + s);
        System.out.println(s.pop() + " Popped from stack."); 
        System.out.println("Stack: " + s);
        System.out.println(s.pop() + " Popped from stack."); 
        System.out.println("Stack: " + s);
        System.out.println(s.pop() + " Popped from stack."); 
    }
}