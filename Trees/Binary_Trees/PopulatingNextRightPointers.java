// Problem Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node?envType=problem-list-v2&envId=wo7dda2m

/*

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]

Example 2:
Input: root = []
Output: []


Approach 1: Using Level Order Traversal (BFS)

1. First, we check if the root is null. If it is, we return null.
2. We initialize a queue and add the root node to it.
3. We then enter a while loop that continues until the queue is empty.
4. Inside the loop, we get the size of the queue, which represents the number of nodes at the current level.
5. We then iterate through the nodes at the current level using a for loop.
6. For each node, we dequeue it from the queue and check if it is not the last node in the current level. If it is not, we set its next pointer to the next node in the queue.
7. We then enqueue the left and right children of the current node if they are not null.
8. After processing all nodes at the current level, we continue to the next level until the queue is empty.
9. Finally, we return the root of the modified tree.


Code:

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            
            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();
                
                if (prev != null) {
                    prev.next = currentNode;
                }
                prev = currentNode;
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.

Space Complexity: O(n), where n is the number of nodes in the tree. In the worst case, we may have to store all nodes at the last level in the queue.


Approach 2: Using Constant Space (DFS).

1. First, we check if the root is null. If it is, we return null.
2. We initialize a variable called leftmost to keep track of the leftmost node at each level. We start with the root node.
3. We enter a while loop that continues as long as leftmost is not null.
4. Inside the loop, we initialize a variable called current to keep track of the current node at the current level. We start with leftmost.
5. We then enter another while loop that continues as long as current is not null.
6. Inside the inner loop, we connect the left child of the current node to its right child.
7. If the current node has a next pointer, we connect the right child of the current node to the left child of the next node.
8. We then move to the next node at the current level by setting current to current.next.
9. After processing all nodes at the current level, we move to the next level by setting leftmost to leftmost.left.
10. Finally, we return the root of the modified tree.


Code:

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Node leftmost = root;
        
        while (leftmost.left != null) {
            Node current = leftmost;
            
            while (current != null) {
                current.left.next = current.right;
                
                if (current.next != null) {
                    current.right.next = current.next.left;
                }
                
                current = current.next;
            }
            
            leftmost = leftmost.left;
        }
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.

Space Complexity: O(1), as we are using constant space and not using any additional data structures for traversal.

*/