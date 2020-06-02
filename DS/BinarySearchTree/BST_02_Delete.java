/*
https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
When we delete a node, three possibilities arise.
1) Node to be deleted is leaf: Simply remove from the tree.

              50                            50
           /     \         delete(20)      /   \
          30      70       --------->    30     70 
         /  \    /  \                     \    /  \ 
       20   40  60   80                   40  60   80

2) Node to be deleted has only one child: Copy the child to the node and delete the child

              50                            50
           /     \         delete(30)      /   \
          30      70       --------->    40     70 
            \    /  \                          /  \ 
            40  60   80                       60   80

3) Node to be deleted has two children: Find inorder successor of the node. Copy contents of 
    the inorder successor to the node and delete the inorder successor. Note that inorder 
    predecessor can also be used.


              50                            60
           /     \         delete(50)      /   \
          40      70       --------->    40    70 
                 /  \                            \ 
                60   80                           80

The important thing to note is, inorder successor is needed only when right child is not empty. 
    In this particular case, inorder successor can be obtained by finding the minimum value in 
    right child of the node.
*/
class BST_02_Delete {
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

        private int findInOrderSuccessor(Node node) {
            if (node == null) {
                System.out.println("Null Node. Cannot find InOrder successor.");
                return 0;
            }
            while (node.left != null) {
                node = node.left;
            }
            return node.data;
        }

        private Node deleteUtil(Node node, int key) {
            if (node == null) {
                System.out.println(key + " not found in BST.");
                return node;
            } else if (node.data > key) {
                node.left = deleteUtil(node.left, key);
                return node;
            } else if (node.data < key) {
                node.right = deleteUtil(node.right, key);
                return node;
            } else {
                // node.data == key
                if (node.left == null) {
                    // Leaf node or Node with right sub-tree only
                    return node.right;
                } else if (node.right == null) {
                    // Node with left sub-tree only
                    return node.left;
                } else {
                    // Node with both left and right sub-tree
                    int inOrderSuccessor = findInOrderSuccessor(node.right);
                    node.data = inOrderSuccessor;
                    deleteUtil(node.right, inOrderSuccessor);
                    /*
                    Instead of searching inOrder successor in one recursion call and then deleting 
                    it in another, we can also do both in the same while loop by keeping track of
                    its parent.
                    */
                    return node;
                }
            }
        }

        public void delete(int key) {
            root = deleteUtil(root, key);
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
        System.out.println("Inorder traversal of the given tree: " + tree); 

        System.out.println("\nDelete 90 which doesn't exists"); 
        tree.delete(90); 
        System.out.println("Inorder traversal of the the tree" + tree); 
  
        System.out.println("\nDelete 20"); 
        tree.delete(20); 
        System.out.println("Inorder traversal of the modified tree" + tree); 
  
        System.out.println("\nDelete 30"); 
        tree.delete(30); 
        System.out.println("Inorder traversal of the modified tree"  + tree); 
  
        System.out.println("\nDelete 50"); 
        tree.delete(50); 
        System.out.println("Inorder traversal of the modified tree" + tree); 
    }
}