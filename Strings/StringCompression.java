// Problem Link: https://leetcode.com/problems/string-compression?envType=problem-list-v2&envId=wem0bhs2

/*

Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.

Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: 6
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
After modifying the input array in-place, the first 6 characters of chars should be ["a","2","b","2","c","3"].

Example 2:

Input: chars = ["a"]
Output: 1
Explanation: The only group is "a", which remains uncompressed since it is a single character.
After modifying the input array in-place, the first character of chars should be ["a"].

Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: 4
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
After modifying the input array in-place, the first 4 characters of chars should be ["a","b","1","2"].


Approach: Two Pointers.

1. First, we will initialize two pointers, one for reading the input array and another for writing the compressed characters.
2. We will iterate through the input array using the read pointer and count the number of consecutive repeating characters.
3. When we encounter a different character or reach the end of the array, we will write the character and its count (if greater than 1) to the write pointer.
4. Finally, we will return the length of the compressed array, which is the position of the write pointer.

Dry Run:
Input: chars = ["a","a","b","b","c","c","c"]
read = 0, write = 0
i = 1: chars[1] == chars[0] => count = 2
i = 2: chars[2] != chars[1] => write "a" and "2", count = 1
i = 3: chars[3] == chars[2] => count = 2
i = 4: chars[4] != chars[3] => write "b" and "2", count = 1
i = 5: chars[5] == chars[4] => count = 2
i = 6: chars[6] != chars[5] => write "c" and "3", count = 1
Final: write "a", "2", "b", "2", "c", "3"
Output: 6

Time Complexity: O(n), where n is the length of the input array. We are iterating through the array once to count the characters and build the compressed string.

Space Complexity: O(1), since we are using only a constant amount of extra space for the pointers and counters. The compressed string is stored in the input array itself.

*/

// Code:
class StringCompression {

    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 0) return 0;

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                compressed.append(chars[i - 1]);
                if (count > 1) {
                    compressed.append(count);
                }
                count = 1;
            }
        }

        // Handle the last character
        compressed.append(chars[n - 1]);
        if (count > 1) {
            compressed.append(count);
        }

        // Convert the compressed string back to a character array
        char[] result = compressed.toString().toCharArray();
        System.arraycopy(result, 0, chars, 0, result.length);

        return result.length;
    }
}