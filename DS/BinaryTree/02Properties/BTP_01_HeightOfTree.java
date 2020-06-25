/*
https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/

- Recursively calculate height of left and right subtrees of a node and assign height to the node as 
    max of the heights of two children plus 1. 
- Algorithm:
    1. If tree is empty then return 0
    2. Else
        a) Get the height of left subtree recursively  i.e., 
            call getHeight(tree->left-subtree)
        a) Get the height of right subtree recursively  i.e., 
            call getHeight(tree->right-subtree)
        c) Get the max of height of left and right 
            subtrees and add 1 to it for the current node.
            height = max(height of left subtree,  
                                height of right subtree) 
                                + 1
        d) Return height

*/
public class BTP_01_HeightOfTree {
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

        int getHeight(Node node) {
            if (node == null) {
                return 0;
            }
            int lHeight = getHeight(node.left);
            int rHeight = getHeight(node.right);

            if (lHeight >= rHeight) {
                return (lHeight + 1);
            } else {
                return (rHeight + 1);
            }
        }

        int getHeight() {
            return getHeight(root);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Height of binary tree is " + tree.getHeight() + "."); 
    }
}