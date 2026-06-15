// Problem Link: https://leetcode.com/problems/group-anagrams?envType=problem-list-v2&envId=string

/*

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.


Approach: 

1. First, we will create a hashmap to store the grouped anagrams. The key of the hashmap will be the sorted version of the string, and the value will be a list of strings that are anagrams of each other.

2. We will iterate through each string in the input array, sort the characters of the string, and use the sorted string as the key to group the anagrams together in the hashmap.

3. Finally, we will return the values of the hashmap as a list of lists, which will contain the grouped anagrams.


Dry Run:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
- For "eat": sorted version is "aet". We add "eat" to the list corresponding to the key "aet".
- For "tea": sorted version is "aet". We add "tea" to the list corresponding to the key "aet".
- For "tan": sorted version is "ant". We add "tan" to the list corresponding to the key "ant".
- For "ate": sorted version is "aet". We add "ate" to the list corresponding to the key "aet".
- For "nat": sorted version is "ant". We add "nat" to the list corresponding to the key "ant".
- For "bat": sorted version is "abt". We add "bat" to the list corresponding to the key "abt".

Once we have processed all the strings, the hashmap will look like this:
{
  "aet": ["eat", "tea", "ate"],
  "ant": ["tan", "nat"],
  "abt": ["bat"]
}

Finally, we return the values of the hashmap as a list of lists:
[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]


Time Complexity: O(N * K log K) where N is the number of strings and K is the maximum length of a string (due to sorting each string).

Space Complexity: O(N * K) for storing the grouped anagrams in the hashmap, where N is the number of strings and K is the maximum length of a string (since we are storing the sorted version of each string as a key in the hashmap).


*/


// Code:

import java.util.*;
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] arr = s.toCharArray(); // Convert the string to a character array
            Arrays.sort(arr);  // Sort the character array to get the sorted version of the string
            String str = new String(arr); // Convert the sorted character array back to a string
            if(!map.containsKey(str)){
                map.put(str,new ArrayList<>());
            }
            map.get(str).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
