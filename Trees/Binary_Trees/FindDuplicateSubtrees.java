// Problem Link: https://leetcode.com/problems/find-duplicate-subtrees?envType=problem-list-v2&envId=tree

/*

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]


Approach: Using HashMap and DFS traversal.

1. First, we will create a HashMap to store the serialized representation of each subtree and its frequency.
2. We will perform a DFS traversal of the binary tree, and for each node, we will serialize its subtree into a string representation.
3. The serialization will be done in a way that includes the value of the current node and the serialized representations of its left and right subtrees.
4. If the serialized representation of a subtree has been seen before (i.e., its frequency in the HashMap is 1), we will add the current node to the result list.
5. Finally, we will return the list of duplicate subtree roots.


Code:

class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        serialize(root,map,result);
        return result;
    }

    private String serialize(TreeNode node,Map<String,Integer> map,List<TreeNode> result){
        if(node == null) return "#";
        String left = serialize(node.left,map,result);
        String right = serialize(node.right,map,result);
        String serial = node.val + "," + left + "," + right;
        int c = map.getOrDefault(serial,0);
        if(c == 1){
            result.add(node);
        }
        map.put(serial,c+1);
        return serial;
    }
}


Time Complexity: O(N) where N is the number of nodes in the tree. Each node is visited once during the DFS traversal.

Space Complexity: O(N) for the hash map and the recursion stack in the worst case.

*/
