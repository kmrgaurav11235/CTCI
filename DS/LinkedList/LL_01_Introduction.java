/*
https://www.geeksforgeeks.org/linked-list-set-1-introduction/

- Like arrays, Linked List is a linear data structure. Unlike arrays, linked list elements are 
    not stored at a contiguous location; the elements are linked using pointers.
- Advantages over arrays
    1) Dynamic size
    2) Ease of insertion/deletion

- Drawbacks:
    1) Random access is not allowed. We have to access elements sequentially starting from the 
        first node. So we cannot do binary search with linked lists efficiently with its default 
        implementation.
    2) Extra memory space for a pointer is required with each element of the list.
    3) Not cache friendly. Since array elements are contiguous locations, there is locality of 
        reference which is not there in case of linked lists.

- Representation:
    * A linked list is represented by a pointer to the first node of the linked list. The first node 
        is called the head. If the linked list is empty, then the value of the head is NULL.
    * Each node in a list consists of at least two parts:
        1) Data
        2) Pointer (Or Reference) to the next node
    * In Java, LinkedList can be represented as a class and a Node as a separate class. The LinkedList 
        class contains a reference of Node class type.
*/
public class LL_01_Introduction {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

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
        /* Start with the empty list. */
        LinkedList lnList = new LinkedList(); 
  
        lnList.head = new Node(1); 
        Node second = new Node(2); 
        Node third = new Node(3); 
  
        lnList.head.next = second; // Link first node with the second node 
        second.next = third; // Link first node with the second node 

        System.out.println(lnList);
    }
}