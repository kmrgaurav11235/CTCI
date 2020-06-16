/*
https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
- Given a binary tree and two values say n1 and n2, write a program to find the Lowest 
    Common Ancestor.
- Definition of LCA:
    Let T be a rooted tree. The Lowest Common Ancestor of two nodes n1 and n2 is defined 
    as the lowest node in T that has both n1 and n2 as descendants (where we allow a node 
    to be a descendant of itself).
- The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest 
    from the root. 
*/
public class BinaryTree_12_LowestCommonAncestor {
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

        private LcaSearchResult findLCAUtil(int key1, int key2, Node node) {
            LcaSearchResult lcaSearchResult = new LcaSearchResult();
            if (node == null) {
                return lcaSearchResult;
            }
            if (node.data == key1) {
                lcaSearchResult.isKey1Found = true;
            }
            if (node.data == key2) {
                lcaSearchResult.isKey2Found = true;
            }
            LcaSearchResult leftLcaSearchResult = findLCAUtil(key1, key2, node.left);
            LcaSearchResult rightLcaSearchResult = findLCAUtil(key1, key2, node.right);

            if (leftLcaSearchResult.lca != null) {
                return leftLcaSearchResult;
            } else if (rightLcaSearchResult.lca != null) {
                return rightLcaSearchResult;
            } else {
                lcaSearchResult.isKey1Found = lcaSearchResult.isKey1Found || leftLcaSearchResult.isKey1Found
                        || rightLcaSearchResult.isKey1Found;
                lcaSearchResult.isKey2Found = lcaSearchResult.isKey2Found || leftLcaSearchResult.isKey2Found
                        || rightLcaSearchResult.isKey2Found;
                if (lcaSearchResult.isKey1Found && lcaSearchResult.isKey2Found) {
                    lcaSearchResult.lca = node;
                }
                return lcaSearchResult;
            }
        }

        public Node findLCA(int key1, int key2) {
            LcaSearchResult lcaSearchResult = findLCAUtil(key1, key2, root);
            return lcaSearchResult.lca;
        }
    }

    static class LcaSearchResult {
        boolean isKey1Found;
        boolean isKey2Found;
        Node lca;

        LcaSearchResult(boolean isKey1Found, boolean isKey2Found, Node lca) {
            this.isKey1Found = isKey1Found;
            this.isKey2Found = isKey2Found;
            this.lca = lca;
        }

        public LcaSearchResult() {
            this(false, false, null);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        Node lca = tree.findLCA(4, 5);
        if (lca != null) {
            System.out.println("LCA(4, 5) = " + lca.data);
        } else {
            System.out.println("LCA(4, 5) = Keys are not present");
        }

        lca = tree.findLCA(4, 10);
        if (lca != null) {
            System.out.println("LCA(4, 10) = " + lca.data);
        } else {
            System.out.println("LCA(4, 10) = Keys are not present");
        }
    }
}