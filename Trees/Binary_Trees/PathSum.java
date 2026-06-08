// Problem Link : https://leetcode.com/problems/path-sum

/*

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false

Example 3:
Input: root = [], targetSum = 0
Output: false


Approach 1: Using Depth First Search (DFS).

1. First, we check if the root is null. If it is, we return false since there are no paths to consider.

2. Next, we check if the current node is a leaf node (i.e., it has no left or right children). 
If it is a leaf node, we compare the value of the node with the targetSum. 
If they are equal, we return true, indicating that we have found a valid path.

3. If the current node is not a leaf node, we recursively call the hasPathSum function on the left and right children of the current node.

4. We also need to update the targetSum by subtracting the value of the current node from it before making the recursive calls.

5. Finally, we return the logical OR of the results from the left and right recursive calls.


Code:

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        if (root == null) {      // Base case: if the root is null, return false
            return false;
        }
        
        if (root.left == null && root.right == null) {   // Check if we are at a leaf node
            return root.val == targetSum;
        }
        
        int newTargetSum = targetSum - root.val;  // Recursive calls for left and right subtrees with updated targetSum
        return hasPathSum(root.left, newTargetSum) || hasPathSum(root.right, newTargetSum);
    }
}


Time Complexity: O(N), where N is the number of nodes in the binary tree. 
In the worst case, we may have to visit all nodes to find a valid path.

Space Complexity: O(H), where H is the height of the binary tree. 
In the worst case, the height of the tree can be equal to the number of nodes (O(N)) in the case of a skewed tree, leading to O(N) space complexity.


Approach 2: Using Breadth First Search (BFS).

1. First, we check if the root is null. If it is, we return false since there are no paths to consider.

2. We initialize a queue to perform a level-order traversal of the tree. 
We also initialize a variable to keep track of the current sum along the path.

3. We enqueue the root node along with its value as the initial sum.

4. We then enter a loop that continues until the queue is empty.

5. Inside the loop, we dequeue a node and its corresponding sum.

6. We check if the dequeued node is a leaf node. If it is, we compare the sum with the targetSum. 
If they are equal, we return true, indicating that we have found a valid path.

7. If the dequeued node is not a leaf node, we enqueue its left and right children (if they exist) along with their corresponding sums (current sum + child node's value).

8. Finally, if we exit the loop without finding a valid path, we return false.


Code:

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        if (root == null) {      // Base case: if the root is null, return false
            return false;
        }
        
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();  // Queue to store nodes and their corresponding sums
        queue.offer(new Pair<>(root, root.val));  // Enqueue the root node with its value as the initial sum
        
        while (!queue.isEmpty()) {  // Loop until the queue is empty
            Pair<TreeNode, Integer> current = queue.poll();  // Dequeue a node and its corresponding sum
            TreeNode currentNode = current.getKey();
            int currentSum = current.getValue();
            
            if (currentNode.left == null && currentNode.right == null) {  // Check if we are at a leaf node
                if (currentSum == targetSum) {  // Compare the sum with targetSum
                    return true;  // If they are equal, we found a valid path
                }
            }
            
            if (currentNode.left != null) {  // Enqueue left child if it exists
                queue.offer(new Pair<>(currentNode.left, currentSum + currentNode.left.val));
            }
            
            if (currentNode.right != null) {  // Enqueue right child if it exists
                queue.offer(new Pair<>(currentNode.right, currentSum + currentNode.right.val));
            }
        }
        
        return false;  // If we exit the loop without finding a valid path, return false
    }
}

Time Complexity: O(N), where N is the number of nodes in the binary tree. 
In the worst case, we may have to visit all nodes to find a valid path.

Space Complexity: O(N), where N is the number of nodes in the binary tree. 
In the worst case, we may have to store all nodes in the queue (e.g., in the case of a complete binary tree), leading to O(N) space complexity.

*/