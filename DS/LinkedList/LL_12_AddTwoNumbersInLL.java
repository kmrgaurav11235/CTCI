/*
https://www.geeksforgeeks.org/sum-of-two-linked-lists/

- Given two numbers represented by two linked lists, write a function that returns 
    the sum list. The sum list is linked list representation of the addition of two 
    input numbers. 
- It is not allowed to modify the lists. 
- Example
    Input:
        First List: 5->6->3  // represents number 563
        Second List: 8->4->2 //  represents number 842
    Output:
        Resultant list: 1->4->0->5  // represents number 1405
*/
public class LL_12_AddTwoNumbersInLL {

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

        public int getLength() {
            Node p = head;
            int length = 0;
            while (p != null) {
                length++;
                p = p.next;
            }
            return length;
        }
    }

    static class NodeAndCarry {
        Node node;
        int carry;

        public NodeAndCarry(Node node, int carry) {
            this.node = node;
            this.carry = carry;
        }
    }

    private static Node copyLL(Node node) {
        Node temp = new Node(0);
        Node p = temp;

        while (node != null) {
            p.next = new Node(node.data);
            p = p.next;
        }
        return temp.next;
    }

    private static NodeAndCarry addLLOfSameSize(Node p, Node q) {
        if (p == null) {
            return new NodeAndCarry(null, 0);
        }
        NodeAndCarry nxt = addLLOfSameSize(p.next, q.next);

        int sum = p.data + q.data + nxt.carry;
        int carry = sum / 10;
        sum = sum % 10;

        Node node = new Node(sum);
        node.next = nxt.node;
        return new NodeAndCarry(node, carry);
    }

    private static NodeAndCarry propagateCarry(Node start, Node end, Node sumStart, int sumCarry) {
        /*
        COMMENT: A more intuitive way to propagate carry is to use a Stack:
        1) Traverse the larger list from 'start' to before 'sumStart' and push every visited node to stack.
        2) Pop a node from stack and compare. If you have carry, add to it.
        */
        int sum = 0;
        if (start.next == end) {
            sum = start.data + sumCarry;
            int newCarry = sum / 10;
            sum = sum % 10;

            Node temp = new Node(sum);
            temp.next = sumStart;
            return new NodeAndCarry(temp, newCarry);
        } else {
            NodeAndCarry nxt = propagateCarry(start.next, end, sumStart, sumCarry);
            sum = start.data + nxt.carry;
            int newCarry = sum / 10;
            sum = sum % 10;

            Node temp = new Node(sum);
            temp.next = nxt.node;
            return new NodeAndCarry(temp, newCarry);
        }
    }

    public static LinkedList addTwoLists(LinkedList list1, LinkedList list2) {
        LinkedList sumList = new LinkedList();

        if (list1.head == null) {
            sumList.head = copyLL(list1.head);
        } else if (list2.head == null) {
            sumList.head = copyLL(list2.head);
        }
        int size1 = list1.getLength();
        int size2 = list2.getLength();

        if (size1 == size2) {
            NodeAndCarry nodeAndCarry = addLLOfSameSize(list1.head, list2.head);
            if (nodeAndCarry.carry == 0) {
                sumList.head = nodeAndCarry.node;
            } else {
                Node carryNode = new Node(nodeAndCarry.carry);
                carryNode.next = nodeAndCarry.node;
                sumList.head = carryNode;
            }
            return sumList;
        } else if (size1 < size2) {
            // Swap the lists and the size
            LinkedList tempList = list1;
            list1 = list2;
            list2 = tempList;

            int tempSize = size1;
            size1 = size2;
            size2 = tempSize;
        } 
        // Now size1 > size2

        Node p = list1.head;
        for (int i = 0; i < (size1 - size2); i++) {
            p = p.next;
        }
        // Now p is at the same level as list2.head

        NodeAndCarry secondPart = addLLOfSameSize(p, list2.head);

        NodeAndCarry firstPart = propagateCarry(list1.head, p, secondPart.node, secondPart.carry);

        if (firstPart.carry == 0) {
            sumList.head = firstPart.node;
        } else {
            Node temp = new Node(firstPart.carry);
            temp.next = firstPart.node;
            sumList.head = temp;
        }
        return sumList;
    }

    public static void main(String[] args) {
        System.out.println("******* TEST 1 *******");
        // creating first list 
        LinkedList list1 = new LinkedList(); 
        list1.head = new Node(6); 
        list1.head.next = new Node(4); 
        list1.head.next.next = new Node(9); 
        list1.head.next.next.next = new Node(5); 
        list1.head.next.next.next.next = new Node(7); 
        System.out.println("First List is: " + list1);  

        // creating seconnd list 
        LinkedList list2 = new LinkedList(); 
        list2.head = new Node(4); 
        list2.head.next = new Node(8); 
        System.out.println("Second List is: " + list2); 

        // add the two lists and see the result 
        LinkedList result1 = addTwoLists(list1, list2); 
        System.out.println("Resultant List is: " + result1); 

    
        System.out.println("\n******* TEST 2 *******");
        // creating first list 
        LinkedList list3 = new LinkedList(); 
        list3.head = new Node(9); 
        list3.head.next = new Node(9); 
        list3.head.next.next = new Node(9); 
        System.out.println("First List is: " + list3);  

        // creating seconnd list 
        LinkedList list4 = new LinkedList(); 
        list4.head = new Node(1); 
        list4.head.next = new Node(8); 
        System.out.println("Second List is: " + list4); 

        // add the two lists and see the result 
        LinkedList result2 = addTwoLists(list3, list4); 
        System.out.println("Resultant List is: " + result2); 
    }
}