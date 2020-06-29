/*
https://www.geeksforgeeks.org/flattening-a-linked-list/

- Given a linked list where every node represents a linked list and contains two pointers of its type:
    1) Pointer to next node in the main list; we call it 'right' pointer.
    2) Pointer to a linked list where this node is head; we call it 'down' pointer.
- All linked lists are sorted. 
- Example:
        5 -> 10 -> 19 -> 28
        ⬇    ⬇    ⬇    ⬇
        7    20    22    35
        ⬇    ⬇    ⬇    ⬇
        8          50    40
        ⬇                ⬇
        30               45

- Write a function flatten() to flatten the lists into a single linked list. The flattened linked list should 
    also be sorted. 
- For example, for the above input list, output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.

- The idea is to use merge() process of merge sort for linked lists. We use merge() to merge lists one by one. 
    We recursively merge() the current list with already flattened list. The down pointer is used to link nodes 
    of the flattened list.
*/
public class LL_13_FlattenLL {
    static class Node {
        int data;
        Node right, down;

        Node(int data) {
            this.data = data;
            right = null;
            down = null;
        }
    }

    static class LinkedList {
        Node head;

        public Node push(Node node, int data) {
            Node temp = new Node(data);
            temp.down = node;
            node = temp;
            return node;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[head -> ");
            Node p = head;
            while (p != null) {
                sb.append(p.data + " -> ");
                p = p.down;
            }
            sb.append(" null]");
            return sb.toString();
        }

        private Node merge(Node head1, Node head2) {
            if(head1 == null) {
                return head2;
            } else if (head2 == null) {
                return head1;
            } else if (head1.data <= head2.data) {
                head1.down = merge(head1.down, head2);
                return head1;
            } else {
                head2.down = merge(head1, head2.down);
                return head2;
            }
        }

        public Node flatten(Node node) {
            if (node == null || node.right == null) {
                return node;
            }
            Node head1 = node;
            Node head2 = node.right;

            while(head2 != null) {
                head1 = merge(head1, head2);
                head2 = head2.right;
            }
            return node;
        }
    }
    public static void main(String[] args) {
        LinkedList list = new LinkedList(); 
  
        /* Let us create the following linked list 
            5 -> 10 -> 19 -> 28
            ⬇    ⬇    ⬇    ⬇
            7    20    22    35
            ⬇    ⬇    ⬇    ⬇
            8          50    40
            ⬇                ⬇
            30               45
        */
  
        list.head = list.push(list.head, 30); 
        list.head = list.push(list.head, 8); 
        list.head = list.push(list.head, 7); 
        list.head = list.push(list.head, 5); 
  
        list.head.right = list.push(list.head.right, 20); 
        list.head.right = list.push(list.head.right, 10); 
  
        list.head.right.right = list.push(list.head.right.right, 50); 
        list.head.right.right = list.push(list.head.right.right, 22); 
        list.head.right.right = list.push(list.head.right.right, 19); 
  
        list.head.right.right.right = list.push(list.head.right.right.right, 45); 
        list.head.right.right.right = list.push(list.head.right.right.right, 40); 
        list.head.right.right.right = list.push(list.head.right.right.right, 35); 
        list.head.right.right.right = list.push(list.head.right.right.right, 20); 
  
        // flatten the list 
        list.head = list.flatten(list.head); 
  
        System.out.println(list);; 
    }
}