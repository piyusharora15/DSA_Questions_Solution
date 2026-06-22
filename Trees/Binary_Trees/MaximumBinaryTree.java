// Problem Link: https://leetcode.com/problems/maximum-binary-tree?envType=problem-list-v2&envId=wo7dda2m

/*

You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.


Example 1:
Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.

Example 2:
Input: nums = [3,2,1]
Output: [3,null,2,null,1]


Approach 1: Using Recursion.

1. First, we will find the maximum element in the given array and create a new TreeNode with that value.
2. Then, we will recursively call the function for the left subarray (elements to the left of the maximum element) and assign the returned TreeNode to the left child of the current node.
3. Similarly, we will recursively call the function for the right subarray (elements to the right of the maximum element) and assign the returned TreeNode to the right child of the current node.
4. Finally, we will return the current node.


Code:

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }
    
    private TreeNode construct(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        int maxIndex = left; // Find the index of the maximum element in the current subarray
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        TreeNode node = new TreeNode(nums[maxIndex]);  // Create a new TreeNode with the maximum value
        
        node.left = construct(nums, left, maxIndex - 1);   // Recursively construct the left and right subtrees
        node.right = construct(nums, maxIndex + 1, right);
        
        return node;
    }
}


Time Complexity: O(n^2) in the worst case (when the array is sorted in ascending or descending order), and O(n log n) on average.

Space Complexity: O(n) in the worst case (when the array is sorted in ascending or descending order), and O(log n) on average due to the recursive call stack.


Approach 2: Using a Monotonic Stack.

1. First, we will create an empty stack to keep track of the nodes in the tree.
2. We will iterate through the given array and for each element, we will create a new TreeNode.
3. While the stack is not empty and the value of the current element is greater than the value of the node at the top of the stack, we will pop the node from the stack and set it as the left child of the current node.
4. If the stack is not empty after the above step, we will set the current node as the right child of the node at the top of the stack.
5. Finally, we will push the current node onto the stack.
6. After processing all elements, the root of the maximum binary tree will be the bottom-most node in the stack.
7. We will return the root node.


Code:

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();
        
        for (int num : nums) {
            TreeNode currentNode = new TreeNode(num);
            
            while (!stack.isEmpty() && stack.peek().val < num) {
                currentNode.left = stack.pop();  // Set the popped node as the left child of the current node
            }
            
            if (!stack.isEmpty()) {
                stack.peek().right = currentNode;  // Set the current node as the right child of the top node in the stack
            }
            
            stack.push(currentNode);  // Push the current node onto the stack
        }
        
        while (stack.size() > 1) { // The root of the maximum binary tree will be the bottom-most node in the stack
            stack.pop();
        }
        
        return stack.peek();  // Return the root node
    }
}


Time Complexity: O(n) since we are iterating through the array once and each element is pushed and popped from the stack at most once.

Space Complexity: O(n) for the stack used to store the nodes of the tree.

*/