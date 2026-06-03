// Problem Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal?envType=problem-list-v2&envId=wo7dda2m

/*

Company Tags: Amazon, Apple, Facebook, Google, Microsoft.

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]


Approach: Using HashMap and Recursion.

1. First, we create a HashMap to store the indices of the elements in the inorder array for O(1) access.
2. We define a recursive function that takes the current range of the inorder array and the current index in the postorder array.
3. The last element in the postorder array is the root of the current subtree. We create a new TreeNode with this value.
4. We find the index of this root value in the inorder array using the HashMap.
5. We recursively build the right subtree first (since we are processing postorder from the end) and then the left subtree.
6. Finally, we return the constructed tree.


Code:

class Solution {
    private Map<Integer, Integer> inorderIndexMap;
    private int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        inorderIndexMap = new HashMap<>(); // Build a HashMap to store the index of each value in the inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        postorderIndex = postorder.length - 1; // Start from the last index of the postorder array
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int left, int right) {
        
        if (left > right) { // Base case: if there are no elements to construct the tree
            return null;
        }
        
        int rootValue = postorder[postorderIndex--];  // Get the current root value from the postorder array
        TreeNode root = new TreeNode(rootValue);
        
        int inorderIndex = inorderIndexMap.get(rootValue); // Get the index of the root value in the inorder array
        
        root.right = buildTreeHelper(inorder, postorder, inorderIndex + 1, right); // Recursively build the right subtree first
    
        root.left = buildTreeHelper(inorder, postorder, left, inorderIndex - 1); // Then build the left subtree
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node once to construct the tree.

Space Complexity: O(n), where n is the number of nodes in the tree. 
The space complexity is due to the HashMap and the recursive call stack in the worst case (when the tree is skewed).


*/