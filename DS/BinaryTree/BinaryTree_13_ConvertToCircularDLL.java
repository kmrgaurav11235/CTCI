/*
https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/

- Given a Binary Tree, convert it to a Circular Doubly Linked List (In-Place).
    * The left and right pointers in nodes are to be used as previous and next pointers respectively in converted 
        Circular Linked List.
    * The order of nodes in List must be same as Inorder of the given Binary Tree.
    * The first node of Inorder traversal must be head node of the Circular List.
-         Tree:
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30
- Circular DLL: 4 <-> 30 <-> 10 <-> 6 <-> 26 <-> 3 <-> 3

- Algorithm:
    1) Recursively convert left subtree to a circular DLL. Let the converted list be leftList.
    2) Recursively convert right subtree to a circular DLL. Let the converted list be rightList.
    3) Make a circular linked list of root of the tree, make left and right of root to point to itself.
    4) Concatenate leftList with list of single root node.
    5) Concatenate the list produced in step above with rightList.
- Note that the above code traverses tree in Postorder fashion. We can traverse in inorder fashion also. We can first 
    concatenate left subtree and root, then recur for right subtree and concatenate the result with left-root 
    concatenation.
- How to Concatenate two circular DLLs:
    1) Get the last node of the left list. Retrieving the last node is an O(1) operation, since the prev pointer of the 
        head points to the last node of the list.
    2) Connect it with the first node of the right list
    3) Get the last node of the second list
    4) Connect it with the head of the list.

- IMPORTANT: If the problem is about Converting Binary Tree into a Simple Doubly Linked List (not a circular one), just 
    break the connection between head and tail at the end.

*/
public class BinaryTree_13_ConvertToCircularDLL {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class BinaryTree {
        Node root;

        BinaryTree() {
            root = null;
        }

        // Concatenate both the lists and returns the head of the List 
        private Node concatCircularDoublyLinkedLists(Node leftList, Node rightList) {
            // If either of the list is empty, then return the other list 
            if (leftList == null) {
                return rightList;
            } else if (rightList == null) {
                return leftList;
            }

            Node leftEnd = leftList.left;
            Node rightEnd = rightList.left;

            // Connect the last node of Left List with the first Node of the right List 
            leftEnd.right = rightList;
            rightList.left = leftEnd;

            // left of first node refers to the last node in the list 
            leftList.left = rightEnd;
            // Right of last node refers to the first node of the List 
            rightEnd.right = leftList;

            return leftList;
        }

        public Node convertbinaryTreeToCircularDoublyLinkedList(Node node) {
            if (node == null) {
                return null;
            }

            // Recursively convert left and right subtrees 
            Node leftList = convertbinaryTreeToCircularDoublyLinkedList(node.left);
            Node rightList = convertbinaryTreeToCircularDoublyLinkedList(node.right);

            // Make a circular linked list of single node. To do so, make the right and left pointers of this node 
            // point to itself 
            node.left = node;
            node.right = node;

            // Concatenate the left list with the list with the current node
            // Cconcatenate the returned list with the right List
            return concatCircularDoublyLinkedLists(concatCircularDoublyLinkedLists(leftList, node), rightList);
        }
    }

    public static void main(String[] args) {
        // Build the tree
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        // head refers to the head of the Link List
        Node head = tree.convertbinaryTreeToCircularDoublyLinkedList(tree.root);

        // Display the Circular LinkedList
        displayCircularDoublyLinkedList(head);
    }

    static void displayCircularDoublyLinkedList(Node head) {
        StringBuilder sb = new StringBuilder("[head -> ");
        Node p = head;
        do {
            sb.append(p.data + " -> ");
            p = p.right;
        } while (p != head);
        sb.append(" head]");
        System.out.println(sb);
    }
}