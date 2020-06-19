/*
NEEDS TO BE FIXED
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

    private static Node moveForward(Node p, int n) {
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        return p;
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

    private static NodeAndCarry propagateCarry(Node node, Node end, int carry, Node secondPart) {
        int sum = 0;
        if (node == end) {
            sum = node.data + carry;
            int newCarry = sum % 10;
            sum = sum / 10;
            Node temp = new Node(sum);
            temp.next = secondPart;
            return new NodeAndCarry(temp, newCarry);
        } else {
            NodeAndCarry nxt = propagateCarry(node.next, end, carry, secondPart);
            sum = node.data + nxt.carry;
            int newCarry = sum % 10;
            sum = sum / 10;
            Node temp = new Node(sum);
            temp.next = nxt.node;
            return new NodeAndCarry(temp, newCarry);
        }
    }

    public static LinkedList addTwoLists(LinkedList list1, LinkedList list2) {
        LinkedList sumList = new LinkedList();

        Node num1 = list1.head;
        Node num2 = list2.head;

        if (num1 == null) {
            sumList.head = copyLL(num2);
        } else if (num2 == null) {
            sumList.head = copyLL(num1);
        }
        int m = list1.getLength();
        int n = list2.getLength();

        Node p = num1, q = num2;

        if (m > n) {
            p = moveForward(p, m - n);
        } else if (m < n) {
            q = moveForward(q, n - m);
        } else {
            // m == n
            NodeAndCarry nodeAndCarry = addLLOfSameSize(p, q);
            if (nodeAndCarry.carry != 0) {
                Node temp = new Node(nodeAndCarry.carry);
                temp.next = nodeAndCarry.node;
                sumList.head = temp;
            } else {
                sumList.head = nodeAndCarry.node;
            }
            return sumList;
        }
        NodeAndCarry secondPart = addLLOfSameSize(p, q);
        NodeAndCarry firstPart = null;

        if (m > n) {
            firstPart = propagateCarry(num1, p, secondPart.carry, secondPart.node);
        } else if (m < n) {
            firstPart = propagateCarry(num2, q, secondPart.carry, secondPart.node);
        } 

        if (firstPart.carry != 0) {
            Node temp = new Node(firstPart.carry);
            temp.next = firstPart.node;
            sumList.head = temp;
        } else {
            sumList.head = firstPart.node;
        }
        return sumList;
    }

    public static void main(String[] args) {
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
        LinkedList result = addTwoLists(list1, list2); 
        System.out.println("Resultant List is: " + result); 
    }
}