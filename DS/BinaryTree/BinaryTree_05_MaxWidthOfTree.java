/*
Given a binary tree, write a function to get the maximum width of the given tree. Width of a tree is maximum of widths of all levels.Let us consider the below example tree.

         1
        /  \
       2    3
     /  \     \
    4    5     8 
              /  \
             6    7
For the above tree,
width of level 1 is 1,
width of level 2 is 2,
width of level 3 is 3
width of level 4 is 2.

So the maximum width of the tree is 3.
*/
import java.util.HashMap;
import java.util.Map;

public class BinaryTree_05_MaxWidthOfTree {
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

        void getMaxWidthUtil(Node node, int level, Map <Integer, Integer> levelToWidthMap) {
            if (node == null) {
                return;
            }
            if (levelToWidthMap.containsKey(level)) {
                levelToWidthMap.put(level, levelToWidthMap.get(level) + 1);
            } else {
                levelToWidthMap.put(level, 1);
            }
            getMaxWidthUtil(node.left, level + 1, levelToWidthMap);
            getMaxWidthUtil(node.right, level + 1, levelToWidthMap);
        }

        int getMaxWidth() {
            if (root == null) {
                return 0;
            }
            Map <Integer, Integer> levelToWidthMap = new HashMap<>();
            getMaxWidthUtil(root, 0, levelToWidthMap);
            
            int maxWidth = 0;
            for (int value : levelToWidthMap.values()) {
                if (value > maxWidth) {
                    maxWidth = value;
                }                
            }
            return maxWidth;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

       /* 
        Constructed binary tree is: 
              1 
            /  \ 
           2    3 
         /  \    \ 
        4   5     8  
                 /  \ 
                6   7 
         */
        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.right.right = new Node(8); 
        tree.root.right.right.left = new Node(6); 
        tree.root.right.right.right = new Node(7); 
   
        System.out.println("Maximum width is " + tree.getMaxWidth()); 
    }
}