import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree_02_IterativeTreeTraversals {
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

        void iterativePreOrder() {
            Deque<Node> stack = new LinkedList<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node != null) {
                    System.out.print(node.data + " ");
                    stack.push(node.right);
                    stack.push(node.left);
                }
            }
            
        }

        void iterativeInOrder() {
            Deque<Node> stack = new LinkedList<>();
            Node node = root;

            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    // Keep putting the left nodes in stack to be visited later
                    stack.push(node);
                    node = node.left;
                }
                // At this point node is null

                // Gets the last node
                node = stack.pop();

                // Visit the node
                System.out.print(node.data + " ");

                // Visit its right sub-tree
                node = node.right;
            }
        }

        static final class Pair {
            Node node;
            int position;

            Pair(Node node, int position) {
                this.node = node;
                this.position = position; // 0 = left, 1 = right, 2 = node
            }
        }

        void iterativePostOrder() {
            Deque<Pair> stack = new LinkedList<>();

            if (root == null) {
                return;
            }

            stack.push(new Pair(root, 0));

            while (!stack.isEmpty()) {
                Pair pair = stack.pop();
                if (pair.position == 0) {
                    // Push the node and left node
                    // Push the node first as required order: is left, right, node.
                    stack.push(new Pair(pair.node, 1));
                    if (pair.node.left != null) {
                        stack.push(new Pair(pair.node.left, 0));
                    }
                } else if (pair.position == 1) {
                    // Push the node and right node
                    stack.push(new Pair(pair.node, 2));
                    if (pair.node.right != null) {
                        stack.push(new Pair(pair.node.right, 0));
                    }
                } else {
                    // Process the node
                    System.out.print(pair.node.data + " ");
                }
            }
        }


        void iterativePostOrderWithTwoStacks() {          
            Deque<Node> stack1 = new LinkedList<>();
            Deque<Node> stack2 = new LinkedList<>();

            stack1.push(root);
            // Similar to Pre-order

            while (!stack1.isEmpty()) {
                Node node = stack1.pop();

                if (node != null) {
                    // Push the node into s2
                    stack2.push(node);

                    // Push the children in s1 for processing.
                    // left goes in first in s1, then right. Right comes out of s1 first then left.
                    // Since right came out of s1 first, it goes into s2 first. left goes into s2 later.
                    // So, left comes out of s2 first.
                    stack1.push(node.left);
                    stack1.push(node.right);
                }
            }

            while (!stack2.isEmpty()) {
                Node node = stack2.pop();
                System.out.print(node.data + " ");
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
  
        System.out.println("Iterative Preorder traversal of binary tree is "); 
        tree.iterativePreOrder(); 
  
        System.out.println("\nIterative Inorder traversal of binary tree is "); 
        tree.iterativeInOrder(); 
  
        System.out.println("\nIterative Postorder traversal of binary tree is "); 
        tree.iterativePostOrder();
  
        System.out.println("\nIterative Postorder traversal of binary tree using two stacks is "); 
        tree.iterativePostOrderWithTwoStacks();
    }
}