/*
https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

- A binary search tree (BST) has the following properties:
    * The left subtree of a node contains only nodes with keys less than the node’s key.
    * The right subtree of a node contains only nodes with keys greater than the node’s key.
    * Both the left and right subtrees must also be binary search trees.

- The idea is to write a method that traverses down the tree keeping track of the narrowing 
    min and max allowed values as it goes, looking at each node only once.
*/
public class BST_03_IsBST {

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

        private boolean isBSTUtil(Node node, Node lNode, Node rNode) {
            if (node == null) {
                return true;
            } else if ((lNode != null && lNode.data >= node.data) || (rNode != null && node.data >= rNode.data)) {
                return false;
            } else {
                return isBSTUtil(node.left, lNode, node) && isBSTUtil(node.right, node, rNode);
            }
        }

        public boolean isBST() {
            return isBSTUtil(root, null, null);
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 
        tree.root = new Node(3);  
        tree.root.left = new Node(2);  
        tree.root.right = new Node(5);  
        tree.root.left.left = new Node(1);  
        tree.root.left.right = new Node(4);  
    
        if (tree.isBST()) {
            System.out.println("Is BST");
        } else {
            System.out.println("Not a BST");  
        }
    }
}