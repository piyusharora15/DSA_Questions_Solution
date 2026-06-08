// Problem Link : https://leetcode.com/problems/path-sum-ii?envType=problem-list-v2&envId=tree

/*

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:
Input: root = [1,2], targetSum = 0
Output: []


Approach: Using DFS:

1. First we will check if the root is null or not, if it is null then we will return an empty list.

2. We will create a list of list to store the result and a list to store the current path.

3. We will call the dfs function with the root, targetSum, current path and result list.

4. In the dfs function, we will check if the current node is null or not, if it is null then we will return.

5. We will add the current node value to the current path.

6. We will check if the current node is a leaf node and if the current path sum is equal to targetSum, if it is then we will add the current path to the result list.

7. We will call the dfs function for the left and right child of the current node.

8. After the dfs function call, we will remove the current node value from the current path.

9. Finally, we will return the result list.


Code:

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);

        if (node.left == null && node.right == null && targetSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        }

        dfs(node.left, targetSum - node.val, currentPath, result);
        dfs(node.right, targetSum - node.val, currentPath, result);

        currentPath.remove(currentPath.size() - 1);
    }
}


Time Complexity: O(N) where N is the number of nodes in the tree, as we are visiting each node once.

Space Complexity: O(H) where H is the height of the tree, as we are using a stack to store the current path and the maximum depth of the stack will be equal to the height of the tree.

*/