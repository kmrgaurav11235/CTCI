import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/

- Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the 
    shortest path from the root node down to the nearest leaf node.
- For example, minimum height of below Binary Tree is 3 (1-2-4).
         1
        /  \
       2    3
     /        \
    4          5 
              /  \
             6    7
- The idea is to traverse the given Binary Tree. 
    1) For every node, check if it is a leaf node. If yes, then return 1. 
    2) If not leaf node then if the left subtree is NULL, then recur for the right subtree. And if the 
        right subtree is NULL, then recur for the left subtree. 
    3) If both left and right subtrees are not NULL, then take the minimum of two heights.

- The above method may end up with complete traversal of Binary Tree even when the topmost leaf is close 
    to root. A Better Solution is to do Level Order Traversal. While doing traversal, returns depth of the 
    first encountered leaf node.
*/
public class BinaryTree_14_MinimumDepth {
    static class Node {
        int data;
        Node left, right;

        Node (int data) {
            this.data = data;
            left = null;
            right = null;
        }

        Node () {
            this(0);
        }
    }

    static class BinaryTree {
        Node root;

        BinaryTree () {
            root = null;
        }

        BinaryTree (Node root) {
            this.root = root;
        }

        BinaryTree (int data) {
            this.root = new Node(data);
        }

        private int getMinimumDepth(Node node) {
            if (node == null) {
                // Corner case. Should never be hit unless the code is called on root == null 
                return 0;
            }
            if (node.left == null && node.right == null) {
                // Leaf Node
                return 1;
            } else if (node.left == null) {
                return 1 + getMinimumDepth(node.right);
            } else if (node.right == null) {
                return 1 + getMinimumDepth(node.left);
            } else {
                return 1 + Math.min(getMinimumDepth(node.left), getMinimumDepth(node.right));
            }
        }

        int getMinimumDepth() {
            return getMinimumDepth(root);
        }

        static class QueueItem {
            Node node;
            int depth;

            public QueueItem(Node node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public int getMinimumDepthQueue() {
            if (root == null) {
                return 0;
            }
            Deque<QueueItem> queue = new LinkedList<>();
            queue.offer(new QueueItem(root, 1));

            while (!queue.isEmpty()) {
                QueueItem queueItem = queue.poll();
                Node currentNode = queueItem.node;
                int depth = queueItem.depth;

                if (currentNode.left == null && currentNode.right == null) {
                    return depth;
                }
                if (currentNode.left != null) {
                    queue.offer(new QueueItem(currentNode.left, depth + 1));
                }
                if (currentNode.right != null) {
                    queue.offer(new QueueItem(currentNode.right, depth + 1));
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Minimum Height of binary tree is " + tree.getMinimumDepth() + "."); 
        System.out.println("Minimum Height of binary tree using queue is " + tree.getMinimumDepthQueue() + "."); 
    }
    
}