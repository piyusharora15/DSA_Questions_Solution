// Problem Link: https://leetcode.com/problems/merge-two-binary-trees?envType=problem-list-v2&envId=wo7dda2m

/*

You are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.

Example 1:
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]

Example 2:
Input: root1 = [1], root2 = [1,2]
Output: [2,2]


Approach 1: Using Depth First Search (DFS).

1. First, we check if both root1 and root2 are null. If they are, we return null.
2. If one of the roots is null, we return the other root as the merged tree.
3. If both roots are not null, we create a new TreeNode with the value equal to the sum of the values of root1 and root2.
4. We then recursively merge the left children of root1 and root2 and assign the result to the left child of the new TreeNode.
5. Similarly, we recursively merge the right children of root1 and root2 and assign the result to the right child of the new TreeNode.
6. Finally, we return the new TreeNode as the merged tree.


Code:

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        
        return merged;
    }
}

Time Complexity: O(n), where n is the total number of nodes in both trees. 
In the worst case, we may have to visit all nodes of both trees.

Space Complexity: O(n), where n is the total number of nodes in both trees. 
In the worst case, the recursion stack can go as deep as the total number of nodes in both trees, especially if the trees are skewed.



Approach 2: Using Breadth First Search (BFS).

1. First, we check if both root1 and root2 are null. If they are, we return null.
2. If one of the roots is null, we return the other root as the merged tree.
3. If both roots are not null, we create a new TreeNode with the value equal to the sum of the values of root1 and root2.
4. We then use a queue to perform a level order traversal of both trees simultaneously. 
We enqueue the left and right children of both trees along with the corresponding nodes in the merged tree.
5. For each pair of nodes dequeued, we check if they are null. 
If both nodes are not null, we create a new TreeNode with the value equal to the sum of the values of the two nodes and assign it to the corresponding child of the merged tree.
6. We then enqueue the left and right children of both nodes along with the corresponding nodes in the merged tree.
7. Finally, we return the merged tree.


Code:

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        
        TreeNode merged = new TreeNode(root1.val + root2.val);
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{root1, root2, merged});
        
        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();
            TreeNode node1 = nodes[0];
            TreeNode node2 = nodes[1];
            TreeNode mergedNode = nodes[2];
            
            if (node1.left != null || node2.left != null) {
                if (node1.left != null && node2.left != null) {
                    mergedNode.left = new TreeNode(node1.left.val + node2.left.val);
                    queue.offer(new TreeNode[]{node1.left, node2.left, mergedNode.left});
                } else if (node1.left != null) {
                    mergedNode.left = node1.left;
                } else {
                    mergedNode.left = node2.left;
                }
            }
            
            if (node1.right != null || node2.right != null) {
                if (node1.right != null && node2.right != null) {
                    mergedNode.right = new TreeNode(node1.right.val + node2.right.val);
                    queue.offer(new TreeNode[]{node1.right, node2.right, mergedNode.right});
                } else if (node1.right != null) {
                    mergedNode.right = node1.right;
                } else {
                    mergedNode.right = node2.right;
                }
            }
        }
        
        return merged;
    }
}


Time Complexity: O(n), where n is the total number of nodes in both trees. 
In the worst case, we may have to visit all nodes of both trees.

Space Complexity: O(n), where n is the total number of nodes in both trees. 
In the worst case, the queue can hold all nodes of both trees, especially if the trees are complete binary trees.

*/