// Problem Link: https://leetcode.com/problems/number-of-longest-increasing-subsequence?envType=problem-list-v2&envId=wh8gjvyh

/*

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.


Naive Approach: Using Recursion.

Idea:

Generate every possible subsequence.

For each subsequence:

1. Check if increasing.
2. Compute its length.
3. Keep track of:
   - Maximum length found.
   - Number of subsequences having that length.

Recursive Choices:

At every index:

Take current element
Skip current element

Exactly like LIS recursion.

Dry Run:

nums = [1,3,2]

Possible increasing subsequences:
[1]
[3]
[2]

[1,3]
[1,2]

Maximum length: 2
Count: 2
Answer: 2


Code:

class Solution {

    int maxLen = 0;
    int count = 0;

    public int findNumberOfLIS(int[] nums) {
        dfs(nums, 0, Integer.MIN_VALUE, 0);

        return count;
    }

    private void dfs(int[] nums, int index, int prev, int len) {

        if(index == nums.length) {  

            if(len > maxLen) {  // Found a longer subsequence
                maxLen = len;
                count = 1;
            }
            else if(len == maxLen) {   // Found another subsequence of the same maximum length
                count++;
            }

            return;
        }

        dfs(nums, index + 1, prev, len); // Skip current element

        if(nums[index] > prev) {
            dfs(nums, index + 1, nums[index], len + 1);  // Take current element
        }
    }
}


Time Complexity: O(2^n) - Exponential, as we are generating all possible subsequences.

Space Complexity: O(n) - The maximum depth of the recursion tree can go up to n in the worst case.


Why Standard LIS DP is Not Enough?

For LIS Length problem:
dp[i] = Length of LIS ending at i

Example:

[1,3,5,4,7]

At element 7:

We can come from 5
We can come from 4

Both produce LIS length 4.

If we only store length: dp[i]
we lose information about:
How many ways we can achieve that length.
Therefore we need another DP array.

Optimal Approach: Using Dynamic Programming.

State Definition:
We maintain two arrays.

length[i] = Length of the longest increasing subsequence ending at index i.
count[i] = Number of longest increasing subsequences ending at index i having length length[i].

Initialization:

Every element alone forms:
Length = 1
Count = 1

So:
length[i] = 1;
count[i] = 1;

Transition:
For every pair: j < i
If: nums[j] < nums[i]
then we can extend subsequence ending at j.

Case 1:
We found a strictly longer LIS.
length[j] + 1 > length[i]

Then:
Better length discovered.

Update:
length[i] = length[j] + 1
count[i] = count[j]

Why?
Because all best ways now come from j.

Case 2:
We found another way to get the SAME best length.
length[j] + 1 == length[i]

Then:
count[i] += count[j]
because we discovered additional LIS.

Formula:

If:
nums[j] < nums[i]

Better Length
if(length[j] + 1 > length[i])
length[i] = length[j] + 1
count[i] = count[j]

Same Length
if(length[j] + 1 == length[i])
count[i] += count[j]


Detailed Dry Run:

Example 1:
nums = [1,3,5,4,7]

Initial:
length   = [1,1,1,1,1]
count = [1,1,1,1,1]

i = 1 (3)
Check: 1 < 3
New length:
length[1] = 2
count[1] = 1

Now:
length   = [1,2,1,1,1]
count = [1,1,1,1,1]

i = 2 (5)
From 1:
length = 2
From 3:
length = 3
Best:
length[2] = 3
count[2] = 1
Now:
length   = [1,2,3,1,1]
count = [1,1,1,1,1]

i = 3 (4)
From 1:
length = 2
From 3:
length = 3
Best:
length[3] = 3
count[3] = 1
Now:
length   = [1,2,3,3,1]
count = [1,1,1,1,1]

i = 4 (7)
Check all previous.
From 5:
length[2] + 1 = 4

Update:
length[4] = 4
count[4] = count[2] = 1

From 4:
length[3] + 1 = 4

Same best length.
Therefore:
count[4] += count[3] = 2

Final:
length   = [1,2,3,3,4]
count = [1,1,1,1,2]

Maximum LIS Length: 4

Number of LIS: count[4] = 2

Answer:2

Example 2:
nums = [2,2,2,2,2]

Initialize:
len   = [1,1,1,1,1]
count = [1,1,1,1,1]

No extension possible because:
2 < 2
false
Maximum length: 1

Total count:
1+1+1+1+1 = 5

Answer: 5


Time Complexity: O(n^2) - We have two nested loops to fill the dp arrays.

Space Complexity: O(n) - We are using two additional arrays of size n to store lengths and counts.



Interview Explanation (Exactly What You Should Say)

For the LIS length problem, I only needed to store the best length ending at each index. However, in this problem I must also know how many subsequences achieve that best length.

So I maintain two arrays:

len[i] = length of the LIS ending at index i
count[i] = number of LIS ending at index i having length len[i]

For every pair (j, i) where j < i and nums[j] < nums[i], I try to extend the subsequence ending at j.

If len[j] + 1 > len[i], I found a better LIS length for i, so I update len[i] and set count[i] = count[j].
If len[j] + 1 == len[i], I found another way to achieve the same best length, so I add count[j] to count[i].

After filling the DP arrays, I find the global maximum LIS length and sum the counts of all indices whose LIS length equals that maximum.

This gives O(n²) time and O(n) space, which is the standard expected interview solution.


*/


// Code:

import java.util.Arrays;

class NumberOfLIS {

    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] len = new int[n];
        int[] count = new int[n];

        Arrays.fill(len, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for(int i = 0; i < n; i++) {

            for(int j = 0; j < i; j++) {

                if(nums[j] < nums[i]) {     // Can we extend the subsequence ending at j?
 
                    if(len[j] + 1 > len[i]) {  // Found a better length
 
                        len[i] = len[j] + 1;  // Update to the better length

                        count[i] = count[j];  // Reset count to the count of the new best length
                    }

                    else if(len[j] + 1 == len[i]) {  // Found another way to achieve the same best length
 
                        count[i] += count[j];   // Increment count by the count of this alternative way
                    }
                }
            }

            maxLen = Math.max(maxLen, len[i]);
        }

        int answer = 0;

        for(int i = 0; i < n; i++) {

            if(len[i] == maxLen) {

                answer += count[i];
            }
        }

        return answer;
    }
}