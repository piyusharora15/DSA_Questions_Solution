// Problem Link: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array?envType=daily-question&envId=2026-07-27

/*

Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).

Example 1:
Input: nums = [3,4,5,2]
Output: 12 
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12. 

Example 2:
Input: nums = [1,5,4,5]
Output: 16
Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.

Example 3:
Input: nums = [3,7]
Output: 12


Approach: Using Max heap.

1. First, we will create a max heap and add all the elements of the array into it.
2. Then, we will pop the two largest elements from the heap and calculate the maximum product using the formula (nums[i]-1)*(nums[j]-1).
3. Finally, we will return the calculated maximum product.


Dry Run:
Input: nums = [3,4,5,2]
1. Create a max heap and add all elements: [5, 4, 3, 2]
2. Pop the two largest elements: 5 and 4
3. Calculate the maximum product: (5-1)*(4-1) = 4*3 = 12
4. Return 12


Time Complexity: O(n log n), where n is the number of elements in the array. This is because we are adding all elements to the heap, which takes O(n log n) time.

Space Complexity: O(n), where n is the number of elements in the array. This is because we are storing all elements in the heap.

*/

// Code:

import java.util.Collections;
import java.util.PriorityQueue;

class MaxProductOfTwoElements {
    public int maxProduct(int[] nums) {
        // Create a max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all elements to the max heap
        for (int num : nums) {
            maxHeap.add(num);
        }
        
        // Pop the two largest elements from the heap
        int firstMax = maxHeap.poll();
        int secondMax = maxHeap.poll();
        
        // Calculate the maximum product
        return (firstMax - 1) * (secondMax - 1);
    }
}