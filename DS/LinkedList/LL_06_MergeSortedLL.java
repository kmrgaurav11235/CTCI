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
        while (true) {
            if (p == null) {
                r.next = q;
            } else if (q == null) {
                r.next = p;
            } else {
                if (p.data < q.data) {
                    r.next = p;
                    r = r.next;
                    p = p.next;
                } else {
                    r.next = q;
                    r = r.next;
                    q = q.next;
                }
            }
            return temp.next;
        }
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
        lnList3.head = mergeLinkedListsRecursive(lnList1.head, lnList2.head);
        System.out.println("Linked lists after merging is: " + lnList3); 
    }
}