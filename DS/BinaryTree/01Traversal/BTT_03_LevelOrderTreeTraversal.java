/*
https://www.geeksforgeeks.org/level-order-tree-traversal/

- For each node, first the node is visited and then it’s child nodes are put in a FIFO queue.

printLevelorder(tree)
    1) Create an empty queue q
    2) tempNode = root
    3) Loop while tempNode is not NULL
        a) print tempNode->data.
        b) Enqueue tempNode's children (first left then right children) to q.
        c) Dequeue a node from q and assign it’s value to tempNode.
*/
import java.util.Deque;
import java.util.LinkedList;

public class BTT_03_LevelOrderTreeTraversal {
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

        void levelOrder() {          
            Deque<Node> queue = new LinkedList<>();

            if (root == null) {
                return;
            }

            queue.offer(root);

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.print(node.data + " ");
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
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Level Order traversal of binary tree is "); 
        tree.levelOrder(); 
    }
}