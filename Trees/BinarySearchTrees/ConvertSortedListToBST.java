// Problem Link: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

/*

Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]

Example 2:
Input: head = []
Output: []


Approach:

1. First, we need to find the middle element of the linked list, which will be the root of the BST. 
We can use the slow and fast pointer technique to find the middle element efficiently.

2. Once we have the middle element, we can create a new TreeNode with that value.

3. We then recursively build the left subtree using the left half of the linked list and the right subtree using the right half of the linked list.

4. The base case for the recursion will be when the head of the linked list is null, in which case we return null.

5. Finally, we return the root of the constructed BST.


Code:

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;  // Find the middle element of the linked list
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);  // The middle element is the root of the BST
        
        if (prev != null) {  // If there is a left half, recursively build the left subtree
            prev.next = null; // Break the linked list
            root.left = sortedListToBST(head);
        }
        
        root.right = sortedListToBST(slow.next); // Recursively build the right subtree
        
        return root;
    }
}


Time Complexity: O(n), where n is the number of nodes in the linked list. 
We visit each node once to find the middle and construct the BST.

Space Complexity: O(log n) on average, due to the recursive stack space used for constructing the BST.

*/