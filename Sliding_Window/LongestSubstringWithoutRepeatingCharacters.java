// Problem Link: https://leetcode.com/problems/longest-substring-without-repeating-characters?envType=study-plan-v2&envId=top-interview-150

/*

Company Tags: Amazon, Apple, Facebook, Google, Microsoft, Uber.

Given a string s, find the length of the longest substring without duplicate characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 
Constraints:
0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.


Naive Approach:

1. First, we can generate all possible substrings of the given string and check if they contain all unique characters.
2. We can keep track of the maximum length of such substrings.


Code:

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substr = s.substring(i, j);
                if (hasAllUniqueCharacters(substr)) {
                    maxLength = Math.max(maxLength, substr.length());
                }
            }
        }
        return maxLength;
    }
    private boolean hasAllUniqueCharacters(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }
}

Time Complexity: O(n^3), where n is the length of the string. 
We generate O(n^2) substrings and checking for unique characters takes O(n) time in the worst case.

Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string.


Optimal Approach 1: Sliding Window and HashSet.

1. We will use a sliding window approach to keep track of the current substring without repeating characters.
2. We will use a HashSet to store the characters in the current window.
3. We will expand the window by moving the right pointer and shrink it by moving the left pointer when we encounter a repeating character.
4. We will keep track of the maximum length of the substring without repeating characters during this process.


Code:

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}

Time Complexity: O(n), where n is the length of the string. 
Each character is visited at most twice (once by the right pointer and once by the left pointer).

Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string. 
In the worst case, we may need to store all characters in the substring. 


Optimal Approach 2: Sliding Window and HashMap.

1. We will use a sliding window approach similar to the previous method, but instead of a HashSet, we will use a HashMap to store the last index of each character.
2. When we encounter a repeating character, we can directly jump the left pointer to the index right after the last occurrence of that character, which can potentially skip multiple characters at once and improve efficiency.
3. We will keep track of the maximum length of the substring without repeating characters during this process.

Time Complexity: O(n), where n is the length of the string. Each character is visited at most once.

Space Complexity: O(min(m, n)), where m is the size of the character set and n is the length of the string. 
In the worst case, we may need to store all characters in the substring.

*/

// Code:

import java.util.*;
class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> indexMap = new HashMap<>();

        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            if (indexMap.containsKey(ch)) {
                left = Math.max(left, indexMap.get(ch) + 1);
            }
            indexMap.put(ch, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
