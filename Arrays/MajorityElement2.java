// Problem Link: https://leetcode.com/problems/majority-element-ii?envType=problem-list-v2&envId=array

/*

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]


Approach 1: Using HashMap.

1. First we will create a HashMap to store the frequency of each element in the array.
2. We will iterate through the array and update the frequency of each element in the HashMap.
3. After we have the frequency of each element, we will iterate through the HashMap and check if the frequency of any element is greater than n/3.
4. If it is, we will add that element to our result list.
5. Finally, we will return the result list. 


Code:

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {  // Count the frequency of each element
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int n = nums.length;  // Check for elements that appear more than n/3 times
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > n / 3) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}


Time Complexity: O(n) - We traverse the array once to count frequencies and then traverse the HashMap to find the majority elements.
Space Complexity: O(n) - In the worst case, we might store all elements in the HashMap if they are all unique.


Approach 2: Boyer-Moore Voting Algorithm.

1. First we will initialize two candidate variables and their corresponding count variables.
2. We will iterate through the array and update the candidates and their counts based on the current element.
3. After the first pass, we will have at most two candidates that could be the majority elements.
4. We will then reset the counts and iterate through the array again to count the occurrences of the candidates.
5. Finally, we will check if the counts of the candidates are greater than n/3 and add them to the result list if they are.


Time Complexity: O(n) - We traverse the array twice, once to find the candidates and once to count their occurrences.

Space Complexity: O(1) - We only use a constant amount of extra space for the candidates and their counts.

*/


// Code:

import java.util.ArrayList;
import java.util.List;
class MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int candidate1 = 0, candidate2 = 1; // Initialize candidates to different values
        int count1 = 0, count2 = 0;

        // First pass to find potential candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Reset counts for the second pass
        count1 = 0;
        count2 = 0;

        // Second pass to confirm the candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        int n = nums.length;
        if (count1 > n / 3) {
            result.add(candidate1);
        }
        if (count2 > n / 3) {
            result.add(candidate2);
        }

        return result;
    }
}