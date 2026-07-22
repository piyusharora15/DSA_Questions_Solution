// Problem Link: https://leetcode.com/problems/reverse-linked-list?envType=problem-list-v2&envId=wravg9od

/*

Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []


Approach 1: Using Recursion.
1. First, we will check if the head is null or if the head's next is null. If either of these conditions is true, we will return the head as it is already reversed.
2. If the head is not null and the head's next is not null, we will call the reverseList function recursively with the head's next as the argument. This will reverse the rest of the list and return the new head of the reversed list.
3. After the recursive call, we will set the next node's next pointer to point to the current head, effectively reversing the link between the two nodes.
4. Finally, we will set the current head's next pointer to null to avoid a cycle in the list and return the new head of the reversed list.

Code:

class ReverseLL {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {  // Base case: if the list is empty or has only one node
            return head;
        }
        
        ListNode newHead = reverseList(head.next); // Recursive call to reverse the rest of the list
        
        head.next.next = head; // Reverse the link between the current node and the next node
        head.next = null; // Set the current node's next to null
        
        return newHead; // Return the new head of the reversed list
    }
}

Time Complexity: O(n), where n is the number of nodes in the linked list. We visit each node once.

Space Complexity: O(n), due to the recursive call stack. In the worst case, the recursion depth can go up to n, where n is the number of nodes in the linked list.


Approach 2: Using Iteration.

1. First, we will initialize three pointers: prev, curr, and next. The prev pointer will be initialized to null, the curr pointer will be initialized to the head of the list, and the next pointer will be used to store the next node in the list.
2. We will iterate through the list, updating the pointers to reverse the links between nodes.
3. Finally, we will return the new head of the reversed list.

Dry Run Example:
Input: head = [1,2,3,4,5]

First Iteration:
prev = null, curr = 1, next = 2
Step 1: Set curr.next to prev (1.next = null)
Step 2: Move prev to curr (prev = 1)
Step 3: Move curr to next (curr = 2)
Step 4: Set next to curr.next (next = 3)

Second Iteration:
prev = 1, curr = 2, next = 3
Step 1: Set curr.next to prev (2.next = 1)
Step 2: Move prev to curr (prev = 2)
Step 3: Move curr to next (curr = 3)
Step 4: Set next to curr.next (next = 4)

Third Iteration:
prev = 2, curr = 3, next = 4
Step 1: Set curr.next to prev (3.next = 2)
Step 2: Move prev to curr (prev = 3)
Step 3: Move curr to next (curr = 4)
Step 4: Set next to curr.next (next = 5)

Fourth Iteration:
prev = 3, curr = 4, next = 5
Step 1: Set curr.next to prev (4.next = 3)
Step 2: Move prev to curr (prev = 4)
Step 3: Move curr to next (curr = 5)
Step 4: Set next to curr.next (next = null)

Fifth Iteration:
prev = 4, curr = 5, next = null
Step 1: Set curr.next to prev (5.next = 4)
Step 2: Move prev to curr (prev = 5)
Step 3: Move curr to next (curr = null)
Step 4: Set next to curr.next (next = null)

Final Result:
prev = 5, curr = null, next = null
The new head of the reversed list is prev, which is node with value 5.


Code:

class ReverseLL {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; // Initialize prev to null
        ListNode curr = head; // Initialize curr to head
        ListNode next = null; // Initialize next to null
        
        while (curr != null) { // Iterate through the list
            next = curr.next; // Store the next node
            curr.next = prev; // Reverse the link
            prev = curr; // Move prev to curr
            curr = next; // Move curr to next
        }
        
        return prev; // Return the new head of the reversed list
    }
}

Time Complexity: O(n), where n is the number of nodes in the linked list. We visit each node once.

Space Complexity: O(1), as we are using only a constant amount of extra space for the pointers.

*/