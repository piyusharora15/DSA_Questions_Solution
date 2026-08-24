// Problem Link: https://leetcode.com/problems/reverse-string-ii

/*

Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

Example 1:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Example 2:
Input: s = "abcd", k = 2
Output: "bacd"


Approach : Two Pointer In-Place Swap.

1. First, we convert the string into a character array to allow in-place modifications.
2. We iterate through the character array in steps of 2k. For each segment, we reverse the first k characters using a two-pointer approach.
3. We set the left pointer to the start of the segment and the right pointer to the end of the first k characters (or the end of the string if there are fewer than k characters left).
4. We swap the characters at the left and right pointers, then move the left pointer forward and the right pointer backward until they meet or cross.
5. Finally, we convert the modified character array back to a string and return it.


Dry Run:
Input: s = "abcdefg", k = 2
1. Convert string to char array: ch = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
2. Iterate through the array in steps of 2k (i.e., 4):
   - For i = 0:
     - left = 0, right = min(1, 6) = 1
     - Swap ch[0] and ch[1]: ch = ['b', 'a', 'c', 'd', 'e', 'f', 'g']
   - For i = 4:
     - left = 4, right = min(5, 6) = 5
     - Swap ch[4] and ch[5]: ch = ['b', 'a', 'c', 'd', 'f', 'e', 'g']
3. Convert char array back to string: "bacdfeg"


Time Complexity: O(n), where n is the length of the string. We traverse the string once.

Space Complexity: O(n) for the character array used to store the string characters.

*/

// Code:
class Solution {
    public String reverseStr(String s, int k) {
        if(s == null || s.length() == 0 || k == 0) return s;
        char[] ch = s.toCharArray();
        int n = ch.length;
        for(int i=0;i<n;i+=2*k){
            int left = i;
            int right = Math.min(i+k-1,n-1);
            while(left < right){
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
        }
        return new String(ch);
    }
}
