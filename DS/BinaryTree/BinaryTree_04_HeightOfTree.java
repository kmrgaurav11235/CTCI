public class BinaryTree_04_HeightOfTree {
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

        int getHeight(Node node) {
            if (node == null) {
                return 0;
            }
            int lHeight = getHeight(node.left);
            int rHeight = getHeight(node.right);

            if (lHeight >= rHeight) {
                return (lHeight + 1);
            } else {
                return (rHeight + 1);
            }
        }

        int getHeight() {
            return getHeight(root);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Height of binary tree is " + tree.getHeight() + "."); 
    }
}