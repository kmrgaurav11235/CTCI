/*
https://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/

- Given a binary tree, print it vertically. The following example illustrates vertical order traversal.

           1
        /    \ 
       2      3
      / \   /   \
     4   5  6   7
               /  \ 
              8   9 
               

The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9

- Algorithm:
- We need to check the Horizontal Distances from the root for all nodes. If two nodes have the same 
    Horizontal Distance (HD), then they are on the same vertical line. 
- The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is 
    considered as +1 horizontal distance and a left edge is considered as -1 horizontal distance. 
- For example, in the above tree, HD for Node 4 is at -2, HD for Node 2 is -1, HD for 5 and 6 is 0 
    and HD for node 7 is +2.
- We can do preorder traversal of the given Binary Tree. While traversing the tree, we can recursively 
    calculate HDs. 
- We initially pass the horizontal distance as 0 for root. For left subtree, we pass the Horizontal 
    Distance as Horizontal distance of root minus 1. For right subtree, we pass the Horizontal Distance 
    as Horizontal Distance of root plus 1. 
- For every HD value, we maintain a list of nodes in a hash map. Whenever we see a node in traversal, 
    we go to the hash map entry and add the node to the hash map using HD as a key in a map.
*/
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

class BTT_05_VerticalOrderTraversal {
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

        void verticalOrderTraversalUtil(Node node, int horizontalDistance, SortedMap<Integer, ArrayList<Node>> horizontalDistanceToNodeListMap) {
            if (horizontalDistanceToNodeListMap.containsKey(horizontalDistance)) {
                ArrayList<Node> nodeListMap = horizontalDistanceToNodeListMap.get(horizontalDistance);
                nodeListMap.add(node);
            } else {
                ArrayList<Node> nodeListMap = new ArrayList<>();
                nodeListMap.add(node);
                horizontalDistanceToNodeListMap.put(horizontalDistance, nodeListMap);
            }

            // Visit the left Subtree
            if (node.left != null) {
                verticalOrderTraversalUtil(node.left, horizontalDistance - 1, horizontalDistanceToNodeListMap);
            }

            // Visit the right Subtree
            if (node.right != null) {
                verticalOrderTraversalUtil(node.right, horizontalDistance + 1, horizontalDistanceToNodeListMap);
            }
        }

        String verticalOrderTraversal() {
            /*
            We will use use a Map to store Nodes with same horizontal distance.
            Since the order in which nodes are traversed in important:
                i) We will use a TreeMap instead of a HashMap.
                ii) We will store keys as horizontal distance and values as 
                    ArrayList of Nodes. 
            Comment: Probably better to use HashMap and a class with min-max values of horizontal distance because:
            Insertion in HashMap = O(1)
            Insertion in TreeMap = O(log n)
            */
            
            SortedMap<Integer, ArrayList<Node>> horizontalDistanceToNodeListMap = new TreeMap<>(); // To store the hd -> Nodes mapping

            if (root == null) {
                return "";
            }

            verticalOrderTraversalUtil(root, 0, horizontalDistanceToNodeListMap);

            StringBuilder verticalOrderTraversal = new StringBuilder(); // To store the traversal

            for (int horizontalDistance : horizontalDistanceToNodeListMap.keySet()) {
                for (Node node : horizontalDistanceToNodeListMap.get(horizontalDistance)) {
                    verticalOrderTraversal.append(node.data + " ");
                }
                verticalOrderTraversal.append("\n");
            }
            return verticalOrderTraversal.toString();
        }
    }
    /*
    Note that the above solution may print nodes in same vertical order as they appear in tree. 
    For example, the above program prints 12 before 9.

            1
          /     \ 
         2        3
        /  \   /    \
       4    5  6      7
                 \   /   \
                  8  10     9 
                        \
                         11
                            \
                             12      
    */

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

       /* 
        Constructed binary tree is: 
               1
            /    \ 
           2      3
          / \   /   \
         4   5  6   7
                   /  \ 
                  8   9 
         */
        
        Node root = new Node(1); 
        root.left = new Node(2); 
        root.right = new Node(3); 
        root.left.left = new Node(4); 
        root.left.right = new Node(5); 
        root.right.left = new Node(6); 
        root.right.right = new Node(7); 
        root.right.left.right = new Node(8); 
        root.right.right.right = new Node(9); 
        tree.root = root; 

        System.out.println("Vertical Order Traversal of Tree : \n" + tree.verticalOrderTraversal());
    }
}