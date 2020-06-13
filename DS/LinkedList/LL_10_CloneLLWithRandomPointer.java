/*
https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/

- Given a Linked List with a second pointer that can point to any node in the list. 
    We have to duplicate this list.
- Algorithm: This solution works using constant space.
    1) Create the copy of node 1 and insert it between node 1 & node 2 in original Linked 
        List, create the copy of 2 and insert it between 2 & 3. Continue in this fashion 
        and add the copy of N after the Nth node.
    2) Now copy the arbitrary link in this fashion
        original.next.arbitrary = original.arbitrary.next;
    3) Now restore the original and copy linked lists in this fashion in a single loop.
        original.next = original.next.next;
        copy.next = copy.next.next;
    4) Make sure that last element of original.next is null.
- Time Complexity: O(n)
- Auxiliary Space: O(1)
*/
public class LL_10_CloneLLWithRandomPointer {

    static class Node {
        int data;
        Node next, random;

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
                sb.append("{data: " + p.data + ", random: " 
                    + (p.random == null ? "null" : p.random.data)
                    + "} -> ");
                p = p.next;
            }
            sb.append(" null]");
            return sb.toString();
        }
    }
    
    private static LinkedList cloneList(LinkedList originalList) {
        if (originalList == null || originalList.head == null) {
            return null;
        }

        // Insert duplicate node after every node of original list  
        Node originalNode = originalList.head;
        while (originalNode != null) {
            Node temp = new Node(originalNode.data);
            temp.next = originalNode.next;
            originalNode.next = temp;

            originalNode = temp.next;
        }

        // Adjust the random pointers of the newly added nodes 
        originalNode = originalList.head;
        while (originalNode != null) {
            originalNode.next.random = (originalNode.random == null) ? null : originalNode.random.next;
            originalNode = originalNode.next.next;
        }

        // Save the start of copied linked list in a new Object
        LinkedList duplicateList = new LinkedList();
        duplicateList.head = originalList.head.next;

        // Separate the original list and copied list  
        originalNode = originalList.head;
        Node duplicateNode = null;
        while (originalNode != null) {
            duplicateNode = originalNode.next;
            originalNode.next = originalNode.next.next;
            duplicateNode.next = (duplicateNode.next == null) ? null : duplicateNode.next.next;

            originalNode = originalNode.next;
        }

        return duplicateList;
    }
    public static void main(String[] args) {
        LinkedList originalList = new LinkedList();
        originalList.head = new Node(1);
        originalList.head.next = new Node(2);  
        originalList.head.next.next = new Node(3);  
        originalList.head.next.next.next = new Node(4);  
        originalList.head.next.next.next.next = new Node(5);  
        // 1's random points to 3  
        originalList.head.random = originalList.head.next.next;  
        // 2's random points to 1  
        originalList.head.next.random = originalList.head;  
        // 3's and 4's random points to 5  
        originalList.head.next.next.random = originalList.head.next.next.next.next;  
        originalList.head.next.next.next.random = originalList.head.next.next.next.next;  
        // 5's random points to 2  
        originalList.head.next.next.next.next.random = originalList.head.next;  
    
        System.out.println("Original list : " + originalList);  
    
        LinkedList clonedList = cloneList(originalList);  
        System.out.println("Cloned list : " + clonedList);      
    }
}