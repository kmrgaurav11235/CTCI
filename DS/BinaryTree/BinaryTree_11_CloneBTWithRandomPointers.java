/*
https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/

- Given a Binary Tree where every node has a random pointer that points to any random 
    node of the binary tree and can even point to null, clone the given binary tree.
- Algorithm:
    1) Create new clone-nodes in the tree and insert them in the original tree at 
        the left pointer edge:
              1
            /   \
          2      3
        Clone Nodes Inserted:
              1
            /   \
          1(C)   3
        /       /
       2       3(C)     
     /
    2(C)
        
        i.e. if current node is A and it’s left child is B ( A — >> B ), then new cloned node 
        with key A wil be created (say cA) and it will be put as A — >> cA — >> B (B can be a 
        null or a non-null left child). 
        Right child pointer will be set correctly i.e. if for current node A, right child is C in 
        original tree (A — >> C) then corresponding cloned nodes cA and cC will like cA — >> cC.

    2. Set random pointer in cloned tree as per original tree:
        i.e. if node A’s random pointer points to node B, then in cloned tree, cA will point to cB 
        (cA and cB are new node in cloned tree corresponding to node A and B in original tree).
    3. Restore left pointers correctly in both original and cloned tree.
*/
public class BinaryTree_11_CloneBTWithRandomPointers {
    static class Node {
        int data;
        Node left, right, random;

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

        private void preOrder(Node root) {
            if (root != null) {
                System.out.print("{ data: " + root.data + ", random: "
                        + (root.random == null ? "null" : root.random.data) + " } ");
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        void preOrder() {
            preOrder(root);
        }

        private void inOrder(Node root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print("{ data: " + root.data + ", random: "
                        + (root.random == null ? "null" : root.random.data) + " } ");
                inOrder(root.right);
            }
        }

        void inOrder() {
            inOrder(root);
        }

        // This method creates new cloned nodes and puts them in between current node 
        // and it's left child.
        private void insertCloneNodes(Node node) {
            if (node == null) {
                return;
            }
            Node temp = new Node(node.data);
            temp.left = node.left;
            node.left = temp;
            insertCloneNodes(node.left.left);
            insertCloneNodes(node.right);
        }
        
        // This method sets random pointer in cloned tree as per original tree.
        private void setRandomPointerForClonedNodes(Node node) {
            if (node == null) {
                return;
            }
            node.left.random = (node.random == null) ? null : node.random.left;
            setRandomPointerForClonedNodes(node.left.left);
            setRandomPointerForClonedNodes(node.right);
        }

        // This function will restore left pointers correctly in both original and 
        // cloned tree 
        private Node seperateCloneFromOriginal(Node node) {
            if (node == null) {
                return null;
            }
            Node clone = node.left;
            node.left = node.left.left;

            clone.left = seperateCloneFromOriginal(node.left);
            clone.right = seperateCloneFromOriginal(node.right);

            return clone;
        }

        public BinaryTree cloneTree() {
            BinaryTree clonedTree = new BinaryTree();
            if (root == null) {
                return clonedTree;
            }
            insertCloneNodes(root);
            setRandomPointerForClonedNodes(root);
            clonedTree.root = seperateCloneFromOriginal(root);
            return clonedTree;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        // Random pointers
        tree.root.random = tree.root.left.right;
        tree.root.left.random = null;
        tree.root.right.random = tree.root.left;
        tree.root.left.left.random = tree.root.left.left;
        tree.root.left.right.random = tree.root;

        System.out.println("Preorder traversal of original binary tree is ");
        tree.preOrder();
        System.out.println("\nInorder traversal of original binary tree is ");
        tree.inOrder();

        BinaryTree clonedTree = tree.cloneTree();

        System.out.println("\n\nPreorder traversal of original binary tree after cloning is ");
        tree.preOrder();
        System.out.println("\nInorder traversal of original binary tree after cloning is ");
        tree.inOrder();

        System.out.println("\n\nPreorder traversal of cloned binary tree after cloning is ");
        clonedTree.preOrder();
        System.out.println("\nInorder traversal of cloned binary tree after cloning is ");
        clonedTree.inOrder();
    }
}