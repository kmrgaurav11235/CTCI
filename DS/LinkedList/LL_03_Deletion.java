/*
https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/

- To delete a node from linked list, we need to do following steps:
    1) Find previous node of the node to be deleted.
    2) Change the next of previous node.
    3) Free memory for the node to be deleted.
*/
public class LL_03_Deletion {
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

        public void deleteNode(int key) {
            if (head == null) {
                System.err.println("Empty Linked List!");
                return;
            }
            Node p = head, prev = null;
            while (p != null && p.data != key) {
                prev = p;
                p = p.next;
            }

            if (p == null) {
                System.out.println("Key not found in Linked List.");
            } else if (prev == null) {
                // Key found at head
                head = head.next;
            } else {
                prev.next = p.next;
            }
        }

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
    }
    public static void main(String[] args) {
        LinkedList lnList = new LinkedList(); 
  
        lnList.push(7); 
        lnList.push(1); 
        lnList.push(3); 
        lnList.push(2); 
  
        System.out.println("Created Linked list is:" + lnList); 
  
        lnList.deleteNode(1); // Delete node at position 4 
  
        System.out.println("\nLinked List after Deletion of Node 1:" + lnList); 
    }
}