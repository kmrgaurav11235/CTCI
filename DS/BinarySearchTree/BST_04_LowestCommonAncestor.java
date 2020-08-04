/*
https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
- Given values of two values key1 and key2 in a Binary Search Tree, find the Lowest 
    Common Ancestor (LCA).
- - Definition of LCA:
    Let T be a rooted tree. The Lowest Common Ancestor of two nodes n1 and n2 is defined 
    as the lowest node in T that has both n1 and n2 as descendants (where we allow a node 
    to be a descendant of itself). 
- Algorithm:
    1) Create a recursive function that takes a node and the two values n1 and n2.
    2) If the value of the current node is less than both n1 and n2, then LCA lies in the 
        right subtree. Call the recursive function for thr right subtree.
    3) If the value of the current node is greater than both n1 and n2, then LCA lies in 
        the left subtree. Call the recursive function for thr left subtree.
    4) If both the above cases are false then return the current node as LCA.
*/
public class BST_04_LowestCommonAncestor {

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

        private boolean isPresent(Node node, int key) {
            if (node == null) {
                return false;
            } else if (key == node.data) {
                return true;
            } else if (key < node.data) {
                return isPresent(node.left, key);
            } else {
                return isPresent(node.right, key);
            }
        }

        private Node findLcaUtil(Node node, int key1, int key2) {
            if (node == null) {
                return null;
            } else if (key1 < node.data && key2 < node.data) {
                return findLcaUtil(node.left, key1, key2);
            } else if (key1 > node.data && key2 > node.data) {
                return findLcaUtil(node.right, key1, key2);
            } else {
                return node;
            }
        }

        public Node findLca(int key1, int key2) {
            Node lca = findLcaUtil(root, key1, key2);
            if (lca == null) {
                return lca;
            } else {
                if (isPresent(lca, key1) && isPresent(lca, key2)) {
                    return lca;
                } else {
                    return null;
                }
            }
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
   
        int n1 = 10, n2 = 14;
        Node lca = tree.findLca(n1, n2);
        if (lca != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + lca.data);
        } else {
            System.out.println("LCA of " + n1 + " and " + n2 + " is null.");
        }

        n1 = 14;
        n2 = 8;
        lca = tree.findLca(n1, n2);
        if (lca != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + lca.data);
        } else {
            System.out.println("LCA of " + n1 + " and " + n2 + " is null.");
        }

        n1 = 10;
        n2 = 22;
        lca = tree.findLca(n1, n2);
        if (lca != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + lca.data);
        } else {
            System.out.println("LCA of " + n1 + " and " + n2 + " is null.");
        }

        n1 = 8;
        n2 = 16;
        lca = tree.findLca(n1, n2);
        if (lca != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + lca.data);
        } else {
            System.out.println("LCA of " + n1 + " and " + n2 + " is null.");
        }
    }
}