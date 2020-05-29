/*
https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/

- Given a linked list, write a function to reverse every k nodes.
- Example:
    Input: 1->2->3->4->5->6->7->8->NULL, K = 3
    Output: 3->2->1->6->5->4->8->7->NULL

    Input: 1->2->3->4->5->6->7->8->NULL, K = 5
    Output: 5->4->3->2->1->8->7->6->NULL 

- The algorithm combines recursion and simple linked list reversal.
    1) Reverse the first sub-list of size k. While reversing keep track of the next 
        node and previous node. Let the pointer to the next node be next and pointer 
        to the previous node be prev. See this post for reversing a linked list.
    2) Recursively call for rest of the list and link the two sub-lists.
        head.next = reverse(next, k) 
    3) Return prev. 'prev' becomes the new head of the list.

*/
public class LL_05_ReverseK {
    
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

        static Node reverse(Node head, int k) {
            if (head == null || head.next == null) {
                return head;
            }

            Node prev = null, curr = head;
            for (int i = 0; i < k && curr != null; i++) {
                Node nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
            }

            if (curr != null) {
                head.next = reverse(curr, k);
            }
            return prev;
        }
    }
    public static void main(String args[]) 
    { 
        LinkedList lList = new LinkedList(); 
        // Constructed Linked List is 1->2->3->4->5->6-> 7->8->8->9->10->null
        lList.push(10); 
        lList.push(9); 
        lList.push(8); 
        lList.push(7); 
        lList.push(6); 
        lList.push(5); 
        lList.push(4); 
        lList.push(3); 
        lList.push(2); 
        lList.push(1); 
          
        System.out.println("Given Linked List: " + lList); 
          
        lList.head = lList.reverse(lList.head, 3); 
  
        System.out.println("k-Reversed list: " + lList); 
    } 
}