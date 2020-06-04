/*
https://www.geeksforgeeks.org/iterative-preorder-traversal/
https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
https://www.geeksforgeeks.org/iterative-postorder-traversal/
https://www.geeksforgeeks.org/iterative-postorder-traversal-set-3/

- Preorder: To convert an inherently recursive procedures to iterative, we need an explicit stack. 
    Following is a simple stack based iterative process to print Preorder traversal:
        1) Create an empty stack nodeStack and push root node to stack.
        2) Do following while nodeStack is not empty.
            a) Pop an item from stack and print it.
            b) Push right child of popped item to stack
            c) Push left child of popped item to stack

    Right child is pushed before left child to make sure that left subtree is processed first.

- Inorder: 
    1) Create an empty stack S.
    2) Initialize current node as root
    3) Push the current node to S and set current = current->left until current is NULL
    4) If current is NULL and stack is not empty then 
        a) Pop the top item from stack.
        b) Print the popped item, set current = popped_item->right 
        c) Go to step 3.
    5) If current is NULL and stack is empty then we are done.

- Postorder Traversal Using Two Stacks:
    Postorder traversal can easily be done using two stacks. The idea is to push reverse postorder 
    traversal to a stack. Once we have the reversed postorder traversal in a stack, we can just pop 
    all items one by one from the stack and print them. This order of printing will be in postorder 
    because of the LIFO property of stacks. 
- Now the question is, how to get reversed postorder elements in a stack – the second stack is used 
    for this purpose. If take a closer look at reverse-postorder sequence, we can observe that it is 
    very similar to the preorder traversal. The only difference is that the right child is visited before 
    left child, and therefore the sequence is “root right left” instead of “root left right”. So, we 
    can do something like iterative preorder traversal with the following differences:
    a) Instead of printing an item, we push it to a stack.
    b) We push the left subtree before the right subtree.
- Complete algorithm:
    1) Push root to first stack.
    2) Loop while first stack is not empty
        a) Pop a node from first stack and push it to second stack
        b) Push left and right children of the popped node to first stack
    3) Print contents of second stack

- Postorder traversal on a Binary Tree iteratively using a single stack:

- Consider the Below Terminologies:
    0 -  Left element
    1 -  Right element
    2 -  Node element

- Detailed algorithm:
    Take a Stack and perform the below operations:
    1) Insert a pair of the root node as (node, 0).
    2) Pop the top element to get the pair 
    (Let a = node and b be the variable)
        a) If b is equal to 0:
            Push another pair as (node, 1) and 
            Push the left child as (node->left, 0)
            Repeat Step 2
        b) Else If b is equal to 1:
            Push another pair as (node, 2) and 
            Push right child of node as (node->right, 0)
            Repeat Step 2
        c) Else If b is equal to 2:
            Print(node->data)
    3) Repeat the above steps while stack is not empty


*/
import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree_02_IterativeTreeTraversals {
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

        void iterativePreOrder() {
            Deque<Node> stack = new LinkedList<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node != null) {
                    System.out.print(node.data + " ");
                    stack.push(node.right);
                    stack.push(node.left);
                }
            }
            
        }

        void iterativeInOrder() {
            Deque<Node> stack = new LinkedList<>();
            Node node = root;

            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    // Keep putting the left nodes in stack to be visited later
                    stack.push(node);
                    node = node.left;
                }
                // At this point node is null

                // Gets the last node
                node = stack.pop();

                // Visit the node
                System.out.print(node.data + " ");

                // Visit its right sub-tree
                node = node.right;
            }
        }

        static final class Pair {
            Node node;
            int position;

            Pair(Node node, int position) {
                this.node = node;
                this.position = position; // 0 = left, 1 = right, 2 = node
            }
        }

        void iterativePostOrder() {
            Deque<Pair> stack = new LinkedList<>();

            if (root == null) {
                return;
            }

            stack.push(new Pair(root, 0));

            while (!stack.isEmpty()) {
                Pair pair = stack.pop();
                if (pair.position == 0) {
                    // Push the node and left node
                    // Push the node first as required order: is left, right, node.
                    stack.push(new Pair(pair.node, 1));
                    if (pair.node.left != null) {
                        stack.push(new Pair(pair.node.left, 0));
                    }
                } else if (pair.position == 1) {
                    // Push the node and right node
                    stack.push(new Pair(pair.node, 2));
                    if (pair.node.right != null) {
                        stack.push(new Pair(pair.node.right, 0));
                    }
                } else {
                    // Process the node
                    System.out.print(pair.node.data + " ");
                }
            }
        }


        void iterativePostOrderWithTwoStacks() {          
            Deque<Node> stack1 = new LinkedList<>();
            Deque<Node> stack2 = new LinkedList<>();

            stack1.push(root);
            // Similar to Pre-order

            while (!stack1.isEmpty()) {
                Node node = stack1.pop();

                if (node != null) {
                    // Push the node into s2
                    stack2.push(node);

                    // Push the children in s1 for processing.
                    // left goes in first in s1, then right. Right comes out of s1 first then left.
                    // Since right came out of s1 first, it goes into s2 first. left goes into s2 later.
                    // So, left comes out of s2 first.
                    stack1.push(node.left);
                    stack1.push(node.right);
                }
            }

            while (!stack2.isEmpty()) {
                Node node = stack2.pop();
                System.out.print(node.data + " ");
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); 

        tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Iterative Preorder traversal of binary tree is "); 
        tree.iterativePreOrder(); 
  
        System.out.println("\nIterative Inorder traversal of binary tree is "); 
        tree.iterativeInOrder(); 
  
        System.out.println("\nIterative Postorder traversal of binary tree is "); 
        tree.iterativePostOrder();
  
        System.out.println("\nIterative Postorder traversal of binary tree using two stacks is "); 
        tree.iterativePostOrderWithTwoStacks();
    }
}