// Problem Link: https://leetcode.com/problems/reverse-string?envType=problem-list-v2&envId=wem0bhs2

/*

Write a function that reverses a string. The input string is given as an array of characters s.
You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

Example 2:
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Approach: Using Two Pointer Technique.

1. First, we initialize two pointers, left and right, to the start and end of the array, respectively.
2. We then enter a loop that continues until the left pointer is less than the right pointer.
3. Inside the loop, we swap the characters at the left and right positions.
4. After swapping, we increment the left pointer and decrement the right pointer to move towards the center of the array.
5. Once the loop exits, the array will be reversed in place.


Dry Run:
Input: s = ["h","e","l","l","o"]

1. Initialize left = 0, right = 4 (length of array - 1)
2. Swap s[0] and s[4]: ["o","e","l","l","h"]
3. Increment left to 1, decrement right to 3
4. Swap s[1] and s[3]: ["o","l","l","e","h"]
5. Increment left to 2, decrement right to 2
6. Now left is not less than right, so we exit the loop.

Output: ["o","l","l","e","h"]


Time Complexity: O(n), where n is the length of the input array. 
We traverse half of the array, performing a constant-time swap operation for each pair of characters.

Space Complexity: O(1), as we are using a constant amount of extra space for the two pointers, regardless of the input size.

*/


// Code:

class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            // Swap characters at left and right pointers
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move pointers towards the center
            left++;
            right--;
        }
    }
}