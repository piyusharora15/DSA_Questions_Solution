// Problem Link: https://leetcode.com/problems/sum-root-to-leaf-numbers?envType=problem-list-v2&envId=wo7dda2m

/*

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.


Approach: Using DFS.

1. First we will check if the root is null or not. If it is null then we will return 0.
2. We will create a variable sum and initialize it to 0. This variable will be used to store the sum of all root-to-leaf numbers.
3. We will create a helper function that will take the current node and the current number as parameters. 
This function will be used to traverse the tree and calculate the sum of all root-to-leaf numbers.
4. In the helper function, we will check if the current node is a leaf node or not. 
If it is a leaf node then we will add the current number to the sum variable.
5. If the current node is not a leaf node then we will call the helper function for the left and right child of the current node.
6. Finally, we will return the sum variable.


Code:

class Solution {
    int sum = 0;
    
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0);
        return sum;
    }
    
    private void helper(TreeNode root, int currentNumber) {
        if(root == null) return;
        
        currentNumber = currentNumber * 10 + root.val;
        
        if(root.left == null && root.right == null) {
            sum += currentNumber;
            return;
        }
        
        helper(root.left, currentNumber);
        helper(root.right, currentNumber);
    }
}


Time Complexity: O(n) where n is the number of nodes in the tree. We are visiting each node once.

Space Complexity: O(h) where h is the height of the tree. 
In the worst case, the height of the tree can be equal to the number of nodes in the tree, which is O(n). 
In the best case, the height of the tree can be log(n) for a balanced tree. 
Therefore, the space complexity is O(h) in general.

*/