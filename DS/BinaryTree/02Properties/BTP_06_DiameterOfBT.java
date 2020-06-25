/*
https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
- The diameter of a tree (sometimes called the width) is the number of nodes on the longest 
    path between two end nodes.

        Tree T
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30

- The diagram above shows a tree with diameter 6, the nodes that form the longest path are:
    30 - 4 - 10 - 26 - 3 - 3
- The diameter of a tree T is the largest of the following quantities:
    * the diameter of T’s left subtree
    * the diameter of T’s right subtree
    * the longest path between leaves that goes through the root of T (this can be computed 
        from the heights of the subtrees of T) 

*/
public class BTP_06_DiameterOfBT {
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

        private DiameterResult getDiameterUtil(Node node) {
            if (node == null) {
                return new DiameterResult();
            }
            DiameterResult leftResult = getDiameterUtil(node.left);
            DiameterResult rightResult = getDiameterUtil(node.right);

            int maxHeight = 1 + Math.max(leftResult.maxHeight, rightResult.maxHeight);

            int maxDiameterThroughNode = 1 + leftResult.maxHeight + rightResult.maxHeight;
            int maxDiameterAmongChildren = Math.max(leftResult.maxDiameter, rightResult.maxDiameter);
            int maxDiameter = Math.max(maxDiameterThroughNode, maxDiameterAmongChildren);

            return new DiameterResult(maxDiameter, maxHeight);
        }

        int getDiameter() {
            DiameterResult result = getDiameterUtil(root);
            return result.maxDiameter;
        }
    }

    static class DiameterResult {
        int maxDiameter;
        int maxHeight;

        DiameterResult(int maxDiameter, int maxHeight) {
            this.maxDiameter = maxDiameter;
            this.maxHeight = maxHeight;
        }

        public DiameterResult() {
            this(0, 0);
        }
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

        System.out.println("Diameter of Tree 1: " + tree1.getDiameter());
   
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
        
        System.out.println("Diameter of Tree 2: " + tree2.getDiameter());
    }
}