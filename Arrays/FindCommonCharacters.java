// Problem Link: https://leetcode.com/problems/find-common-characters?envType=problem-list-v2&envId=wh88bf73

/*

Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]

Approach: Using Frequency Array.
1. First, we will create a frequency array of size 26 to store the frequency of each character in the first word.
2. Then, we will iterate through the rest of the words and create a temporary frequency array for each word.
3. We will update the main frequency array by taking the minimum frequency of each character between the main frequency array and the temporary frequency array.
4. Finally, we will construct the result based on the frequency array and return it as a list of strings.

Dry Run:
Input: words = ["bella","label","roller"]
1. Create a frequency array of size 26: freq = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
2. Iterate through the first word "bella" and update the frequency array: freq = [1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
3. Iterate through the second word "label" and create a temporary frequency array: temp = [1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
4. Update the main frequency array by taking the minimum frequency of each character: freq = [1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
5. Iterate through the main frequency array and construct the result: result = ["e", "l", "l"]

Time Complexity: O(n * m), where n is the number of words and m is the average length of the words. We iterate through each word and each character in the word to update the frequency array.

Space Complexity: O(1), since the frequency array is of fixed size 26, regardless of the input size. The result list can contain at most 26 characters in the worst case.

*/

// Code:

import java.util.ArrayList;
import java.util.List;

public class FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        // Initialize frequency array with the first word
        for (char c : words[0].toCharArray()) {
            freq[c - 'a']++;
        }
        // Update frequency array with minimum counts from subsequent words
        for (int i = 1; i < words.length; i++) {
            int[] temp = new int[26];
            for (char c : words[i].toCharArray()) {
                temp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                freq[j] = Math.min(freq[j], temp[j]);
            }
        }
        // Construct result based on frequency array
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) {
                result.add(String.valueOf((char) ('a' + i)));
            }
        }
        return result;
    }
}
