// Problem Link: https://leetcode.com/problems/partition-list?envType=problem-list-v2&envId=linked-list

/*

Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

Approach:
1. First, we will create two dummy nodes, one for the "less than x" partition and another for the "greater than or equal to x" partition.
2. We will iterate through the original linked list and for each node, we will check its value against x.
3. If the node's value is less than x, we will append it to the "less than x" partition. Otherwise, we will append it to the "greater than or equal to x" partition.
4. After processing all nodes, we will connect the two partitions together by linking the end of the "less than x" partition to the head of the "greater than or equal to x" partition.
5. Finally, we will return the head of the "less than x" partition, which will be the new head of the modified linked list.


Dry Run:
Input: head = [1,4,3,2,5,2], x = 3
1. Initialize two dummy nodes: lessHead and greaterHead.
2. Initialize two pointers: less and greater, pointing to lessHead and greaterHead respectively.
3. Start iterating through the original linked list:
   - Node 1: value = 1 < 3, append to less partition.
   - Node 4: value = 4 >= 3, append to greater partition.
   - Node 3: value = 3 >= 3, append to greater partition.
   - Node 2: value = 2 < 3, append to less partition.
   - Node 5: value = 5 >= 3, append to greater partition.
   - Node 2: value = 2 < 3, append to less partition.
4. After processing all nodes, connect the two partitions:
   - less.next = greaterHead.next (linking the end of less partition to the start of greater partition)
5. Return lessHead.next as the new head of the modified linked list.

Code:

class PartitionList {
    public ListNode partition(ListNode head, int x) {
    
        ListNode lessHead = new ListNode(0);        // Create two dummy nodes for the two partitions
        ListNode greaterHead = new ListNode(0);
        
        ListNode less = lessHead;      // Pointers to build the two partitions
        ListNode greater = greaterHead;
        
        while (head != null) {   // Iterate through the original linked list
            if (head.val < x) {  // Append to the less partition
                less.next = head;
                less = less.next;
            } else {    // Append to the greater partition
                greater.next = head;
                greater = greater.next;
            }
            head = head.next; // Move to the next node
        }    // Connect the two partitions
    
        less.next = greaterHead.next; // Link the end of less partition to the start of greater partition
        greater.next = null; // Terminate the greater partition
        
        return lessHead.next; // Return the head of the modified linked list
    }
}


Time Complexity: O(n), where n is the number of nodes in the linked list. We traverse the list once.

Space Complexity: O(1), as we are using a constant amount of extra space for the dummy nodes and pointers.

*/