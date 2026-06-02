// Problem Link: https://leetcode.com/problems/invert-binary-tree?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []


Approach 1: Recursive Depth-First Search (DFS).

1. First check if the current node is null. If it is, return null.
2. Recursively call the function to invert the left subtree and store the result in a variable (e.g., leftInverted).
3. Recursively call the function to invert the right subtree and store the result in another variable (e.g., rightInverted).
4. Swap the left and right children of the current node.
5. Return the root of the inverted tree.


Code:

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode leftInverted = invertTree(root.left);   // Invert the left and right subtrees
        TreeNode rightInverted = invertTree(root.right);
        
        root.left = rightInverted;   // Swap the left and right children
        root.right = leftInverted;
        
        return root;
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree, since we visit each node exactly once.

Space Complexity: O(h), where h is the height of the binary tree, due to the recursive call stack. 
In the worst case (for a skewed tree), the space complexity can be O(n).


Approach 2: Iterative Breadth-First Search (BFS) using a Queue.

1. Check if the root is null. If it is, return null.
2. Initialize a queue and add the root node to it.
3. While the queue is not empty:
   a. Remove the front node from the queue (let's call it currentNode).
   b. Swap the left and right children of currentNode.
   c. If the left child of currentNode is not null, add it to the queue.
   d. If the right child of currentNode is not null, add it to the queue.
4. Return the root of the inverted tree.


Code:

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            
            TreeNode temp = currentNode.left;  // Swap the left and right children
            currentNode.left = currentNode.right;
            currentNode.right = temp;
            
            if (currentNode.left != null) {  // Add the left and right children to the queue if they are not null
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        
        return root;
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree, since we visit each node exactly once.

Space Complexity: O(n), in the worst case (for a complete binary tree), the queue can hold all the nodes at the last level, which is O(n/2) = O(n). 
In the best case (for a skewed tree), the space complexity can be O(1) if we consider the queue to be empty after processing each node.

*/