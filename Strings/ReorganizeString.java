// Problem Link: https://leetcode.com/problems/reorganize-string?envType=problem-list-v2&envId=wem0bhs2

/*

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""


Approach: Using Priority Queue.

1. First, we will count the frequency of each character in the string using a HashMap.
2. Then, we will create a max heap (priority queue) to store the characters based on their frequency. 
The character with the highest frequency will be at the top of the heap.
3. We will then build the result string by repeatedly taking the two most frequent characters from the heap and appending them to the result string.
4. If the frequency of a character is greater than 1, we will decrement its frequency and push it back into the heap.
5. If at any point, the heap is empty and we still have characters left to add, it means that it is not possible to rearrange the string, and we will return an empty string.
6. Finally, we will return the result string.


Dry Run:

(I) Input: s = "aab"
1. Count frequency: {'a': 2, 'b': 1}
2. Create max heap: [('a', 2), ('b', 1)]
3. Build result string:
    - Pop 'a' and 'b' from heap, append to result: result = "ab"
    - Decrement frequency of 'a' to 1, push back into heap: [('a', 1)]
    - Pop 'a' from heap, append to result: result = "aba"
4. Heap is empty, and we have used all characters. Return result: "aba"


(II) Input: s = "aaab"
1. Count frequency: {'a': 3, 'b': 1}
2. Create max heap: [('a', 3), ('b', 1)]
3. Build result string:
    - Pop 'a' and 'b' from heap, append to result: result = "ab"
    - Decrement frequency of 'a' to 2, push back into heap: [('a', 2)]
    - Pop 'a' from heap, append to result: result = "aba"
    - Decrement frequency of 'a' to 1, push back into heap: [('a', 1)]
    - Pop 'a' from heap, append to result: result = "abaa"
4. Heap is empty, but we still have 'a' left to add. Return result: ""


Time Complexity: O(n log k), where n is the length of the string and k is the number of unique characters in the string. The log k factor comes from the heap operations.

Space Complexity: O(n + k), where n is the length of the string and k is the number of unique characters in the string. The O(n) space is used for the frequency map, and O(k) space is used for the heap.


*/


// Code:


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class ReorganizeString {
    public String reorganizeString(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create a max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(freqMap.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> prevEntry = null;

        // Step 3: Build the result string
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            result.append(currentEntry.getKey());

            // If there was a previous character with remaining frequency, push it back into the heap
            if (prevEntry != null && prevEntry.getValue() > 0) {
                maxHeap.offer(prevEntry);
            }

            // Decrement the frequency of the current character and set it as previous
            currentEntry.setValue(currentEntry.getValue() - 1);
            prevEntry = currentEntry;
        }

        // Step 4: Check if the result length matches the input length
        return result.length() == s.length() ? result.toString() : "";
    }
}