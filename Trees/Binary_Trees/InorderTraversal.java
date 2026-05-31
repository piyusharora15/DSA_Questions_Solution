// Problem Link: https://leetcode.com/problems/binary-tree-inorder-traversal?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [4,2,6,5,7,1,3,9,8]

Example 3:
Input: root = []
Output: []

Example 4:
Input: root = [1]
Output: [1]


Approach:

1. First we will create a list to store the result of the inorder traversal.
2. We will define a helper function that takes a node as an argument and performs the inorder traversal.
3. In the helper function, we will first check if the node is null. If it is, we will return.
4. If the node is not null, we will recursively call the helper function on the left child of the node.
5. After the left subtree has been traversed, we will add the value of the current node to the result list.
6. Finally, we will recursively call the helper function on the right child of the node.


Code:

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        helper(node.left, result);
        result.add(node.val);
        helper(node.right, result);
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(n), where n is the number of nodes in the binary tree. In the worst case, the binary tree is completely unbalanced, and we have to store all the nodes in the recursion stack. In the best case, the binary tree is completely balanced, and we have to store only log(n) nodes in the recursion stack. However, since we are also storing the result in a list, we need O(n) space for that as well.

*/