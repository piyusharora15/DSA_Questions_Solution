// Problem Link: https://leetcode.com/problems/linked-list-cycle

/*

Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Brute Force Approach: Using HashMap.

1. First, we can use a HashMap to store the nodes we have already visited.
2. We can traverse the linked list and for each node, we check if it is already present in the HashMap. If it is, then we have found a cycle and we return true. If it is not present, we add it to the HashMap and continue traversing the linked list.


Code:

import java.util.HashMap;

public class LLCycle {
    public boolean hasCycle(ListNode head) {
        ListNode temp = head;
        HashMap<ListNode,Integer> nodeMap = new HashMap<>();
        while(temp != null){
            if(nodeMap.containsKey(temp)){
                return true;
            }
            nodeMap.put(temp,1);
            temp = temp.next;
        }
        return false;
    }
}

Time Complexity: O(n), we traverse the linked list once, where n is the number of nodes in the linked list.

Space Complexity: O(n), we use a HashMap to store the nodes we have already visited, which can take up to n space in the worst case.


Optimal Approach: Using Floyd's Cycle Detection Algorithm (Tortoise and Hare Algorithm)
1. First, we can use two pointers, slow and fast, to traverse the linked list. The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
2. If there is a cycle in the linked list, the fast pointer will eventually catch up to the slow pointer. If the fast pointer reaches the end of the linked list (i.e., it becomes null), then there is no cycle in the linked list.
3. We can return true if the slow and fast pointers meet, and false if the fast pointer reaches the end of the linked list.


Code:

public class LLCycle {

    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

Time Complexity: O(n), we traverse the linked list once, where n is the number of nodes in the linked list.

Space Complexity: O(1), we only use two pointers, slow and fast, which take up constant space.

*/

