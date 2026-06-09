// Problem Link: https://leetcode.com/problems/average-of-levels-in-binary-tree?envType=problem-list-v2&envId=wo7dda2m

/*

Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10^-5 of the actual answer will be accepted.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].

Example 2:
Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]


Approach 1: Using BFS.

1. First we will create a list of type double to store the average of each level.
2. We will use a queue to perform BFS on the tree. We will start by adding the root node to the queue.
3. We will then enter a loop that continues until the queue is empty. 
Inside the loop, we will determine the number of nodes at the current level by checking the size of the queue.
4. We will initialize a variable to keep track of the sum of the node values at the current level.
5. We will then iterate through all the nodes at the current level, dequeueing each node and adding its value to the sum. 
If the node has left or right children, we will enqueue them for processing in the next level.
6. After processing all nodes at the current level, we will calculate the average by dividing the sum by the number of nodes at that level and add it to our result list.
7. Finally, we will return the list of averages.


Code:

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                sum += currentNode.val;
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            result.add(sum / size);
        }
        
        return result;
    }
}

Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(m), where m is the maximum number of nodes at any level in the binary tree. 
In the worst case, this could be O(n/2) for a complete binary tree, which simplifies to O(n).


Approach 2: Using DFS.

1. First we will create a list of type double to store the average of each level and a list of type integer to store the count of nodes at each level.
2. We will define a helper function that takes the current node and the current level as parameters.
3. Inside the helper function, we will check if the current node is null. If it is, we will return from the function.
4. We will then check if the current level is equal to the size of our result list. If it is, it means we are visiting this level for the first time, so we will add the value of the current node to the result list and set the count for that level to 1.
5. If the current level is less than the size of our result list, it means we have already visited this level before, so we will add the value of the current node to the existing sum for that level and increment the count for that level.
6. We will then recursively call the helper function for the left and right children of the current node, incrementing the level by 1 for each call.
7. After the DFS traversal is complete, we will iterate through the result list and divide each sum by the corresponding count to get the average for each level.


Code:


class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<Double> sumList = new ArrayList<>();
        List<Double> countList = new ArrayList<>();
        dfs(root,0,sumList,countList);
        for(int i=0;i<sumList.size();i++){
            result.add(sumList.get(i) / countList.get(i));
        }
        return result;
    }
    private void dfs(TreeNode node, int level, List<Double> sumList, List<Double> countList){
        if(node == null) return;
        if(level < sumList.size()){
            sumList.set(level, sumList.get(level) + node.val);
            countList.set(level, countList.get(level) + 1);
        }
        else {
            sumList.add((double)node.val);
            countList.add(1.0);
        }
        dfs(node.left,level+1,sumList,countList);
        dfs(node.right,level+1,sumList,countList);
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(h), where h is the height of the binary tree. In the worst case, this could be O(n) for a skewed binary tree, and O(log n) for a balanced binary tree.

*/