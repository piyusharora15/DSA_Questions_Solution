// Problem Link: https://leetcode.com/problems/two-sum/


/*

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

Brute Force Approach:
1. Iterate through each element x and find if there is another value that equals to target - x.
2. Take two nested loops to check for each pair of numbers if they add up to the target.

Time Complexity: O(n^2) - We traverse the array twice.
Space Complexity: O(1) - We are not using any extra space.


Optimal Approach: Using Hash Map.

1. First, we create a hash map to store the numbers and their indices.
2. Then, we iterate through the array and for each number, we calculate its complement by subtracting it from the target.
3. We check if the complement exists in the hash map. If it does, we return the indices of the current number and its complement.
4. If the complement does not exist, we add the current number and its index to the hash map.
5. If we finish iterating through the array without finding a pair, we return [-1, -1] to indicate that no solution was found.


Time Complexity: O(n) - We traverse the array once.
Space Complexity: O(n) - In the worst case, we could be storing all n elements in the hash map.

*/

// Code:
import java.util.HashMap;
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
}
