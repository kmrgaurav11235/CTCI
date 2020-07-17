/*
https://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/

- Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST.
- Input Tree:
         10
        /  \
       5    8
      / \
     2   20

- In the above tree, nodes 20 and 8 must be swapped to fix the tree.  
- Following is the output tree:
         10
        /  \
       5    20
      / \
     2   8

- Since inorder traversal of BST is always a sorted array, the problem can be reduced to a problem where two 
    elements of a sorted array are swapped. There are two cases that we need to handle:
    1) The swapped nodes are not adjacent in the inorder traversal of the BST.
        e.g. Nodes 5 and 25 are swapped in {3 5 7 8 10 15 20 25}. 
        The inorder traversal of the given tree is 3 25 7 8 10 15 20 5 
    During inorder traversal, we find node 7 is smaller than the previous visited node 25. Here save the context 
    of node 25 (previous node). Again, we find that node 5 is smaller than the previous node 20. This time, we 
    save the context of node 5 ( current node ). Finally swap the two node’s values.


    2) The swapped nodes are adjacent in the inorder traversal of BST.
        e.g. Nodes 7 and 8 are swapped in {3 5 7 8 10 15 20 25}. 
        The inorder traversal of the given tree is 3 5 8 7 10 15 20 25 
    Unlike case 1, here only one point exists where a node value is smaller than previous node value. Node 7 
    is smaller than node 8.

- We will maintain three pointers, first, middle and last. When we find the first point where current node value is 
    smaller than previous node value, we update the first with the previous node & middle with the current node. 
- When we find the second point where current node value is smaller than previous node value, we update the last 
    with the current node. 
- In case #2, we will never find the second point. So, last pointer will not be updated. 
- After processing, if the last node value is null, then two swapped nodes of BST are adjacent.
- Time Complexity: O(n)
*/
public class BST_08_CorrectSwappedNodes {
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

        // variables to be used for correcting swapped nodes
        private Node previous;
        private Node first, middle, last;

        public void inorder(Node node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }

        private void swapData(Node node1, Node node2) {
            int temp = node1.data;
            node1.data = node2.data;
            node2.data = temp;
        }

        // Do inorder traversal to find out the two swapped nodes. Set three pointers, first, middle and last. 
        // If the swapped nodes are adjacent to each other, then first and middle contain the resultant nodes.
        // Else, first and last contain the resultant nodes.
        private void findSwappedNodes(Node node) {
            if (node == null) {
                return;
            }
            // Recur for the left subtree
            findSwappedNodes(node.left);

            // If this node is smaller than the previous node, it is violating the BST rule. 
            if (previous != null && previous.data > node.data) {
                if (first == null) {
                    // first violation
                    first = previous;
                    middle = node;
                } else {
                    // second violation
                    last = node;
                }
            }

            // Mark this node as previous
            previous = node;
            // Recur for the right subtree
            findSwappedNodes(node.right);
        }

        public void correctSwappedNodes() {
            if (root == null) {
                return;
            }
            // Initialize pointers needed for findSwappedNodes() 
            previous = null;
            first = middle = last = null;

            findSwappedNodes(root);

            if (last != null) {
                // All 3 are present
                swapData(first, last);
            } else if (first != null) {
                // Only the first two are present
                swapData(first, middle);
            }
        }
    }

    public static void main(String[] args) {
        /*   6 
            / \ 
           10  2 
          / \ / \ 
         1  3 7 12 
          
        10 and 2 are swapped 
        */
  
        Node root = new Node(6);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(12);
        root.right.left = new Node(7);

        BinaryTree tree = new BinaryTree();
        tree.root = root;

        System.out.println("Inorder Traversal of the original tree:");
        tree.inorder(root);

        tree.correctSwappedNodes();

        System.out.println("\nInorder Traversal of the fixed tree:");
        tree.inorder(root);
    }
}