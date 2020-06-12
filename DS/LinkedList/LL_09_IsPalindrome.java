/*
https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/

- METHOD 1: Use a Stack. This uses O(n) time and space.
    1) Traverse the given list from head to tail and push every visited node to stack.
    2) Traverse the list again. For every visited node, pop a node from stack and compare 
        data of popped node with currently visited node.
    3) If all nodes matched, then return true, else false.

- METHOD 2: By reversing the list. This method takes O(n) time and O(1) extra space.
    1) Get the middle of the linked list.
    2) Reverse the second half of the linked list.
    3) Check if the first half and second half are identical.
    4) Construct the original linked list by reversing the second half again and attaching it 
        back to the first half.
- When number of nodes are even, the first and second half contain exactly half nodes. The 
    challenging thing in this method is to handle the case when number of nodes are odd. We don’t 
    want the middle node as part of any of the lists as we are going to compare them for equality. 
    For odd case, we use a separate variable ‘midnode’.
*/
public class LL_09_IsPalindrome {

    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    static class LinkedList {
        Node head;
        public void push(char data) {
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

        private boolean isIdentical(Node root1, Node root2) {
            // Check if two Linked Lists are identical
            while (root1 != null && root2 != null) {
                if (root1.data != root2.data) {
                    return false;
                }
                root1 = root1.next;
                root2 = root2.next;
            }
            if (root1 == null && root2 == null) {
                return true;
            } else {
                return false;
            }
        }

        private Node reverse(Node node) {
            // Reverse a Linked List
            if (node == null || node.next == null) {
                return node;
            }
            Node prev = null;
            Node curr = node;
            Node nxt = null;

            while (curr != null) {
                nxt = curr.next;
                curr.next = prev;

                prev = curr;
                curr = nxt;
            }
            return prev;
        }

        public boolean isPalindrome() {
            if (head == null || head.next == null) {
                return true;
            }

            // Get the middle of the list.
            Node fast = head, slow = head, prevSlow = null;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                // We need previous of the slow_ptr for ending the first half
                prevSlow = slow;
                slow = slow.next;
            }

            Node midNode = null;
            /* 
            'fast' would become null when there are even elements in the list and not null for 
            odd elements. We need to skip the middle node for odd case and store it in 'midNode' 
            so that we can restore the original list.
            */
            if (fast != null) {
                midNode = slow;
                slow = slow.next;
            }

            // null terminate first half 
            prevSlow.next = null;
            Node secondHalf = slow;

            // reverse the second half and compare it with first half 
            Node secondHalfReversed = reverse(secondHalf);
            boolean isPalindrome = isIdentical(head, secondHalfReversed);
            // Construct the original list back
            Node secondHalfCorrected = reverse(secondHalfReversed);

            if (midNode == null) {
                prevSlow.next = secondHalfCorrected;
            } else {
                prevSlow.next = midNode;
                midNode.next = secondHalfCorrected;
            }

            return isPalindrome;
        }
    }
    public static void main(String[] args) {
        LinkedList lList = new LinkedList(); 
  
        char str[] = { 'a', 'b', 'a', 'c', 'a', 'b', 'a' }; 
        for (int i = 0; i < str.length; i++) { 
            lList.push(str[i]); 
            System.out.println("List: " + lList); 

            if (lList.isPalindrome()) { 
                System.out.println("Is Palindrome.\n"); 
            } else { 
                System.out.println("Not Palindrome.\n"); 
            } 
        } 
    }
}