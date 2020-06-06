/*
https://www.geeksforgeeks.org/print-ancestors-of-a-given-node-in-binary-tree/
- Given a Binary Tree and a key, write a function that prints all the ancestors of the key 
    in the given binary tree.
- For example, if the given tree is following Binary Tree and key is 7, then your function 
    should print 4, 2 and 1.

              1
            /   \
          2      3
        /  \
      4     5
     /
    7

*/
public class BinaryTree_08_PrintAncestors {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class Tree {
        Node root;

        Tree() {
            root = null;
        }

        boolean printAncestors(Node root, int key) {
            if (root == null) {
                return false;
            } 
            if (printAncestors(root.left, key) || printAncestors(root.right, key)) {
                // Key was found below this node
                System.out.print(root.data + " ");
                return true;
            } else if (root.data == key) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree(); 
          
        /* Construct the following binary tree 
                  1 
                /   \ 
               2     3 
              /  \ 
             4    5 
            / 
           7 
        */
        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.left.left.left = new Node(7); 
        
        System.out.print("Printing Ancestors: ");
        tree.printAncestors(tree.root, 7); 
        System.out.println("");

    }
}