// Problem Link: https://leetcode.com/problems/leaf-similar-trees?envType=problem-list-v2&envId=tree

/*

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false


Approach: Using DFS.

1. First we will traverse the first tree and store the leaf nodes in a list.
2. Then we will traverse the second tree and store the leaf nodes in another list.
3. Finally, we will compare the two lists and return true if they are equal, otherwise false.


Code:

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        
        return leaves1.equals(leaves2);
    }
    
    private void dfs(TreeNode node, List<Integer> leaves) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
        }
        
        dfs(node.left, leaves);
        dfs(node.right, leaves);
    }
}


Time Complexity: O(N + M), where N is the number of nodes in the first tree and M is the number of nodes in the second tree. We traverse both trees once.

Space Complexity: O(L1 + L2), where L1 is the number of leaves in the first tree and L2 is the number of leaves in the second tree. We store the leaf nodes in two separate lists.


*/