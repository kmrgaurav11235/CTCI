/*
https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
- Searching a key: To search a given key in Binary Search Tree, we first compare it with root, if 
    the key is present at root, we return root. If key is greater than root’s key, we recur for 
    right subtree of root node. Otherwise we recur for left subtree.
- Inserting a key: A new key is always inserted at leaf. We start searching a key from root till 
    we hit a leaf node. Once a leaf node is found, the new node is added as a child of the leaf 
    node.
- The worst case time complexity of search and insert operations is O(h) where h is height of Binary 
    Search Tree. In worst case, we may have to travel from root to the deepest leaf node. The height 
    of a skewed tree may become n and the time complexity of search and insert operation may become O(n).
- Inorder traversal of BST always produces sorted output.
*/
class BST_01_SearchAndInsert {
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class BinarySearchTree {
        private Node root;

        BinarySearchTree() {
            root = null;
        }

        BinarySearchTree(int data) {
            root = new Node(data);
        }

        private void inOrder(Node node, StringBuilder stringBuilder) {
            if (node == null) {
                return;
            }
            inOrder(node.left, stringBuilder);
            stringBuilder.append(node.data + " ");
            inOrder(node.right, stringBuilder);
        }

        public Node search(Node node, int key) {
            if (node == null || node.data == key) {
                return node;
            } else if (node.data > key) {
                return search(node.left, key);
            } else {
                return search(node.right, key);
            }
        }

        private Node insertUtil(Node node, int key) {
            if (node == null) {
                node = new Node(key);
            } else if (node.data == key) {
                System.out.println("Duplicate Key! Key already exists.");
            } else if (node.data > key) {
                node.left = insertUtil(node.left, key);
            } else {
                node.right = insertUtil(node.right, key);
            }
            return node;
        }

        public void insert(int key) {
            root = insertUtil(root, key);
        }

        @Override
        public String toString() {
            if (root == null) {
                return "[]";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InOrder Traversal: [ ");
            inOrder(root, stringBuilder);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(); 
  
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
       20   40  60   80 */
        tree.insert(50); 
        tree.insert(30); 
        tree.insert(20); 
        tree.insert(40); 
        tree.insert(70); 
        tree.insert(60); 
        tree.insert(80); 
  
        // print Inorder traversal of the BST 
        // Inorder traversal of BST always produces sorted output.
        System.out.println(tree);
    }
}