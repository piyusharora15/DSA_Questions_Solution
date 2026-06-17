// Problem Link: https://leetcode.com/problems/add-two-numbers?envType=problem-list-v2&envId=linked-list

/*

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Approach: Using Elementary Math.

1. First, we will create a dummy node to store the result linked list. We will also create a variable to keep track of the carry.

2. We will iterate through both linked lists until we reach the end of both. 
In each iteration, we will add the values of the current nodes of both linked lists along with the carry from the previous iteration.

3. We will calculate the new carry and the value to be stored in the current node of the result linked list.

4. We will create a new node with the calculated value and link it to the result linked list.

5. After the loop, if there is any carry left, we will create a new node with the carry value and link it to the result linked list.

6. Finally, we will return the next node of the dummy node, which is the head of the result linked list.


Time Complexity: O(max(m, n)), where m and n are the lengths of the two linked lists. We traverse both linked lists once.

Space Complexity: O(max(m, n)), where m and n are the lengths of the two linked lists. In the worst case, we may need to create a new node for each digit in the result linked list.

*/


// Code:

class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }
}