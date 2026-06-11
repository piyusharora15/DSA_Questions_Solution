// Problem Link: https://leetcode.com/problems/majority-element

/*

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3

Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Approach 1: HashMap.

1. First we will create a HashMap to store the frequency of each element in the array.
2. We will iterate through the array and for each element, we will update its frequency in the HashMap.
3. After we have the frequency of each element, we will iterate through the HashMap to find the element that has a frequency greater than n/2, where n is the size of the array.
4. We will return that element as the majority element.


Code:

class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {  // Count the frequency of each element
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {  // Find the majority element
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }

        return -1;  // This line will never be reached since the majority element always exists
    }
}

Time Complexity: O(n) - We traverse the array once to count frequencies and then traverse the HashMap to find the majority element.

Space Complexity: O(n) - In the worst case, all elements in the array are different, and we will store all of them in the HashMap.


Approach 2: Boyer-Moore Voting Algorithm.

1. We will maintain two variables: `candidate` to store the current candidate for the majority element and `count` to store the count of the candidate.
2. We will iterate through the array and for each element, we will check if it is the same as the current candidate. 
If it is, we will increment the count. If it is not, we will decrement the count.
3. If the count becomes zero, we will update the candidate to the current element and reset the count to 1.
4. After we have iterated through the array, the candidate will be the majority element, and we will return it.


Time Complexity: O(n) - We traverse the array once to find the majority element.

Space Complexity: O(1) - We are using only a constant amount of extra space to store the candidate and count.


*/


// Code:

class MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;  // Update candidate
                count = 1;        // Reset count
            } else if (num == candidate) {
                count++;          // Increment count if current element is same as candidate
            } else {
                count--;          // Decrement count if current element is different from candidate
            }
        }

        return candidate;  // The candidate is the majority element
    }
}