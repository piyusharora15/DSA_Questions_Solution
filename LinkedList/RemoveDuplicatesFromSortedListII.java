// Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii?envType=problem-list-v2&envId=linked-list

/*

Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Input: head = [1,1,1,2,3]
Output: [2,3]

Approach: Using Two Pointers.

1. Create a dummy node and point its next to the head of the linked list. This helps in handling edge cases where the head itself might be a duplicate.
2. Initialize a pointer 'prev' to the dummy node and a pointer 'current' to the head of the linked list.
3. Traverse the linked list using a while loop until 'current' is not null.
4. Inside the loop, check if 'current' has a duplicate by comparing its value with the value of 'current.next'.
   - If they are equal, it means we have found a duplicate. In this case, we need to skip all nodes with the same value. We can do this by moving 'current' forward until we reach a node with a different value.
   - After skipping the duplicates, we update 'prev.next' to point to 'current.next', effectively removing the duplicates from the list.
   - If they are not equal, it means we have found a unique value. In this case, we can simply move both 'prev' and 'current' pointers forward.
5. Continue this process until we reach the end of the linked list.

Dry Run:
Input: head = [1,2,3,3,4,4,5]
- Create a dummy node with value 0 and point its next to the head of the linked list (value 1).
- Initialize 'prev' to the dummy node and 'current' to the head of the linked list (value 1).
- Check if 'current' (1) is equal to 'current.next' (2).
- They are not equal, so we move both 'prev' and 'current' to the next nodes (prev points to 1, current points to 2).
- Check if 'current' (2) is equal to 'current.next' (3).
- They are not equal, so we move both 'prev' and 'current' to the next nodes (prev points to 2, current points to 3).
- Check if 'current' (3) is equal to 'current.next' (3).
- They are equal, so we skip all nodes with value 3 by moving 'current' forward until we reach a node with a different value (current points to 4).
- Update 'prev.next' to point to 'current.next' (which points to the node with value 4), effectively removing the duplicates (3) from the list.
- Now, 'prev' points to 2 and 'current' points to 4.
- Check if 'current' (4) is equal to 'current.next' (4).
- They are equal, so we skip all nodes with value 4 by moving 'current' forward until we reach a node with a different value (current points to 5).
- Update 'prev.next' to point to 'current.next' (which points to null), effectively removing the duplicates (4) from the list.
- Now, 'prev' points to 2 and 'current' points to 5.
- Check if 'current' (5) is equal to 'current.next' (null).
- They are not equal, so we move both 'prev' and 'current' to the next nodes (prev points to 5, current points to null).
- The while loop ends since 'current' is null.
Output: The modified linked list is [1,2,5].

Code:

class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0); // Create a dummy node
        dummy.next = head;
        ListNode prev = dummy; // Pointer to the last node before the sublist of duplicates
        ListNode current = head; // Pointer to traverse the list
        
        while (current != null) {
            
            if (current.next != null && current.val == current.next.val) { // Check if current node has duplicates
                
                while (current.next != null && current.val == current.next.val) { // Skip all nodes with the same value
                    current = current.next;
                }
                prev.next = current.next;  // Remove duplicates by linking prev to the next unique node
            } else {
                prev = prev.next; // Move prev pointer to the current node if it's unique
            }
            current = current.next; // Move current pointer forward
        }
        
        return dummy.next; // Return the modified list starting from the first unique node
    }
}

Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse the list once.

Space Complexity: O(1), as we are using only a constant amount of extra space for pointers.

*/
