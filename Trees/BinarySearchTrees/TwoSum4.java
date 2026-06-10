// Problem Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

Example 1:
Input: root = [5,3,6,2,4,null,7], k = 9
Output: true

Example 2:
Input: root = [5,3,6,2,4,null,7], k = 28
Output: false


Approach 1: Using HashSet.

1. First, we will traverse the tree and store the values of the nodes in a HashSet.
2. Then, we will traverse the tree again and for each node, we will check if (k - node.val) exists in the HashSet.
3. If it exists, we will return true. If we finish traversing the tree and do not find such a pair, we will return false.


Code:

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    
    private boolean dfs(TreeNode node, HashSet<Integer> set, int k) {
        if (node == null) {
            return false;
        }
        
        if (set.contains(k - node.val)) {
            return true;
        }
        
        set.add(node.val);
        
        return dfs(node.left, set, k) || dfs(node.right, set, k);
    }
}

Time Complexity: O(n), where n is the number of nodes in the tree. 
We traverse the tree once to store the values in the HashSet and once to check for the pairs.

Space Complexity: O(n), where n is the number of nodes in the tree. 
In the worst case, we may store all the node values in the HashSet.


Approach 2: Using Inorder Traversal and Two Pointers.

1. First, we will perform an inorder traversal of the BST to get a sorted list of the node values.
2. Then, we will use two pointers, one starting at the beginning of the list and the other starting at the end of the list.
3. We will check the sum of the values at the two pointers. 
If the sum is equal to k, we will return true. 
If the sum is less than k, we will move the left pointer to the right. 
If the sum is greater than k, we will move the right pointer to the left.
4. If the two pointers meet and we do not find such a pair, we will return false.


Code:

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        int left = 0;
        int right = list.size() - 1;
        
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        
        return false;
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree. 
We traverse the tree once to perform the inorder traversal and once to check for the pairs using the two pointers.

Space Complexity: O(n), where n is the number of nodes in the tree. 
In the worst case, we may store all the node values in the list during the inorder traversal.

*/