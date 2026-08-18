// Problem Link: https://leetcode.com/problems/minimum-size-subarray-sum

/*

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. 
If there is no such subarray, return 0 instead.

Example 1:Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Optimal Approach: Sliding Window Technique.

1. First, we will initialize two pointers, i and j, both set to 0. We will also maintain a variable sum to keep track of the current sum of the subarray and a variable minLen to store the minimum length found so far.
2. We will iterate through the array using the j pointer. For each element, we will add it to the sum.
3. If the sum is greater than or equal to the target, we will update minLen with the length of the current subarray (j - i + 1) and then move the i pointer to the right, subtracting nums[i] from sum, until the sum is less than target.
4. Finally, we will return minLen if it has been updated; otherwise, we will return 0.

Dry Run:
Input: target = 7, nums = [2,3,1,2,4,3]
- Initialize i = 0, j = 0, sum = 0, minLen = 7
- j = 0, sum = 2, minLen = 7
- j = 1, sum = 5, minLen = 7
- j = 2, sum = 6, minLen = 7
- j = 3, sum = 8, minLen = 4, sum = 6, i = 1
- j = 4, sum = 10, minLen = 4, sum = 7, i = 2
- j = 5, sum = 10, minLen = 4, sum = 9, i = 3
- j = 5, sum = 9, minLen = 3, sum = 7, i = 4
- j = 5, sum = 7, minLen = 2, sum = 3, i = 5

Output: 2

Time Complexity: O(n), where n is the length of the input array.
We are iterating through the array once with the j pointer, and the i pointer only moves forward, so the overall time complexity is linear.

Space Complexity: O(1), as we are using a constant amount of extra space for variables like i, j, sum, and minLen.


*/ 

// Code:
class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i=0,j=0,sum=0;
        int minLen = n+1;
        while(j < n){
            sum += nums[j];
            while(sum >= target){
                minLen = Math.min(minLen,j-i+1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minLen == n+1 ? 0 : minLen;
    }
}

