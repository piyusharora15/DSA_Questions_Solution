// Problem Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii?envType=problem-list-v2&envId=wo7dda2m


/*

Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]

Example 2:
Input: root = []
Output: []


Approach 1: Using Level Order Traversal (BFS).

1. First, we will check if the root is null. If it is, we will return null.
2. We will create a queue and add the root node to it.
3. We will then enter a while loop that will continue until the queue is empty.
4. Inside the while loop, we will get the size of the queue, which represents the number of nodes at the current level.
5. We will then enter a for loop that will iterate through each node at the current level.
6. Inside the for loop, we will dequeue a node from the queue and check if it is not the last node at the current level. If it is not, we will set its next pointer to the next node in the queue.
7. We will then enqueue the left and right children of the current node (if they exist) to the queue for processing in the next level.
8. After processing all nodes at the current level, we will continue to the next iteration of the while loop until all levels have been processed.
9. Finally, we will return the root node, which now has its next pointers populated.


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
                Node current = queue.poll();
                
                if (prev != null) {
                    prev.next = current;
                }
                prev = current;
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(n), where n is the number of nodes in the binary tree. In the worst case, we may have to store all nodes at the last level in the queue.


Approach 2: Using Constant Space (Iterative).

1. First, we will check if the root is null. If it is, we will return null.
2. We will create a variable called 'current' and set it to the root node.
3. We will enter a while loop that will continue until 'current' is null.
4. Inside the while loop, we will create a variable called 'dummy' and set it to a new Node with value 0. We will also create a variable called 'tail' and set it to 'dummy'.
5. We will then enter another while loop that will continue until 'current' is null.
6. Inside the inner while loop, we will check if 'current.left' is not null. If it is not, we will set 'tail.next' to 'current.left' and move 'tail' to 'tail.next'.
7. We will then check if 'current.right' is not null. If it is not, we will set 'tail.next' to 'current.right' and move 'tail' to 'tail.next'.
8. After processing both children, we will move 'current' to 'current.next' to process the next node at the current level.
9. After processing all nodes at the current level, we will move 'current' to 'dummy.next', which points to the first node of the next level.
10. Finally, we will return the root node, which now has its next pointers populated.


Code:

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Node current = root;
        
        while (current != null) {
            Node dummy = new Node(0);
            Node tail = dummy;
            
            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }
                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }
                current = current.next;
            }
            
            current = dummy.next;
        }
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node exactly once.

Space Complexity: O(1), since we are using only a constant amount of extra space for the dummy node and tail pointer. The next pointers are being modified in place, so we do not need any additional data structures to store the nodes at each level.

*/