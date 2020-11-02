import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/invert-binary-tree/discuss/62707/Straightforward-DFS-recursive-iterative-BFS-solutions
https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/

- Invert a binary tree. Or Convert a Binary Tree into its Mirror Tree.
- Example:

    Input:

         4
       /   \
      2     7
     / \   / \
    1   3 6   9
    Output:

         4
       /   \
      7     2
     / \   / \
    9   6 3   1

- Trivia: This problem was inspired by this original tweet by Max Howell: 
    https://twitter.com/mxcl/status/608682016205344768
    > Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a 
        whiteboard so f*** off.
- Recursive solution is straightforward.
- It is also bound to the application stack, which means that it is not scalable. You can find the problem size that 
    will overflow the stack and crash your application), so more robust solution would be to use stack data structure.
- We can easily create an iterative solution using BFS - or so called level order traversal.

*/
public class BTCC_03_InvertBinaryTree {

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

        private void preOrder(Node root) {
            if (root != null) {
                System.out.print(root.data + " ");
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        void preOrder() {
            preOrder(root);
            System.out.println("");
        }

        public Node invertRecursive(Node root) {
            if (root == null) {
                return root;
            }

            Node leftNode = root.left;
            Node rightNode = root.right;

            root.left = invertRecursive(rightNode);
            root.right = invertRecursive(leftNode);
            return root;
        }

        public void invertIterative(Node root) {
            if (root == null) {
                return;
            }
            Deque<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                // swap left child with right child 
                Node temp = node.left;
                node.left = node.right;
                node.right = temp;

                // push left and right children in queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
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
        /*        
        Tree:
                   10
                /     \
              12       15
            /   \     /
          25    30   36
        */

        // Print PreOrder for Original Tree
        System.out.print("Original Tree: ");
        tree.preOrder();
        
        // tree.invertRecursive(tree.root);
        tree.invertIterative(tree.root);

        // Print PreOrder for Original Tree
        System.out.print("Inverted Tree: ");
        tree.preOrder();
    }
}
