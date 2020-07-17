import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/

- Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form. The expected time 
    complexity is O(m+n) where m is the number of nodes in the first tree and n is the number of nodes 
    in the second tree. Maximum allowed auxiliary space is O(height of the first tree + height of the 
    second tree). 
- Examples:
    1)
    First BST 
       3
    /     \
   1       5
    Second BST
        4
      /   \
    2       6
    Output: 1 2 3 4 5 6

    2)
    First BST 
          8
         / \
        2   10
       /
      1
    Second BST 
          5
         / 
        3  
       /
      0
    Output: 0 1 2 3 5 8 10

- The idea is to use iterative inorder traversal. We use two auxiliary stacks for two BSTs. Since we need 
  to print the elements in sorted form, whenever we get a smaller element from any of the trees, we print 
  it. If the element is greater, then we push it back to stack for the next iteration.
- Time Complexity: O(m + n)
- Auxiliary Space: O(height of the first tree + height of the second tree)

*/
public class BST_07_MergeTwoBSTs_LimitedExtraSpace {
  static class Node {
    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  private static void inorder(Node root) {
    if (root == null) {
      return;
    }
    inorder(root.left);
    System.out.print(root.data + " ");
    inorder(root.right);
  }

  static void printMerged(Node root1, Node root2) {
    // If one BST is empty, then output is inorder traversal of second BST
    if (root1 == null) {
      inorder(root2);
    } else if (root2 == null) {
      inorder(root1);
    }

    Deque<Node> stack1 = new LinkedList<>();
    Deque<Node> stack2 = new LinkedList<>();
    // stack1 is stack to hold nodes of first BST
    // stack2 is stack to hold nodes of first BST

    Node p = root1, q = root2;

    // Run the loop while there are nodes not yet printed. The nodes may be in stack(explored, but 
    // not printed) or may be not yet explored  
    while (p != null || q != null || !stack1.isEmpty() || !stack2.isEmpty()) {

      // Reach the leftmost node of both BSTs and push ancestors of leftmost nodes to stack stack1 
      // and stack2 respectively  
      while (p != null) {
        stack1.push(p);
        p = p.left;
      }

      while (q != null) {
        stack2.push(q);
        q = q.left;
      }

      // Pop an element from both stacks and compare the popped elements  
      if (!stack1.isEmpty()) {
        p = stack1.pop();
      }
      if (!stack2.isEmpty()) {
        q = stack2.pop();
      }

      if (p == null) {
        // Only stack2 still has nodes
        System.out.print(q.data + " ");
        q = q.right;
      } else if (q == null) {
        // Only stack1 still has nodes
        System.out.print(p.data + " ");
        p = p.right;
      } else {
        // Both stacks have nodes -- Compare p and q
        if (p.data <= q.data) {
          System.out.print(p.data + " ");
          stack2.push(q);
          p = p.right;
          q = null;
        } else {
          System.out.print(q.data + " ");
          stack1.push(p);
          q = q.right;
          p = null;
        }
      }
    }
  }

  public static void main(String[] args) {
    Node root1 = null;
    root1 = new Node(4);
    root1.left = new Node(2);
    root1.right = new Node(5);
    root1.left.left = new Node(1);
    root1.left.right = new Node(3);

    Node root2 = null;
    root2 = new Node(20);
    root2.left = new Node(8);
    root2.right = new Node(22);
    root2.left.left = new Node(4);
    root2.left.right = new Node(12);
    root2.left.right.left = new Node(10);
    root2.left.right.right = new Node(14);

    // Print sorted nodes of both trees
    System.out.println("Merged BSTs: ");
    printMerged(root1, root2);
    System.out.println("");
  }
}