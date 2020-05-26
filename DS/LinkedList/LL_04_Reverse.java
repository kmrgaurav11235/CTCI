/*
https://www.geeksforgeeks.org/reverse-a-linked-list/
- Iterative Method
    1) Initialize three pointers prev as NULL, curr as head and next as NULL.
    2) Iterate trough the linked list. In loop, do following.
    3) Before changing next of current, store next node
        next = curr->next
    4) Now change next of current. This is where actual reversing happens
        curr->next = prev
    5) Move prev and curr one step forward
        prev = curr
        curr = next

- Recursive Method:
   1) Divide the list in two parts - first node and rest of the linked list.
   2) Call reverse for the rest of the linked list.
   3) Link rest to first.
   4) Fix head pointer

*/
public class LL_04_Reverse {
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

        Node reverseIterative(Node node) {
            if (node == null || node.next == null) {
                return node;
            }
            Node prev = null, curr = node;
            while (curr != null) {
                Node next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

        Node reverseRecursive(Node node) {
            if (node == null || node.next == null) {
                return node;
            }
            Node next = node.next;
            Node newHeadNode = reverseRecursive(node.next);
            next.next = node;
            node.next = null;
            return newHeadNode;
        }
    }
    public static void main(String[] args) {
        LinkedList lnList = new LinkedList(); 
  
        lnList.push(7); 
        lnList.push(1); 
        lnList.push(3); 
        lnList.push(2); 
  
        System.out.println("Created Linked list is:" + lnList); 
        lnList.head = lnList.reverseIterative(lnList.head);
        System.out.println("\nLinked List after reversal:" + lnList); 
        lnList.head = lnList.reverseRecursive(lnList.head);
        System.out.println("\nLinked List after reversal again:" + lnList); 
    }
}