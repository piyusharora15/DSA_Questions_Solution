// Problem Link: https://leetcode.com/problems/binary-tree-preorder-traversal?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [1,2,4,5,6,7,3,8,9]

Example 3:
Input: root = []
Output: []

Example 4:
Input: root = [1]
Output: [1]

Approach:

1. First we will create a list to store the result of the preorder traversal.
2. We will create a helper function that takes the current node as an argument.
3. In the helper function, we will first check if the current node is null. If it is, we will return.
4. If the current node is not null, we will add its value to the result list.
5. We will then recursively call the helper function for the left child of the current node.
6. After that, we will recursively call the helper function for the right child of the current node.
7. Finally, we will call the helper function with the root node and return the result list.


Code:

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val); // Add the current node's value to the result list
        helper(node.left, result); // Recur for the left subtree
        helper(node.right, result); // Recur for the right subtree
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree, since we visit each node exactly once.

Space Complexity: O(n) in the worst case (when the tree is completely unbalanced), and O(log n) in the best case (when the tree is completely balanced), due to the recursive call stack. The result list also takes O(n) space to store the values of all nodes.

*/