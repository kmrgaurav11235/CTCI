/*
https://www.geeksforgeeks.org/stack-data-structure-introduction-program/

- Implementing Stack using Linked List:
    * Pros: The linked list implementation of stack can grow and shrink according to the 
        needs at runtime.
    * Cons: Requires extra memory due to involvement of pointers.

*/

class Stack_02_StackByLL {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Stack {
        private Node head;

        Stack() {
            head = null;
        }

        Stack(int data) {
            head = new Node(data);
        }

        boolean isEmpty() {
            return (head == null);
        }

        void push(int data) {
            Node temp = new Node(data);
            temp.next = head;
            head = temp;
        }

        int pop() {
            if (isEmpty()) {
                System.out.println("Pop operation failed! Stack is Empty.");
                return 0;
            }
            Node temp = head;
            head = head.next;
            return temp.data;
        }

        int peek() {
            if (isEmpty()) {
                System.out.println("Peek operation failed! Stack is Empty.");
                return 0;
            }
            return head.data;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ ");

            Node p = head;
            while (p != null) {
                stringBuilder.append(p.data + " ");
                p = p.next;
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
        System.out.println("Stack: " + s);
        System.out.println(s.pop() + " Popped from stack."); 
        System.out.println("Stack: " + s);
        System.out.println(s.pop() + " Popped from stack."); 
    }
}