// Problem Link: https://leetcode.com/problems/binary-tree-level-order-traversal-ii?envType=problem-list-v2&envId=wo7dda2m


/*

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []


Approach: Using BFS (Breadth First Search).

1. First we will check if the root is null, if it is then we will return an empty list.
2. We will create a queue to store the nodes of the tree and a list to store the result.
3. We will add the root node to the queue.
4. We will run a while loop until the queue is empty.
5. Inside the while loop, we will get the size of the queue and create a list to store the values of the nodes at the current level.
6. We will run a for loop from 0 to size-1 and inside the for loop, we will remove the first node from the queue and add its value to the list.
7. We will check if the left child of the node is not null, if it is not null then we will add it to the queue.
8. We will check if the right child of the node is not null, if it is not null then we will add it to the queue.
9. After the for loop, we will add the list of values to the result list.
10. After the while loop, we will reverse the result list and return it.


Code:

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        
        Collections.reverse(result);
        return result;
    }
}


Time Complexity: O(n) where n is the number of nodes in the tree. We visit each node once.

Space Complexity: O(n) where n is the number of nodes in the tree. 
In the worst case, we may have to store all the nodes in the queue and the result list.

*/