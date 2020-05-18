/*
https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/
- In this post, methods to insert a new node in linked list are discussed. A node 
    can be added in three ways
    1) At the front of the linked list
    2) After a given node.
    3) At the end of the linked list.
*/
public class LL_02_Insertion {
    static class Node {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        // Inserts data at head
        public void push(int data) {
            Node temp = new Node(data);
            temp.next = head;
            head = temp;
        }

        // Inserts data after a given Node
        public void insertAfter(Node prevNode, int data) {
            if (prevNode == null) {
                System.err.println("Previous node cannot be null!");
                return;
            }
            Node temp = new Node(data);
            temp.next = prevNode.next;
            prevNode.next = temp;
        }

        // Inserts data at the end
        public void append(int data) {
            Node temp = new Node(data);

            if (head == null) {
                head = temp;
            } else {
                Node p = head;
                while (p.next != null) {
                    p = p.next;
                }
                p.next = temp;
            }
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
    }
    public static void main(String[] args) {
        /* Start with the empty list */
        LinkedList lnList = new LinkedList(); 
  
        // Insert 6.  So linked list becomes 6 -> NUll
        lnList.append(6); 
  
        // Insert 7 at the beginning. So linked list becomes 
        // 7 -> 6 -> NUll
        lnList.push(7); 
  
        // Insert 1 at the beginning. So linked list becomes 
        // 1 -> 7 -> 6 -> NUll
        lnList.push(1); 
  
        // Insert 4 at the end. So linked list becomes 
        // 1 -> 7 -> 6 -> 4 -> NUll
        lnList.append(4); 
  
        // Insert 8, after 7. So linked list becomes 
        // 1 -> 7 -> 8 -> 6 -> 4 -> NUll
        lnList.insertAfter(lnList.head.next, 8); 
  
        System.out.println("Created Linked list is: " + lnList); 
    }
}