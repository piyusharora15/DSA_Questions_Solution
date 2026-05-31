// Problem Link: https://leetcode.com/problems/binary-tree-postorder-traversal?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [4,6,7,5,2,9,8,3,1]

Example 3:
Input: root = []
Output: []

Example 4:
Input: root = [1]
Output: [1]


Approach:

1. First we will create a list to store the postorder traversal result.
2. We will define a helper function that takes a node as an argument and performs the postorder traversal.
3. In the helper function, we will first check if the node is null. If it is null, we will return from the function.
4. If the node is not null, we will recursively call the helper function on the left child of the node.
5. After the left subtree has been traversed, we will recursively call the helper function on the right child of the node.
6. Finally, we will add the value of the current node to the result list.


Code:

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        postorderHelper(node.left, result); // Traverse left subtree
    
        postorderHelper(node.right, result); // Traverse right subtree
        
        result.add(node.val); // Add current node's value to the result list
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(n) in the worst case (when the tree is completely unbalanced), and O(log n) in the best case (when the tree is completely balanced). This is because of the recursive call stack used for the postorder traversal.

*/