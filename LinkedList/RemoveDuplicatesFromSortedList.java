// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list

/*

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Input: head = [1,1,2]
Output: [1,2]

Input: head = [1,1,2,3,3]
Output: [1,2,3]

Approach: Using Two Pointers.

1. Initialize a pointer 'current' to the head of the linked list.
2. Traverse the linked list using a while loop until 'current' is not null and 'current.next' is not null.
3. Inside the loop, check if the value of 'current' is equal to the value of 'current.next'.
   - If they are equal, it means we have found a duplicate. In this case, we need to remove the duplicate node by updating the 'next' pointer of 'current' to skip the duplicate node. This can be done by setting 'current.next' to 'current.next.next'.
   - If they are not equal, it means we have found a unique value. In this case, we can simply move the 'current' pointer to the next node by setting 'current' to 'current.next'.
4. Continue this process until we reach the end of the linked list.

Dry Run:
Input: head = [1,1,2]
- Initialize 'current' to the head of the linked list (value 1).
- Check if 'current' (1) is equal to 'current.next' (1). They are equal, so we remove the duplicate by setting 'current.next' to 'current.next.next' (which points to the node with value 2).
- Now, 'current' still points to the node with value 1, and 'current.next' points to the node with value 2.
- Check if 'current' (1) is equal to 'current.next' (2).
- They are not equal, so we move 'current' to 'current.next' (which points to the node with value 2).
- Now, 'current' points to the node with value 2, and 'current.next' is null.
- The while loop ends since 'current.next' is null.
Output: The modified linked list is [1,2].


Code:

class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode current = head;
        
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {  // Duplicate found, remove it
                current.next = current.next.next;
            } else {        // Move to the next unique element
                current = current.next;
            }
        }
        
        return head;
    }
}

Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse the list once.

Space Complexity: O(1), as we are using only a constant amount of extra space for the pointer 'current'.

*/
