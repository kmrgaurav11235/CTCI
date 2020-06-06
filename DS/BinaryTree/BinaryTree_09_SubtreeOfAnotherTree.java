/*
https://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
- Given two binary trees, check if the first tree is subtree of the second one. 
- A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T. 
- The subtree corresponding to the root node is the entire tree; the subtree corresponding to 
    any other node is called a proper subtree.
- For example, in the following case, tree S is a subtree of tree T.

        Tree S
          10  
        /    \ 
      4       6
       \
        30


        Tree T
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30

*/
public class BinaryTree_09_SubtreeOfAnotherTree {
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
    }
    
    private static boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        return (root1.data == root2.data) && isIdentical(root1.left, root2.left)
            && isIdentical(root1.right, root2.right);
    }

    private static boolean isSubtree(Node root1, Node root2) {
        // Base Cases
        if (root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        }
        if (isIdentical(root1, root2)) {
            return true;
        }
        return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }

    public static void main(String[] args) {
           
        /* 
        TREE 1 
        Construct the following tree 
              26 
             /   \ 
            10     3 
           /    \     \ 
          4      6      3 
           \ 
            30  */
        
        Tree tree1 = new Tree(); 
        tree1.root = new Node(26); 
        tree1.root.right = new Node(3); 
        tree1.root.right.right = new Node(3); 
        tree1.root.left = new Node(10); 
        tree1.root.left.left = new Node(4); 
        tree1.root.left.left.right = new Node(30); 
        tree1.root.left.right = new Node(6); 
   
        /* 
        TREE 2 
        Construct the following tree 
           10 
         /    \ 
         4      6 
          \ 
          30  */
        
        Tree tree2 = new Tree(); 
        tree2.root = new Node(10); 
        tree2.root.right = new Node(6); 
        tree2.root.left = new Node(4); 
        tree2.root.left.right = new Node(30); 
   
        if (isSubtree(tree1.root, tree2.root)) {
            System.out.println("Tree 2 is subtree of Tree 1 "); 
        } else {
            System.out.println("Tree 2 is not a subtree of Tree 1"); 
        }
    }
}