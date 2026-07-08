/*

Problem Link: https://leetcode.com/problems/longest-consecutive-sequence?envType=problem-list-v2&envId=auswip1r

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Explanation: The longest consecutive elements sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8]. Therefore its length is 9.

Example 3:
Input: nums = [1,0,1,2]
Output: 3
Explanation: The longest consecutive elements sequence is [0, 1, 2]. Therefore its length is 3.


Approach: Using HashSet.

1. First, we will add all the elements of the array into a HashSet. This allows for O(1) time complexity for lookups.
2. Next, we will iterate through each element in the array. 
For each element, we will check if it is the start of a sequence by checking if the previous number (element - 1) is not in the HashSet.
3. If it is the start of a sequence, we will initialize a variable to keep track of the current sequence length and increment it while the next consecutive numbers (element + 1, element + 2, ...) are found in the HashSet.
4. We will keep track of the maximum sequence length found during the iteration and return it at the end.


Dry Run:
Input: nums = [100,4,200,1,3,2]
1. Add all elements to HashSet: {100, 4, 200, 1, 3, 2}
2. Iterate through each element:
    - For 100: 99 is not in HashSet, start sequence length = 1 (only 100)
    - For 4: 3 is in HashSet, skip
    - For 200: 199 is not in HashSet, start sequence length = 1 (only 200)
    - For 1: 0 is not in HashSet, start sequence length = 1 (only 1)
    - For 3: 2 is in HashSet, skip
    - For 2: 1 is in HashSet, skip
3. The longest sequence found is [1, 2, 3, 4] with length 4.
4. Return 4 as the output.

Time Complexity: O(n) - We traverse the array once to add elements to the HashSet and then traverse it again to find the longest sequence.

Space Complexity: O(n) - We use a HashSet to store the elements of the array.

*/

// Code:

import java.util.HashSet;
import java.util.Set;
class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            // Check if it's the start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
