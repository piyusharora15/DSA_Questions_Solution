/*

Problem Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [2,1], p = 2, q = 1
Output: 2


Approach 1: Using Depth First Search (DFS).

1. First, we check if the current node is null. If it is, we return null.
2. Next, we check if the current node's value is greater than both p and q's values. If it is, we recursively call the function on the left subtree.
3. If the current node's value is less than both p and q's values, we recursively call the function on the right subtree.
4. If the current node's value is between p and q's values (inclusive), we have found the LCA and return the current node.


Code:

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        
        if (root.val < p.val && root.val < q.val) { // If both p and q are greater than root, LCA lies in right subtree
            return lowestCommonAncestor(root.right, p, q);
        }
        
        if (root.val > p.val && root.val > q.val) { // If both p and q are smaller than root, LCA lies in left subtree
            return lowestCommonAncestor(root.left, p, q);
        }
        
        return root;  // If we reach here, then root is the LCA
    }
}

Time Complexity: O(h), where h is the height of the tree. In the worst case, we might have to traverse from the root to a leaf node.

Space Complexity: O(h) for the recursion stack in the worst case.


Approach 2: Using Breadth First Search (BFS).

1. First, we check if the current node is null. If it is, we return null.
2. We use a queue to perform a level-order traversal of the tree.
3. For each node, we check if its value is greater than both p and q's values. If it is, we continue the search in the left subtree.
4. If the current node's value is less than both p and q's values, we continue the search in the right subtree.
5. If the current node's value is between p and q's values (inclusive), we have found the LCA and return the current node.

Code:

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            if (current.val < p.val && current.val < q.val) { // If both p and q are greater than current, LCA lies in right subtree
                queue.offer(current.right);
            } else if (current.val > p.val && current.val > q.val) { // If both p and q are smaller than current, LCA lies in left subtree
                queue.offer(current.left);
            } else {
                return current;  // If we reach here, then current is the LCA
            }
        }
        
        return null;  // This line will never be reached if p and q are guaranteed to be in the tree
    }
}

Time Complexity: O(h), where h is the height of the tree. In the worst case, we might have to traverse from the root to a leaf node.

Space Complexity: O(w), where w is the maximum width of the tree. In the worst case, we might have to store all nodes at the current level in the queue.

*/