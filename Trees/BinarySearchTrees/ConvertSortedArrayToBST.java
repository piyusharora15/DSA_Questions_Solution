// Problem Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

/*

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.



Approach: 

1. First, we will find the middle element of the array and make it the root of the BST. 
This is because the middle element will ensure that the left and right subtrees are balanced.

2. Then, we will recursively do the same for the left half of the array to create the left subtree and for the right half of the array to create the right subtree.

3. The base case for the recursion will be when the left index is greater than the right index, in which case we will return null.

4. Finally, we will return the root of the BST.


Code:

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);
        
        return node;
    }
}


Time Complexity: O(n), where n is the number of elements in the input array. 
This is because we need to visit each element of the array once to create the nodes of the BST.

Space Complexity: O(log n) on average, due to the recursive call stack.
In the worst case, when the tree is completely unbalanced (like a linked list), the space complexity can go up to O(n). 
However, since we are creating a height-balanced BST, the average space complexity will be O(log n).

*/