/*
Given a binary tree, write a function to get the maximum width of the given tree. Width of a tree is maximum of widths of all levels.Let us consider the below example tree.

         1
        /  \
       2    3
     /  \     \
    4    5     8 
              /  \
             6    7
For the above tree,
width of level 1 is 1,
width of level 2 is 2,
width of level 3 is 3
width of level 4 is 2.

So the maximum width of the tree is 3.
*/
public class BinaryTree_05_MaxWidthOfTree {
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
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

       /* 
        Constructed bunary tree is: 
              1 
            /  \ 
           2    3 
         /  \    \ 
        4   5     8  
                 /  \ 
                6   7 
         */
        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.right.right = new Node(8); 
        tree.root.right.right.left = new Node(6); 
        tree.root.right.right.right = new Node(7); 
   
        System.out.println("Maximum width is " + tree.getMaxWidth(tree.root)); 
    }
}