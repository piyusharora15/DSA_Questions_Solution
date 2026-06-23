// Problem Link: https://leetcode.com/problems/maximum-width-of-binary-tree?envType=problem-list-v2&envId=tree

/*

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

Example 2:
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

Example 3:
Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).


Approach: Using BFS.

1. First, we will check if the root is null or not. If it is null, we will return 0.
2. We will use a queue to perform BFS traversal. Each element in the queue will be a pair containing the node and its index in the current level.
3. For each level, we will calculate the width as the difference between the indices of the rightmost and leftmost nodes, plus 1.
4. We will keep track of the maximum width encountered so far.
5. Finally, we will return the maximum width.


Code:

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int minIndex = queue.peek().getValue(); // Get the index of the first node in the level
            int firstIndex = 0, lastIndex = 0;

            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.getKey();
                int index = current.getValue() - minIndex; // Normalize index to prevent overflow

                if (i == 0) firstIndex = index; // First node's index
                if (i == size - 1) lastIndex = index; // Last node's index

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * index + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * index + 2));
                }
            }

            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }

        return maxWidth;
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once.

Space Complexity: O(n), where n is the number of nodes in the binary tree. In the worst case, we may have to store all nodes in the queue at once (for example, if the tree is a complete binary tree).

*/