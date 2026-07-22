// Problem Link: https://leetcode.com/problems/reverse-linked-list-ii?envType=problem-list-v2&envId=wravg9od

/*

Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

Approach: Using Iteration.

1. First, we will create a dummy node and set its next pointer to the head of the list. This will help us handle edge cases where the head of the list is part of the reversed section.
2. We will initialize three pointers: prev, curr, and next. The prev pointer will be initialized to the dummy node, the curr pointer will be initialized to the head of the list, and the next pointer will be used to store the next node in the list.
3. We will move the prev pointer to the node just before the left position and the curr pointer to the left position.
4. We will iterate through the list from left to right, updating the pointers to reverse the links between nodes in the specified range.
5. Finally, we will return the new head of the list, which is the next pointer of the dummy node.


Code:

class ReverseLL2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {  // Base case: if the list is empty or left equals right
            return head;
        }
        
        ListNode dummy = new ListNode(0); // Create a dummy node
        dummy.next = head; // Set dummy's next to head
        ListNode prev = dummy; // Initialize prev to dummy
        
        for (int i = 0; i < left - 1; i++) {  // Move prev to the node just before the left position
            prev = prev.next;
        }
        
        ListNode curr = prev.next; // Initialize curr to the left position
        
        for (int i = 0; i < right - left; i++) {  // Reverse the nodes from left to right
            ListNode next = curr.next; // Store the next node
            curr.next = next.next; // Reverse the link
            next.next = prev.next; // Connect next to the reversed part
            prev.next = next; // Connect prev to next
        }
        
        return dummy.next; // Return the new head of the list
    }
}


Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse the list once to reach the left position and then reverse the nodes between left and right.

Space Complexity: O(1), as we are using a constant amount of extra space for the pointers.

*/