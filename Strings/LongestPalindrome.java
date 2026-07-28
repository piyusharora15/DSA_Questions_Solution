// Problem Link: https://leetcode.com/problems/longest-palindrome?envType=problem-list-v2&envId=dtka3dwv

/*

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome.

Example 1:
Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

Example 2:
Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.


Approach: Using HashMap.

1. First, we will create a HashMap to store the frequency of each character in the string.
2. Then, we will iterate through the HashMap and for each character, we will check if its frequency is even or odd.
3. If the frequency is even, we can use all of those characters to form a palindrome, so we will add the frequency to the length of the palindrome.
4. If the frequency is odd, we can use (frequency - 1) characters to form a palindrome, and we will add that to the length of the palindrome. We will also keep track of whether we have used an odd frequency character or not.
5. Finally, if we have used an odd frequency character, we can add 1 to the length of the palindrome to account for the center character.
6. Return the length of the longest palindrome.


Dry Run:
Input: s = "abccccdd"
map = {a: 1, b: 1, c: 4, d: 2}
Iterating through the map:
a: frequency = 1 (odd), length = 0, oddUsed = true
b: frequency = 1 (odd), length = 0, oddUsed = true
c: frequency = 4 (even), length = 4, oddUsed = true
d: frequency = 2 (even), length = 6, oddUsed = true
Since oddUsed is true, we can add 1 to the length of the palindrome.
Output: 7

Time Complexity: O(n), where n is the length of the string. We are iterating through the string once to build the HashMap and then iterating through the HashMap to calculate the length of the palindrome.

Space Complexity: O(1), since the HashMap will have at most 52 entries (26 lowercase + 26 uppercase letters).

*/

// Code:

import java.util.HashMap;
import java.util.Map;

class LongestPalindrome {
    public int longestPalindrome(String s) {
        // Create a HashMap to store the frequency of each character
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean oddUsed = false;

        // Iterate through the HashMap to calculate the length of the palindrome
        for (int freq : map.values()) {
            if (freq % 2 == 0) {
                length += freq; // Use all characters if frequency is even
            } else {
                length += freq - 1; // Use (frequency - 1) characters if frequency is odd
                oddUsed = true; // Mark that we have used an odd frequency character
            }
        }

        // If we have used an odd frequency character, we can add 1 to the length of the palindrome
        if (oddUsed) {
            length++;
        }

        return length;
    }
}