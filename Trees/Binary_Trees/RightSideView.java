// Problem Link: https://leetcode.com/problems/binary-tree-right-side-view?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:
Input: root = [1,2,3,4,null,null,null,5]
Output: [1,3,4,5]

Example 3:
Input: root = [1,null,3]
Output: [1,3]

Example 4:
Input: root = []
Output: []


Approach 1: Using Level Order Traversal (BFS).

1. First, we will check if the root is null. If it is, we will return an empty list.

2. We will initialize a queue to perform level order traversal and a list to store the right side view of the tree.

3. We will add the root node to the queue.

4. We will enter a loop that continues until the queue is empty. 
Inside the loop, we will determine the number of nodes at the current level (size of the queue).

5. We will iterate through the nodes at the current level. 
For each node, we will check if it is the last node in the level (i.e., if it is the rightmost node). 
If it is, we will add its value to the right side view list.

6. We will then add the left and right children of the current node to the queue (if they exist) to process the next level.

7. After the loop ends, we will return the list containing the right side view of the tree.


Code:

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        if (root == null) {
            return rightSideView;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (i == levelSize - 1) {  // If it's the last node in the current level, add it to the right side view
                    rightSideView.add(currentNode.val);
                }

                if (currentNode.left != null) {  // Add left and right children to the queue
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return rightSideView;
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(w), where w is the maximum width of the binary tree. 
In the worst case, the width can be O(n) when the tree is a complete binary tree, leading to O(n) space complexity.


Approach 2: Using Depth-First Search (DFS).

1. First, we will check if the root is null. If it is, we will return an empty list.

2. We will initialize a list to store the right side view of the tree.

3. We will define a helper function that takes a node and its depth as parameters.

4. In the helper function, we will check if the current node is null. If it is, we will return.

5. We will check if the current depth is equal to the size of the right side view list.
If it is, it means we are visiting this depth for the first time, and we will add the current node's value to the right side view list.

6. We will then recursively call the helper function for the right child of the current node, followed by the left child.

7. After the helper function has processed all nodes, we will return the list containing the right side view of the tree.


Code:

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        dfs(root, 0, rightSideView);
        return rightSideView;
    }

    private void dfs(TreeNode node, int depth, List<Integer> rightSideView) {
        if (node == null) {
            return;
        }

        if (depth == rightSideView.size()) {  // If this is the first time we are visiting this depth
            rightSideView.add(node.val);
        }

        dfs(node.right, depth + 1, rightSideView);  // Traverse the right child first
        dfs(node.left, depth + 1, rightSideView);   // Then traverse the left child
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(h), where h is the height of the binary tree.
In the worst case, the height can be O(n) when the tree is skewed, leading to O(n) space complexity due to the recursive call stack. 
In a balanced tree, the height would be O(log n), resulting in O(log n) space complexity.

*/