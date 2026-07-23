// Problem Link: https://leetcode.com/problems/middle-of-the-linked-list

/*

Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.


Approach: Using two pointers.

1. First, we will create two pointers, slow and fast, both initialized to the head of the linked list.
2. We will move the fast pointer two steps at a time and the slow pointer one step at a time.
3. When the fast pointer reaches the end of the linked list, the slow pointer will be at the middle node.
4. Finally, we will return the slow pointer as the middle node of the linked list.

Dry Run:
Input: head = [1,2,3,4,5]
1. Initialize slow and fast pointers to head (node with value 1).
2. Move fast pointer two steps and slow pointer one step until fast pointer reaches the end.
3. At this point, slow pointer will be at the middle node (node with value 3).
4. Return the slow pointer.


Code:

class MiddleOfLL {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}


Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse the linked list once.

Space Complexity: O(1), as we are using only two pointers and no additional data structures.

*/