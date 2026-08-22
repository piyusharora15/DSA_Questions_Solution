/*

Problem Link: https://leetcode.com/problems/contiguous-array?envType=problem-list-v2&envId=wh88bf73

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Example 3:
Input: nums = [0,1,1,1,1,1,0,0,0]
Output: 6
Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.

Approach: Using HashMap and Prefix Sum.

1. First, we will convert all 0s in the array to -1s. This way, the problem of finding a contiguous subarray with equal number of 0s and 1s can be transformed into finding a contiguous subarray with a sum of 0.
2. We will use a HashMap to store the first occurrence of each prefix sum. The key will be the prefix sum, and the value will be the index at which this prefix sum was first seen.
3. We will iterate through the array, calculating the prefix sum as we go. If the prefix sum has been seen before, it means that the subarray between the previous index and the current index has a sum of 0 (equal number of 0s and 1s).
4. We will keep track of the maximum length of such subarrays found during the iteration.
5. Finally, we will return the maximum length found.


Dry Run:

Input: nums = [0,1,1,1,1,1,0,0,0]

First, we will convert all 0s to -1s:
nums = [-1, 1, 1, 1, 1, 1, -1, -1, -1]

Now, we will calculate the prefix sum and use a HashMap to track the first occurrence of each prefix sum:
- Initialize prefixSum = 0, maxLength = 0, and a HashMap to store prefix sums.
- Iterate through the array:
  - Update prefixSum by adding the current element.
  - If prefixSum is 0, it means the subarray from the beginning to the current index has a sum of 0.
  - If prefixSum has been seen before, calculate the length of the subarray and update maxLength if it's larger.
  - If prefixSum has not been seen before, store it in the HashMap with its index.

Index: 0, Element: -1, PrefixSum: -1, HashMap: {-1: 0}, MaxLength: 0
Index: 1, Element: 1, PrefixSum: 0, HashMap: {-1: 0}, MaxLength: 2 (subarray from index 0 to 1)
Index: 2, Element: 1, PrefixSum: 1, HashMap: {-1: 0, 0: 1}, MaxLength: 2
Index: 3, Element: 1, PrefixSum: 2, HashMap: {-1: 0, 0: 1, 1: 2}, MaxLength: 2
Index: 4, Element: 1, PrefixSum: 3, HashMap: {-1: 0, 0: 1, 1: 2, 2: 3}, MaxLength: 2
Index: 5, Element: 1, PrefixSum: 4, HashMap: {-1: 0, 0: 1, 1: 2, 2: 3, 3: 4}, MaxLength: 2
Index: 6, Element: -1, PrefixSum: 3, HashMap: {-1: 0, 0: 1, 1: 2, 2: 3, 3: 4}, MaxLength: 2
Index: 7, Element: -1, PrefixSum: 2, HashMap: {-1: 0, 0: 1, 1: 2, 2: 3, 3: 4}, MaxLength: 4 (subarray from index 3 to 7)
Index: 8, Element: -1, PrefixSum: 1, HashMap: {-1: 0, 0: 1, 1: 2, 2: 3, 3: 4}, MaxLength: 6 (subarray from index 2 to 8)


Time Complexity: O(n), where n is the length of the input array. We traverse the array once, and each operation (insertion and lookup) in the HashMap takes O(1) on average.

Space Complexity: O(n), where n is the length of the input array. In the worst case, we may store all prefix sums in the HashMap.

*/


// Code:

import java.util.HashMap;
import java.util.Map;

class ContiguousArray {
    public int findMaxLength(int[] nums) {
        // Convert 0s to -1s
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        // HashMap to store the first occurrence of each prefix sum
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // If prefixSum is 0, update maxLength
            if (prefixSum == 0) {
                maxLength = i + 1;
            }

            // If prefixSum has been seen before, calculate the length of the subarray
            if (prefixSumMap.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(prefixSum));
            } else {
                // Store the first occurrence of this prefix sum
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}