/*
https://www.geeksforgeeks.org/inorder-succesor-node-binary-tree/

- In Binary Tree, Inorder successor of a node is the next node in Inorder traversal of the Binary Tree. 
- Inorder Successor is null for the last node in Inorder traversal.
- Method 1 (Uses Inorder traversal):
    * We can use a class variable as a flag to check if the key node was the previously travsersed node as we do 
        an inorder traversal.
- Method 2 (Uses Parent Pointer):
    * In this method, we assume that every node has a parent pointer. The Algorithm is divided into two cases on 
        the basis of the right subtree of the input node being empty or not.
        1) If right subtree of node is not null, then successor lies in right subtree. Do the following:
            Go to right subtree and return the node with minimum key value in the right subtree.
        2) If right sbtree of node is null, then successor is one of the ancestors. Do the following:
            Travel up using the parent pointer until you see a node which is left child of its parent. The parent 
            of such a node is the successor.
- Method 3:
    We need to take care of 3 cases for any node to find its inorder successor as described below:
    1) Right child of node is not null: If the right child of the node is not null then the inorder successor of 
        this node will be the leftmost node in it's right subtree.
    2) Right Child of the node is null: If the right child of node is null, then we use the following algorithm:
        a) Suppose the given node is x. Start traversing the tree from root node to find x recursively.
        b) If root == x, stop recursion otherwise find x recursively for left and right subtrees.
        c) Now after finding the node x, recur­sion will back­track to the root. Every recursive call will return the 
            node itself to the calling function, we will store this in a temporary node say temp. Now, when it 
            back­tracked to its par­ent which will be root now, check whether root.left = temp, if not, keep going up.
    3) If node is the rightmost node: If the node is the rightmost node in the given tree, there will be no inorder 
        successor of this node. i.e. Inorder Successor of the rightmost node in a tree is NULL.

*/
public class BTP_07_InorderSuccessor {
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

        BinaryTree() {
            root = null;
        }

        private Node inOrderSuccessorUtil(Node node, Node successorAmongParents, int key) {
            if (node == null) {
                return null;
            } else if (key == node.data) {
                // Find the InOrder Successor of this node 
                if (node.right == null) {
                    return successorAmongParents;
                } else {
                    Node p = node.right;
                    while (p.left != null) {
                        p = p.left;
                    }
                    return p;
                }
            } else {
                // Keep searching for the node and find the InOrder Successor recursively
                Node successorLeft = inOrderSuccessorUtil(node.left, node, key);
                if (successorLeft != null) {
                    return successorLeft;
                }
                Node successorRight = inOrderSuccessorUtil(node.right, successorAmongParents, key);
                return successorRight;
                
            }
        }

        public Node inOrderSuccessor(int key) {
            return inOrderSuccessorUtil(root, null, key);
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);  
        root.left = new Node(2);  
        root.right = new Node(3);  
        root.left.left = new Node(4);  
        root.left.right = new Node(5);  
        root.right.right = new Node(6);

        BinaryTree tree = new BinaryTree(); 
        tree.root = root;
        /*
        Tree:
                1
               /  \
              2    3
            /  \    \
           4    5    6
        */ 
    
        // Right subtree not null
        int nodeData = 3;
        Node successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }
    
        // Right subtree null
        nodeData = 4;
        successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }
    
        // Last node in inOrder traversal
        nodeData = 6;
        successor = tree.inOrderSuccessor(nodeData);
        if (successor != null) {
            System.out.println("Inorder successor of " + nodeData + " is " + successor.data);
        } else {
            System.out.println("Inorder successor of " + nodeData + " does not exists");
        }
    }
}