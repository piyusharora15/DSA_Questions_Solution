// Problem Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array

/*

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]

Approach: Using Binary Search.

1. First, we will find the first occurrence of the target using binary search.
2. Then, we will find the last occurrence of the target using binary search.
3. If the target is not found, we will return [-1, -1].
4. If the target is found, we will return the indices of the first and last occurrences.


Dry Run:
Input: nums = [5,7,7,8,8,10], target = 8
1. First Occurrence:
   - Initialize left = 0, right = 5 (length of nums - 1)
   - mid = (0 + 5) / 2 = 2, nums[mid] = 7 < target, move left to mid + 1 = 3
   - mid = (3 + 5) / 2 = 4, nums[mid] = 8 == target, check if it's the first occurrence
   - Since mid > left and nums[mid - 1] != target, we found the first occurrence at index 3.

2. Last Occurrence:
   - Initialize left = 0, right = 5 (length of nums - 1)
   - mid = (0 + 5) / 2 = 2, nums[mid] = 7 < target, move left to mid + 1 = 3
   - mid = (3 + 5) / 2 = 4, nums[mid] = 8 == target, check if it's the last occurrence
   - Since mid < right and nums[mid + 1] != target, we found the last occurrence at index 4.

Output: [3,4]

Time Complexity: O(log n) for both first and last occurrence searches, so overall O(log n).

Space Complexity: O(1) since we are using constant space.

*/

// Code:

class FindFirstLastPositionOfElement {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirstOccurrence(nums, target);
        int last = findLastOccurrence(nums, target);
        return new int[]{first, last};
    }

    private int findFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return -1;
    }

    private int findLastOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                }
                left = mid + 1;
            }
        }
        return -1;
    }
}