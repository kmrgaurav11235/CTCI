/*
https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

- Trees can be traversed in different ways:
         1
        /  \
       2    3
     /  \ 
    4    5 

    1. Depth First Traversals:
        a) Preorder (Root, Left, Right) : 1 2 4 5 3
        b) Inorder (Left, Root, Right) : 4 2 5 1 3
        c) Postorder (Left, Right, Root) : 4 5 2 3 1
    2. Breadth First or Level Order Traversal : 1 2 3 4 5

*/
public class BinaryTree_01_TreeTraversals {
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

        void preOrder(Node root) {
            if (root != null) {
                System.out.print(root.data + " ");
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        void preOrder() {
            preOrder(root);
        }

        void inOrder(Node root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.data + " ");
                inOrder(root.right);
            }            
        }

        void inOrder() {
            inOrder(root);            
        }

        void postOrder(Node root) { 
            if (root != null) {
                postOrder(root.left);
                postOrder(root.right);
                System.out.print(root.data + " ");
            }           
        }

        void postOrder() {
            postOrder(root);            
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Preorder traversal of binary tree is "); 
        tree.preOrder(); 
  
        System.out.println("\nInorder traversal of binary tree is "); 
        tree.inOrder(); 
  
        System.out.println("\nPostorder traversal of binary tree is "); 
        tree.postOrder();
    }
}