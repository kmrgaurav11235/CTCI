/*
https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/

- In Binary Tree, Inorder successor of a node is the next node in Inorder traversal of the Binary Tree. 
- Inorder Successor is null for the last node in Inorder traversal.
- In Binary Search Tree, Inorder Successor of an input node can also be defined as the node with the smallest key 
    greater than the key of the input node. So, it is sometimes important to find next node in sorted order.
- Method 1 (Uses Inorder traversal):
    * We can use a class variable as a flag to check if the key node was the previously travsersed node as we do 
        an inorder traversal.
- Method 2 (Uses Parent Pointer):
    * In this method, we assume that every node has a parent pointer. The Algorithm is divided into two cases on 
        the basis of the right subtree of the input node being empty or not.
        1) If right subtree of node is not NULL, then successor lies in right subtree. Do the following:
            Go to right subtree and return the node with minimum key value in the right subtree.
        2) If right sbtree of node is NULL, then successor is one of the ancestors. Do the following:
            Travel up using the parent pointer until you see a node which is left child of its parent. The parent 
            of such a node is the successor.
- Method 3 (Search from root):
    1) If right subtree of node is not null, then successor lies in right subtree. Do the following:
        Go to right subtree and return the node with minimum key value in the right subtree.
    2) If right subtree of node is null, then start from the root and do the following:
        Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise, go to left 
        side. Keep track of the last node where child = parent.left.

*/
public class BST_05_InorderSuccessor {
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

        public Node inOrderSuccessor(int nodeData) {
            Node p = root;
            Node successorAmongParents = null;
            while (p != null || p.data != nodeData) {
                if (nodeData < p.data) {
                    successorAmongParents = p;
                    p = p.left;
                } else if (nodeData > p.data) {
                    p = p.right;
                } else {
                    // nodeData == p.data
                    if (p.right == null) {
                        return successorAmongParents;
                    } else {
                        Node successor = p.right;
                        while (successor.left != null) {
                            successor = successor.left;
                        }
                        return successor;
                    }
                }
            }
            return null;
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

        // Right tree not null
        int nodeData = 8;
        Node successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }

        // Right tree null
        nodeData = 14;
        successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }

        // Last Element
        nodeData = 22;
        successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }
    }
}