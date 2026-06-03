// Problem Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal?envType=problem-list-v2&envId=tree

/*

Company Tags: Amazon, Apple, Facebook, Google, Microsoft.

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]


Approach: Using Preorder Traversal and HashMap.

1. First, we will create a HashMap to store the index of each value in the inorder array for O(1) access.
2. We will use a recursive helper function to construct the tree. 
The function will take the current range of the preorder and inorder arrays as parameters.
3. The first element of the preorder array will be the root of the current subtree. 
We will find the index of this root in the inorder array using our HashMap.
4. We will then recursively construct the left and right subtrees using the corresponding ranges in the preorder and inorder arrays.
5. Finally, we will return the constructed tree.


Code:

class Solution {
    private int preIndex = 0;
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inorderIndexMap.get(rootVal);

        root.left = buildTreeHelper(preorder, inStart, inIndex - 1);
        root.right = buildTreeHelper(preorder, inIndex + 1, inEnd);

        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node once to construct the tree.

Space Complexity: O(n), where n is the number of nodes in the tree. 
The space complexity is due to the HashMap storing the indices of the inorder traversal and the recursive call stack in the worst case (when the tree is skewed).

*/