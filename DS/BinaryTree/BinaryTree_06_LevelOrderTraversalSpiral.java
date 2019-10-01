import java.util.Deque;
import java.util.LinkedList;

class BinaryTree_06_LevelOrderTraversalSpiral {
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

        String levelOrderTraversalSpiral() {
            /*
            We will use two stacks. One prints nodes left to right (inserts right first, then left)
            and other prints right to left (inserts left first, then right)
            */
            
            Deque<Node> rightToLeft = new LinkedList<>();
            Deque<Node> leftToRight = new LinkedList<>();

            StringBuilder traversal = new StringBuilder();

            if (root == null) {
                return "";
            }
            rightToLeft.push(root);

            while (!rightToLeft.isEmpty() || !leftToRight.isEmpty()) {
                while (!rightToLeft.isEmpty()) {
                    Node node = rightToLeft.pop();
                    traversal.append(node.data + " ");

                    if (node.right != null) {
                        leftToRight.push(node.right);
                    }
                    if (node.left != null) {
                        leftToRight.push(node.left);
                    }
                }

                while (!leftToRight.isEmpty()) {
                    Node node = leftToRight.pop();
                    traversal.append(node.data + " ");

                    if (node.left != null) {
                        rightToLeft.push(node.left);
                    }
                    if (node.right != null) {
                        rightToLeft.push(node.right);
                    }
                }
            }

            return traversal.toString();
        }
    }
    /*
    Alternative: Spiral Level order traversal of a tree using single queue
    The idea behind this approach is first we have to take a queue, a direction flag and a separation flag which is NULL.

    i) Insert the root element into the queue and again insert NULL into the queue.
    ii) For every element in the queue insert its child nodes.
    iii) If a NULL is encountered then check the direction to traverse the particular level is left to right or right to left. 
    If it’s an even level then traverse from left to right otherwise traverse the tree in right to level order i.e., from the 
    front to the previous front i.e., from the current NULL to to the last NULL that has been visited. 
    This continues till the last level then there the loop breaks and we print what is left (that has not printed) by 
    checking the direction to print.

    https://www.geeksforgeeks.org/zig-zag-level-order-traversal-of-a-tree-using-single-queue/
    */

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

        System.out.println("Level Order Traversal of Tree in Spiral form : " + tree.levelOrderTraversalSpiral());
    }
}