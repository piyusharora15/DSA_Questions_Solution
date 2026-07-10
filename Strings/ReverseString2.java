// Problem Link: https://leetcode.com/problems/reverse-string-ii?envType=problem-list-v2&envId=wem0bhs2

/*

Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

Example 1:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Example 2:
Input: s = "abcd", k = 2
Output: "bacd"


Approach: Using Two Pointer Technique.

1. First, we convert the input string into a character array to allow in-place modifications.
2. We then iterate through the character array in steps of 2k, where k is the given integer.
3. For each segment of 2k characters, we reverse the first k characters using a helper function that employs the two-pointer technique.
4. The helper function initializes two pointers, left and right, to the start and end of the segment to be reversed.
5. Inside the helper function, we enter a loop that continues until the left pointer is less than the right pointer. 
We swap the characters at the left and right positions, then increment the left pointer and decrement the right pointer to move towards the center of the segment.
6. Once the loop exits, the segment will be reversed in place.
7. After processing all segments, we convert the character array back to a string and return it as the final result.


Dry Run:
Input: s = "abcdefg", k = 2

First, we convert the string into a character array: ['a', 'b', 'c', 'd', 'e', 'f', 'g'].
Then, we iterate through the array in steps of 2k (4 in this case).
- For the first segment (i = 0), we reverse the first k characters ('a' and 'b'), resulting in ['b', 'a', 'c', 'd', 'e', 'f', 'g'].
- For the second segment (i = 4), we reverse the first k characters ('e' and 'f'), resulting in ['b', 'a', 'c', 'd', 'f', 'e', 'g'].
Finally, we convert the character array back to a string and return "bacdfeg".


Time Complexity: O(n), where n is the length of the input string. 
We traverse the string in steps of 2k, and for each segment, we reverse k characters, leading to a linear time complexity.

Space Complexity: O(n), where n is the length of the input string.

*/


// Code:

class ReverseString2 {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            reverse(arr, i, Math.min(i + k - 1, arr.length - 1));
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}