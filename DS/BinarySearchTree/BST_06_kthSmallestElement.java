/*
https://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/

- Find k-th smallest element in BST (Order Statistics in BST): Given the root of a binary search tree and 
    K as input, find K-th smallest element in BST.
- For example, in the following BST, if k = 3, then the output should be 10, and if k = 5, then the output 
    should be 14.
    Tree:
                20
               /  \
              8    22
            /  \ 
           4     12
                /  \ 
              10    14
- Algorithm: The idea is to use Inorder Traversal.
    * The Inorder Traversal of a BST traverses the nodes in increasing order. So, the idea is to traverse the 
        tree in Inorder. 
    * While traversing, keep track of the count of the nodes visited. If the count becomes k, return the node. 
- Alternative: Augmented  Tree Data Structure by maintaining the rank of each node. We can keep track of 
    elements in the left subtree of every node while building the tree. Since we need the K-th smallest element, 
    we can maintain the number of elements of the left subtree in every node.
*/
class BST_06_kthSmallestElement {

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
        int currentK = 0;

        private Node getKthSmallestUtil(Node node, int k) {
            if (node == null) {
                return null;
            }
            // left
            Node leftNode = getKthSmallestUtil(node.left, k);
            if (leftNode != null) {
                return leftNode;
            }

            // node
            currentK++;
            if (currentK == k) {
                return node;
            }

            // right
            return getKthSmallestUtil(node.right, k);
        }

        public Node getKthSmallest(int k) {
            currentK = 0;
            return getKthSmallestUtil(root, k);
        }
    }
    public static void main(String[] args) {
        
        BinaryTree tree = new BinaryTree(); 
        tree.root = new Node(20); 
        tree.root.left = new Node(8); 
        tree.root.right = new Node(22); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(12); 
        tree.root.left.right.left = new Node(10); 
        tree.root.left.right.right = new Node(14);
        /*
        Tree:
                20
               /  \
              8    22
            /  \ 
           4     12
                /  \ 
              10    14
        */ 
   
        int k = 3;
        Node kthSmallest = tree.getKthSmallest(k);
        if (kthSmallest != null) {
            System.out.println("k = " + k + ". kthSmallest of tree is " + kthSmallest.data);
        } else {
            System.out.println("k = " + k + ". kthSmallest of tree is null.");
        }

        k = 5;
        kthSmallest = tree.getKthSmallest(k);
        if (kthSmallest != null) {
            System.out.println("k = " + k + ". kthSmallest of tree is " + kthSmallest.data);
        } else {
            System.out.println("k = " + k + ". kthSmallest of tree is null.");
        }

        k = 8;
        kthSmallest = tree.getKthSmallest(k);
        if (kthSmallest != null) {
            System.out.println("k = " + k + ". kthSmallest of tree is " + kthSmallest.data);
        } else {
            System.out.println("k = " + k + ". kthSmallest of tree is null.");
        }
    }
}