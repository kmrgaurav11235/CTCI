/*
https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/

- Given two numbers represented by two lists in reverse order, write a method that returns 
    the sum list. The sum list is list representation of the addition of two input numbers.
- e.g.
    Input:
    List1: 7 -> 5 -> 9 -> 4 -> 6 // represents number 64957
    List2: 8 -> 4 // represents number 48
    Output:
    Resultant list: 5 -> 0 -> 0 -> 5 -> 6 // represents number 65005
    Explanation: 64957 + 48 = 65005 
- Algorithm: Traverse both lists and One by one pick nodes of both lists and add the values. 
    If the sum is more than 10 then make carry as 1 and reduce sum. Handle the case where one 
    list has more elements than the other. 
*/
public class LL_11_AddTwoNumbersInReversedLL {

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

    static LinkedList addTwoLists(LinkedList list1, LinkedList list2) {
        LinkedList sumList = new LinkedList();
        sumList.head = new Node(0); // temporary head node

        Node prev = sumList.head;
        int carry = 0;
        Node num1 = list1.head, num2 = list2.head;

        while (num1 != null || num2 != null || carry != 0) {
            int sum = carry;
            if (num1 != null) {
                sum += num1.data;
                num1 = num1.next;
            }
            if (num2 != null) {
                sum += num2.data;
                num2 = num2.next;
            }
            carry = sum / 10; // Update carry for next pass of the loop
            sum = sum % 10; // Update sum for node creation

            prev.next = new Node(sum);
            prev = prev.next;
        }
        sumList.head = sumList.head.next; // removing the temporary head node
        return sumList;
    }

    public static void main(String[] args) {
        // creating first list 
        LinkedList list1 = new LinkedList(); 
        list1.head = new Node(7); 
        list1.head.next = new Node(5); 
        list1.head.next.next = new Node(9); 
        list1.head.next.next.next = new Node(4); 
        list1.head.next.next.next.next = new Node(6); 
        System.out.println("First List is: " + list1);  

        // creating seconnd list 
        LinkedList list2 = new LinkedList(); 
        list2.head = new Node(8); 
        list2.head.next = new Node(4); 
        System.out.println("Second List is: " + list2); 

        // add the two lists and see the result 
        LinkedList result = addTwoLists(list1, list2); 
        System.out.println("Resultant List is: " + result); 
    }
}