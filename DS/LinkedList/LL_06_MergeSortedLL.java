/*
https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/

- Write a function that takes two lists, each of which is sorted in increasing order, and merges the 
    two together into one list which is in increasing order. The new list should be made by splicing
    together the nodes of the first two lists.
- For example if the first linked list a is 5->10->15 and the other linked list b is 2->3->20, then 
    the function should return a pointer to the head node of the merged list 2->3->5->10->15->20.
- Method 1 - Using Recursion: Merge is one of those nice recursive problems where the recursive solution 
    code is much cleaner than the iterative code. It will, however, use stack space which is proportional 
    to the length of the lists.
- Method 2 - Using Iteration.
*/
public class LL_06_MergeSortedLL {
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
    }

    static Node mergeLinkedListsIterative(Node head1, Node head2) {
        Node temp = new Node(0);

        Node p = head1, q = head2, r = temp;
        while (p != null && q != null) {
            if (p.data <= q.data) {
                r.next = p;
                r = r.next;
                p = p.next;
            } else {
                r.next = q;
                r = r.next;
                q = q.next;
            }
        }
        if (p == null) {
            r.next = q;
        } else if (q == null) {
            r.next = p;
        } 
        return temp.next;
    }

    static Node mergeLinkedListsRecursive(Node head1, Node head2) {
        if(head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        } else if (head1.data < head2.data) {
            head1.next = mergeLinkedListsRecursive(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeLinkedListsRecursive(head1, head2.next);
            return head2;
        }
    }
    public static void main(String[] args) {
        LinkedList lnList1 = new LinkedList(); 
        lnList1.push(14); 
        lnList1.push(3); 
        lnList1.push(1); 
        lnList1.push(-4); 

        LinkedList lnList2 = new LinkedList(); 
        lnList2.push(11); 
        lnList2.push(7); 
        lnList2.push(6); 
        lnList2.push(5); 
  
        System.out.println("Linked list 1 is: " + lnList1); 
        System.out.println("Linked list 2 is: " + lnList2); 

        LinkedList lnList3 = new LinkedList(); 
        // lnList3.head = mergeLinkedListsRecursive(lnList1.head, lnList2.head);
        lnList3.head = mergeLinkedListsIterative(lnList1.head, lnList2.head);
        System.out.println("Linked lists after merging is: " + lnList3); 
    }
}