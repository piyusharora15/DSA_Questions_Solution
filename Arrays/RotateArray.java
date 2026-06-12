// Problem Link: https://leetcode.com/problems/rotate-array?envType=study-plan-v2&envId=top-interview-150

/*

Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]


Approach 1: Brute Force.

1. First we will rotate the array by 1 step to the right, and we will do this k times.
2. To rotate the array by 1 step to the right, we will store the last element of the array in a variable, and then we will shift all the elements of the array to the right by 1 step, and then we will place the last element at the first position of the array.
3. We will repeat this process k times.


Code:

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k is greater than n
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1]; // Store the last element

            for (int j = n - 1; j > 0; j--) {  // Shift all elements to the right by 1 step
                nums[j] = nums[j - 1];
            }
            nums[0] = last; // Place the last element at the first position
        }
    }
}


Time Complexity: O(n * k) - We are rotating the array k times, and each rotation takes O(n) time.

Space Complexity: O(1) - We are using only a constant amount of extra space to store the last element during the rotation process.


Approach 2: Using Extra Array.

1. First we will create a new array of the same size as the input array.
2. We will iterate through the input array and for each element, we will calculate its new position after rotation and place it in the new array.
3. Finally, we will copy the elements from the new array back to the original array.


Code:

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k is greater than n
        int[] rotated = new int[n]; // Create a new array to store the rotated elements
        for (int i = 0; i < n; i++) {
            int newPosition = (i + k) % n; // Calculate the new position for each element
            rotated[newPosition] = nums[i]; // Place the element in the new array
        }

        for (int i = 0; i < n; i++) {   // Copy the elements from the rotated array back to the original array
            nums[i] = rotated[i];
        }
    }
}


Time Complexity: O(n) - We are iterating through the array twice, once to place the elements in the new array and once to copy them back to the original array.

Space Complexity: O(n) - We are using an extra array of the same size as the input array to store the rotated elements.


Approach 3: Using Reversal.

1. First we will reverse the entire array.
2. Then we will reverse the first k elements of the array.
3. Finally, we will reverse the remaining n-k elements of the array.
4. This will give us the rotated array.


Time Complexity: O(n) - We are reversing the array three times, and each reversal takes O(n) time.

Space Complexity: O(1) - We are reversing the array in place, so we are not using any extra space.

*/

// Code:

class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k is greater than n
        reverse(nums, 0, n - 1); // Reverse the entire array
        reverse(nums, 0, k - 1); // Reverse the first k elements
        reverse(nums, k, n - 1); // Reverse the remaining n-k elements
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}