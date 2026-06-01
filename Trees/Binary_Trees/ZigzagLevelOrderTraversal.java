// Problem Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values' (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []


Approach: Using BFS (Breadth First Search).

1. First we will check if the root is null, if it is then we will return an empty list.
2. We will create a queue to store the nodes of the tree and a list to store the result.
3. We will add the root node to the queue and a boolean variable to keep track of the direction of traversal (left to right or right to left).
4. We will run a while loop until the queue is empty.
5. Inside the while loop, we will get the size of the queue (number of nodes at the current level) and create a list to store the values of the nodes at the current level.
6. We will run a for loop from 0 to size-1 to process each node at the current level.
7. Inside the for loop, we will remove the front node from the queue and add its value to the list of values for the current level.
8. We will then add the left and right children of the current node to the queue if they are not null.
9. After processing all the nodes at the current level, we will check the direction of traversal and reverse the list of values if the direction is right to left.
10. We will add the list of values for the current level to the result list and toggle the direction for the next level.


Code:

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelValues = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                levelValues.add(currentNode.val);
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            if (!leftToRight) {
                Collections.reverse(levelValues);
            }
            result.add(levelValues);
            leftToRight = !leftToRight; // Toggle the direction
        }
        
        return result;
    }
}



Time Complexity: O(n) where n is the number of nodes in the binary tree, as we need to visit each node once.

Space Complexity: O(n) in the worst case when the binary tree is completely unbalanced (like a linked list), and O(w) where w is the maximum width of the tree in the best case (when the tree is balanced).

*/