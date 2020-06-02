/*
https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
- The function detectAndRemoveLoop() should checks whether a given Linked List contains 
    loop. If loop is present then it should remove the loop and return true. If the list 
    doesn’t contain loop then it returns false. 
- e.g. A linked list with a loop: 
    1 ▶ 2 ▶ 3
        ⬆  ⬇
        5 ◀ 4
    detectAndRemoveLoop() must change the list to:
    1 ▶ 2 ▶ 3 ▶ 4 ▶ 5 ▶ NULL.

- Detecting the loop: Floyd’s Cycle-Finding Algorithm:
    1) Traverse linked list using two pointers. 
    2) Move one pointer(slowP) by one and another pointer(fastP) by two. 
    3) If these pointers meet at the same node then there is a loop. If pointers do not 
        meet then linked list doesn’t have a loop.
- Removing the loop: This method is also dependent on Floyd’s Cycle detection algorithm.
    1) Detect Loop using Floyd’s Cycle detection algorithm and get the pointer to a loop node.
    2) Count the number of nodes in loop. Let the count be k.
    3) Fix one pointer to the head and another to a kth node from the head.
    4) Move both pointers at the same pace, they will meet at loop starting node.
    5) Get a pointer to the last node of the loop and make next of it as NULL.

*/
public class LL_08_RemoveLoop {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class LinkedList {
        Node head;
        public void push(int data) {
            Node temp = new Node(data);
            temp.next = head;
            head = temp;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[head -> ");
            Node p = head;
            while (p != null) {
                sb.append(p.data + " -> ");
                p = p.next;
            }
            sb.append(" null]");
            return sb.toString();
        }

        public boolean detectAndRemoveLoop() {
            if (head == null) {
                return false;
            }
            Node fastPointer = head;
            Node slowPointer = head;

            while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;

                if (fastPointer == slowPointer) {
                    removeLoop(fastPointer);
                    return true;
                }
            }

            return false;
        }

        private void removeLoop(Node loopPointer) {
            // Find the number of nodes in loop
            int n = 1;
            Node p = loopPointer.next;

            while (p != loopPointer) {
                p = p.next;
                n++;
            }

            // Take a pointer n steps from head, (n + 1)th node.
            p = head;
            for (int i = 0; i < n; i++) {
                p = p.next;
            }

            Node q = head;
            Node prev = null;

            // p and q meet at the first node of loop
            while (p != q) {
                prev = p;
                p = p.next;
                q = q.next;
            }

            // remove loop
            prev.next = null;
        }
    }
    public static void main(String[] args) {

        LinkedList lList1 = new LinkedList(); 
        lList1.push(20); 
        lList1.push(4); 
        lList1.push(15); 
        lList1.push(10); 
        if (lList1.detectAndRemoveLoop()) {
            System.out.println("Linked List after removing loop: " + lList1); 
        } else {
            System.out.println("No loop found in list 1: " + lList1);
        }

        LinkedList lList2 = new LinkedList(); 
        lList2.head = new Node(50); 
        lList2.head.next = new Node(20); 
        lList2.head.next.next = new Node(15); 
        lList2.head.next.next.next = new Node(4); 
        lList2.head.next.next.next.next = new Node(10); 
  
        // Creating a loop for testing 
        System.out.println("\nLinked list 2: " + lList2);
        lList2.head.next.next.next.next.next = lList2.head.next.next; 
        System.out.println("Added Loop to Linked list 2.");

        if (lList2.detectAndRemoveLoop()) {
            System.out.println("Linked List 2 after removing loop: " + lList2); 
        } else {
            System.out.println("No loop found in list 2: " + lList2);
        }
    }
}