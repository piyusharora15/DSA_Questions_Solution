// Problem Link: https://leetcode.com/problems/linked-list-cycle-ii?envType=problem-list-v2&envId=wravg9od

/*

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Approach: Using Floyd's Cycle Detection Algorithm (Tortoise and Hare Algorithm).

1. First, we will use two pointers, slow and fast. The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
2. If there is a cycle in the linked list, the fast pointer will eventually meet the slow pointer. If there is no cycle, the fast pointer will reach the end of the list (null).
3. Once a cycle is detected (when slow and fast pointers meet), we can find the starting node of the cycle by initializing another pointer from the head of the list and moving both this pointer and the slow pointer one step at a time until they meet. The meeting point will be the starting node of the cycle.
4. If no cycle is detected, we return null.

Code:

class LLCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // No cycle
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { //Step 1: Detect cycle using Floyd's Cycle Detection Algorithm
            slow = slow.next; // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2

            if (slow == fast) { // Cycle detected
                ListNode entry = head; // Step 2: Find the starting node of the cycle
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry; // Starting node of the cycle
            }
        }

        return null; // No cycle detected
    }
}


Time Complexity: O(n), where n is the number of nodes in the linked list. In the worst case, we may need to traverse all nodes to detect a cycle and find the starting node of the cycle.

Space Complexity: O(1), as we are using only a constant amount of extra space for the pointers.

*/

