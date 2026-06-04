// Problem Link: https://leetcode.com/problems/palindromic-substrings?envType=problem-list-v2&envId=dynamic-programming

/*


Company Tags: Amazon, Morgan Stanley, Microsoft, Oracle, Uber.

Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Naive Approach: Generate all possible substrings and check if they are palindromic. 

Generate every possible substring.
Check whether each substring is a palindrome.
Count all palindromic substrings.

Since a string of length n contains O(n²) substrings and checking each substring takes O(n), the total complexity becomes O(n³).

Algorithm:
count = 0

for i from 0 to n-1
    for j from i to n-1
         if substring(i,j) is palindrome
               count++

return count

Dry Run:
s = "abc"
Substrings:
a   -> palindrome -> count=1
ab  -> not palindrome
abc -> not palindrome

b   -> palindrome -> count=2
bc  -> not palindrome

c   -> palindrome -> count=3

Answer: 3

Code:

class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }
}

Time Complexity: O(n³) - O(n²) for generating substrings and O(n) for checking each substring.

Space Complexity: O(1) - No extra space is used, only a few variables for counting and checking palindromes.


Better Approach: Dynamic Programming(Tabulation).

Key Observation:

For a substring s[i...j] to be palindrome:
1. s[i] == s[j]
2. Inner substring s[i+1...j-1] is palindrome

Therefore:
dp[i][j] = true
if s[i] == s[j]
and dp[i+1][j-1] is true

DP Relation:
dp[i][j] = (s[i] == s[j])
           &&
           (j-i <= 2 || dp[i+1][j-1])

Why j-i <= 2 ?
Length 1: "a"

Length 2: "aa"

Length 3: "aba"

No need to check inner DP further.

Table Filling Order:

Since dp[i][j] depends on dp[i+1][j-1],
we fill from bottom to top:

i = n-1 -> 0
j = i -> n-1

Dry Run:
s = "aaa"
Table:
    a   a   a
a   T   T   T
a       T   T
a           T

Palindromes:

a, a, a, aa, aa, aaa

Count = 6

Code:

class Solution {
    public int countSubstrings(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || dp[i + 1][j - 1])) {

                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }
}

Time Complexity: O(n²) - We fill an n x n table.

Space Complexity: O(n²) - We use an n x n table to store palindrome information.


Optimal Approach: Expand Around Center.

Core Idea

Every palindrome has a center.

Examples:

aba
 ^
center

abba
 ^^
center between characters.

For every position:

Consider it as odd-length center.
Consider gap after it as even-length center.
Expand while characters match.

Why Does This Work?

Every palindrome can be uniquely represented by a center.

racecar
   e

abba
 bb

Instead of generating substrings and checking them,

we start from the center and directly generate palindromes.

Number of Centers:

For string length n:
n odd centers
n-1 even centers

Total:
2n-1 centers

Expansion Function:

while left >=0
and right < n
and s[left] == s[right]

      palindrome found
      count++

      left--
      right++


Detailed Dry Run:

Example
s = "aaa"

Center 0
Odd
a
^
Palindrome: a
count = 1

Expand:
left=-1
right=1
stop

Center Between 0 and 1
aa
^^

Palindrome: aa
count = 2

Expand:
left=-1
right=2
stop

Center 1
aaa
 ^

Step 1:
a
count = 3

Expand:
aaa
count = 4

Center Between 1 and 2
aa
^^

count = 5

Center 2
a
count = 6

Final Answer:
6


Time Complexity: Each expansion may traverse the string.
Worst Case = O(n²)
Example:
"aaaaaa"

Space Complexity: O(1) - We only use a few variables for counting and expanding, no extra space proportional to input size.

*/

// Code:

class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int count = 0;

        for (int center = 0; center < s.length(); center++) {

            // Odd length palindromes
            count += expand(s, center, center);

            // Even length palindromes
            count += expand(s, center, center + 1);
        }

        return count;
    }

    private int expand(String s, int left, int right) {

        int count = 0;

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            count++;

            left--;
            right++;
        }

        return count;
    }
}