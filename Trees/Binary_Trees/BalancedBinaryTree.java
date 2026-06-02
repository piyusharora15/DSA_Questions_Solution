// Problem Link: https://leetcode.com/problems/balanced-binary-tree?envType=problem-list-v2&envId=tree

/*

Given a binary tree, determine if it is height-balanced.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true


Naive Approach: 

1. First we will find the height of the left and right subtree of every node and check if the difference is more than 1 or not. 
If it is more than 1 then we will return false else we will return true.
2. We will repeat this process for every node in the tree.


Code:

class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        if(Math.abs(leftHeight - rightHeight) > 1) return false;
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int height(TreeNode node){
        if(node == null) return 0;
        
        return 1 + Math.max(height(node.left), height(node.right));
    }
}

Time Complexity: O(n^2) where n is the number of nodes in the tree. 
This is because for every node we are calculating the height of its left and right subtree which takes O(n) time and we are doing this for every node in the tree.

Space Complexity: O(n) where n is the number of nodes in the tree.
This is because in the worst case we will have to store all the nodes in the stack for the recursive calls.


Optimal Approach: Using Post Order Traversal.

1. First we will check if the left subtree is balanced or not and if it is not balanced then we will return -1.
2. Then we will check if the right subtree is balanced or not and if it is not balanced then we will return -1.
3. If both the left and right subtree are balanced then we will return the height of the current node which is 1 + max(leftHeight, rightHeight).
4. If at any point we get -1 then we will return false else we will return true.
5. We will repeat this process for every node in the tree.


Code:

class Solution {
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private int checkBalance(TreeNode node){
        if(node == null) return 0;
        
        int leftHeight = checkBalance(node.left);
        if(leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(node.right);
        if(rightHeight == -1) return -1;
        
        if(Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
}

Time Complexity: O(n) where n is the number of nodes in the tree.
This is because we are visiting each node once to check if it is balanced or not.

Space Complexity: O(n) where n is the number of nodes in the tree.
This is because in the worst case we will have to store all the nodes in the stack for the recursive calls.

*/