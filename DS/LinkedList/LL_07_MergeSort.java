/*
https://www.geeksforgeeks.org/merge-sort-for-linked-list/
- Merge sort is often preferred for sorting a linked list. The slow random-access performance 
    of a linked list makes some other algorithms (such as quick sort) perform poorly, and others 
    (such as heap sort) completely impossible.
- Let head be the first node of the linked list to be sorted. Note that we need a reference to 
    head in Merge sort for linked list. This is because the implementation changes next links to 
    sort the linked lists (not data at the nodes). So, head node has to be changed if the data at 
    the original head is not the smallest value in the linked list.

- MergeSort(headRef)
    1) If the head is NULL or there is only one element in the Linked List then return.
    2) Else divide the linked list into two halves - a and b.
    3) Sort the two halves a and b.
        MergeSort(a);
        MergeSort(b);
    4) Merge the sorted a and b using SortedMerge() (already discussed in previous program). Then, 
        update the head pointer.
-  Time Complexity: O(n Log n)
*/
public class LL_07_MergeSort {

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

        public Node mergeSort(Node node) {
            if (node == null || node.next == null) {
                return node;
            }

            // get the middle of the list 
            Node mid = getMiddle(node);
            Node midNext = mid.next;

            // separate the two lists
            mid.next = null;

            // divide
            Node sorted1 = mergeSort(node);
            Node sorted2 = mergeSort(midNext);

            // conquer
            return merge(sorted1, sorted2);
        }

        private Node getMiddle(Node node) {
            Node fastPointer = node;
            Node slowPointer = node;

            while(fastPointer.next != null && fastPointer.next.next != null) {
                fastPointer = fastPointer.next.next;
                slowPointer = slowPointer.next;
            }
            return slowPointer;
        }

        private Node merge(Node head1, Node head2) {
            if(head1 == null) {
                return head2;
            } else if (head2 == null) {
                return head1;
            } else if (head1.data < head2.data) {
                head1.next = merge(head1.next, head2);
                return head1;
            } else {
                head2.next = merge(head1, head2.next);
                return head2;
            }
        }
    }
    
    public static void main(String[] args) {
        LinkedList lList = new LinkedList(); 
        // Create a unsorted linked list: 2->3->20->5->10->15 
        lList.push(15); 
        lList.push(10); 
        lList.push(5); 
        lList.push(20); 
        lList.push(3); 
        lList.push(2); 
        System.out.println("Unsorted Linked List is: " + lList); 

        // Apply merge Sort 
        lList.head = lList.mergeSort(lList.head); 

        System.out.println("Sorted Linked List is: " + lList); 
    }
}