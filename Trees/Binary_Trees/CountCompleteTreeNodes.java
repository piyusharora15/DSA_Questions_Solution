// Problem Link: https://leetcode.com/problems/count-complete-tree-nodes?envType=problem-list-v2&envId=tree

/*

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:
Input: root = []
Output: 0

Example 3:
Input: root = [1]
Output: 1


Approach 1: Using DFS Traversal.

1. First, we will check if the root is null, if yes then return 0.
2. Otherwise, we will recursively count the nodes in the left and right subtrees and add 1 for the current node.
3. Finally, we will return the total count of nodes.


Code:

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree.

Space Complexity: O(h), where h is the height of the tree, which is the space used by the recursion stack.

Approach 2: Using Binary Search.

1. First, we will check if the root is null, if yes then return 0.
2. We will calculate the height of the tree by traversing the leftmost path from the root to the leaf node.
3. We will use binary search to find the number of nodes in the last level.
4. Finally, we will return the total count of nodes.

Code:

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int height = getHeight(root);
        if (height == 0) {
            return 1;
        }
        
        int left = 0, right = (1 << height) - 1; // (1 << height) is equivalent to 2^height, which gives the maximum number of nodes at the last level.
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (exists(mid, height, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return (1 << height) - 1 + left;
    }
    
    private int getHeight(TreeNode node) {
        int height = 0;
        while (node.left != null) {
            node = node.left;
            height++;
        }
        return height;
    }
    
    private boolean exists(int index, int height, TreeNode node) {
        int left = 0, right = (1 << height) - 1;
        for (int i = 0; i < height; i++) {
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                node = node.left;
                right = mid;
            } else {
                node = node.right;
                left = mid + 1;
            }
        }
        return node != null;
    }
}

Time Complexity: O(log^2 n), where n is the number of nodes in the tree. The height of the tree is log(n), and we perform a binary search on the last level which takes O(log n) time.

Space Complexity: O(1), as we are using only a constant amount of extra space for the variables.

*/