// Problem Link: https://leetcode.com/problems/sort-colors

/*

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]


Approach 1: Using Counting Sort.

1. First, we count the number of occurrences of each color (0s, 1s, and 2s) in the input array.
2. Then, we overwrite the input array with the counted values in the correct order (0s first, followed by 1s, and then 2s).


Code:

class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {  // Count the occurrences of each color
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        int index = 0;  // Overwrite the input array with the counted values
        for (int i = 0; i < count0; i++) {
            nums[index++] = 0;
        }
        for (int i = 0; i < count1; i++) {
            nums[index++] = 1;
        }
        for (int i = 0; i < count2; i++) {
            nums[index++] = 2;
        }
    }
}

Time Complexity: O(n) - We traverse the array twice, once for counting and once for overwriting.

Space Complexity: O(1) - We use a constant amount of extra space for counting the occurrences of each color.


Approach 2: Using the Dutch National Flag Algorithm (Three Pointers).

1. First we initialize three pointers: `low` for the next position of 0, `mid` for the current element being processed, and `high` for the next position of 2.
2. We iterate through the array with the `mid` pointer:
   - If the current element is 0, we swap it with the element at the `low` pointer and move both `low` and `mid` pointers forward.
   - If the current element is 1, we simply move the `mid` pointer forward.
   - If the current element is 2, we swap it with the element at the `high` pointer and move the `high` pointer backward.
3. We continue this process until the `mid` pointer exceeds the `high` pointer.


Time Complexity: O(n) - We traverse the array at most once.

Space Complexity: O(1) - We use a constant amount of extra space for the three pointers.


*/

// Code:

class SortColors {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}