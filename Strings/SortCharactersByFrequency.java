// Problem Link: https://leetcode.com/problems/sort-characters-by-frequency?envType=problem-list-v2&envId=wem0bhs2

/*

Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.


Approach 1: Using HashMap and PriorityQueue.

1. First, we will create a HashMap to store the frequency of each character in the string.
2. Then, we will create a PriorityQueue (Max Heap) to store the characters based on their frequency in decreasing order.
3. We will iterate through the HashMap and add each character to the PriorityQueue.
4. Finally, we will build the result string by polling characters from the PriorityQueue and appending them to a StringBuilder based on their frequency.
5. Return the result string.


Dry Run:
Input: s = "tree"
Frequency Map: {t=1, r=1, e=2}
PriorityQueue: [(e, 2), (t, 1), (r, 1)]
Result: "eert"


Code:

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>(); // Step 1: Create a HashMap to store the frequency of each character
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>( 
            (a, b) -> b.getValue() - a.getValue()
        ); // Step 2: Create a PriorityQueue (Max Heap) to store characters based on their frequency

        maxHeap.addAll(frequencyMap.entrySet()); // Step 3: Add each character and its frequency to the PriorityQueue

        StringBuilder result = new StringBuilder();  // Step 4: Build the result string
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char character = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                result.append(character);
            }
        }

        return result.toString();  // Step 5: Return the result string
    }
}


Time Complexity: O(n log n), where n is the length of the string. 
This is because we are inserting n characters into the PriorityQueue, which takes O(log n) time for each insertion.

Space Complexity: O(n), where n is the length of the string.


Approach 2: Using HashMap and Bucket Sort.

1. First, we will create a HashMap to store the frequency of each character in the string.
2. Then, we will create an array of lists (buckets) where the index represents the frequency of characters. 
Each bucket will contain a list of characters that have the same frequency.
3. We will iterate through the HashMap and add each character to the corresponding bucket based on its frequency.
4. Finally, we will build the result string by iterating through the buckets in reverse order (from highest frequency to lowest) and appending the characters to a StringBuilder based on their frequency.
5. Return the result string.


Dry Run:
Input: s = "tree"

Frequency Map: {t=1, r=1, e=2}
Buckets: [[], [t, r], [e], [], []]  // Index 0: frequency 0, Index 1: frequency 1, Index 2: frequency 2
Result: "eert"


Time Complexity: O(n), where n is the length of the string.

Space Complexity: O(n), where n is the length of the string.


*/


// Code:

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>(); // Step 1: Create a HashMap to store the frequency of each character
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<Character>[] buckets = new List[s.length() + 1]; // Step 2: Create an array of lists (buckets)
        for (char c : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(c);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(c); // Step 3: Add each character to the corresponding bucket based on its frequency
        }

        StringBuilder result = new StringBuilder(); // Step 4: Build the result string
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }

        return result.toString(); // Step 5: Return the result string
    }
}

