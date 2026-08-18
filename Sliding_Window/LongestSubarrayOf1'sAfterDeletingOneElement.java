// Problem Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element?envType=problem-list-v2&envId=wh88bf73

/*

Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

Example 1:
Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

Example 2:
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

Example 3:
Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Approach: Using Sliding Window Technique.

1. First, we will initialize two pointers, left and right, both set to 0. We will also maintain a count of zeros in the current window.
2. We will iterate through the array using the right pointer. For each element, if it is a zero, we will increment the count of zeros.
3. If the count of zeros exceeds 1, we will move the left pointer to the right until the count of zeros is less than or equal to 1.
4. During each iteration, we will calculate the length of the current window (right - left) and update the maximum length found so far.
5. Finally, we will return the maximum length found.

Dry Run: 
Input: nums = [1,1,0,1]
- Initialize left = 0, right = 0, zeroCount = 0, maxLength = 0
- right = 0, nums[right] = 1, zeroCount = 0, maxLength = 0
- right = 1, nums[right] = 1, zeroCount = 0, maxLength = 1
- right = 2, nums[right] = 0, zeroCount = 1, maxLength = 2
- right = 3, nums[right] = 1, zeroCount = 1, maxLength = 3
Output: 3


Time Complexity: O(n), where n is the length of the input array. 
We are iterating through the array once with the right pointer, and the left pointer only moves forward, so the overall time complexity is linear.

Space Complexity: O(1), as we are using a constant amount of extra space for variables like left, right, zeroCount, and maxLength.


*/

// Code:

class LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int left = 0, right = 0;
        int zeroCount = 0;
        int maxLength = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left);
            right++;
        }

        return maxLength;
    }
}