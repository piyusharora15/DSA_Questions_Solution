// Problem Link: https://leetcode.com/problems/longest-increasing-subsequence?envType=problem-list-v2&envId=dynamic-programming

/*

Company Tags: Amazon, Microsoft, Paytm, Samsung.

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4
Explanation: The longest increasing subsequence is [0,1,2,3], therefore the length is 4.

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1


Naive Approach: Using Recursion.

Intuition:
At every index we have two choices:

Take current element
OR
Skip current element

If we take it:
nums[current] > previousChosenElement
must be true.

State Definition:  f(index, prevIndex)

Meaning:
Longest LIS possible starting from index when previous selected element is prevIndex

Recursive Choices:
Skip
notTake = f(index+1, prevIndex)

Take
Only if increasing:
nums[index] > nums[prevIndex]

Then: take = 1 + f(index+1, index)

Recurrence:

f(index, prev) = max(skip, take)

Dry Run:

nums = [3,1,2]
Start: f(0,-1)

Choices:
Take 3, Skip 3

Take 3: 1 + f(1,0)
Cannot take 1 or 2 afterward.
Result = 1

Skip 3: f(1,-1)

Take 1: 1 + f(2,1)

Take 2:
1 + 1 = 2

Answer: 2

Subsequence: [1,2]


Code:

class Solution {
    public int lengthOfLIS(int[] nums) {
        return f(0, -1, nums);
    }

    private int f(int index, int prevIndex, int[] nums) {
        if (index == nums.length) {
            return 0;
        }

        int notTake = f(index + 1, prevIndex, nums); // Skip the current element

        int take = 0;  // Take the current element if it's greater than the previous one

        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            take = 1 + f(index + 1, index, nums);
        }

        return Math.max(notTake, take);
    }
}


Time Complexity: O(2^n) - Each element has two choices (take or skip), leading to 2^n possible subsequences.

Space Complexity: O(n) - The maximum depth of the recursion stack can go up to n in the worst case when all elements are increasing.


Better Approach 1: Using Memoization.

DP State: dp[index][prevIndex+1]

Why +1?
Because: prevIndex = -1
cannot be used as array index.

Code:

class Solution {

    int[][] dp;
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        dp = new int[n][n + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return solve(0, -1, nums);
    }
    private int solve(int index, int prevIndex, int[] nums) {
        
        if(index == nums.length)
            return 0;

        if(dp[index][prevIndex + 1] != -1)
            return dp[index][prevIndex + 1];

        int notTake = solve(index + 1, prevIndex, nums);

        int take = 0;

        if(prevIndex == -1 || nums[index] > nums[prevIndex]) {

            take = 1 + solve(index + 1, index, nums);
        }

        return dp[index][prevIndex + 1] = Math.max(take, notTake);
    }
}

Time Complexity: O(n^2) - There are n indices and for each index, we can have n possible previous indices.

Space Complexity: O(n^2) - The dp array takes O(n^2) space, and the recursion stack can go up to O(n) in the worst case, leading to O(n^2) overall space complexity.


Better Approach 2: Using Tabulation.

Key Insight:
Define: dp[i] as: Length of LIS ending at index i.

Example:
nums = [10,9,2,5,3,7]
Initially:
dp = [1,1,1,1,1,1]
Every element itself is LIS length 1.

Transition:
For every previous index: j < i
If: nums[j] < nums[i]
then current element can extend LIS ending at j.

dp[i] = max(dp[i], dp[j] + 1)

Detailed Dry Run:

Example
nums = [10,9,2,5,3,7,101,18]

Initialize:
dp = [1,1,1,1,1,1,1,1]

i = 3 (value = 5)
Check:
10 < 5 ? No
9 < 5 ? No
2 < 5 ? Yes
dp[3] = max(1, dp[2]+1)
      = max(1,2)
      = 2
DP: [1,1,1,2,1,1,1,1]

i = 5 (value = 7)
Check:
2 < 7
dp[5] = 2
Check:
5 < 7
dp[5] = max(2,3) = 3
Check:
3 < 7
Still: 3
DP: [1,1,1,2,2,3,1,1]

i = 6 (value = 101)
Can extend from:
10
9
2
5
3
7
Best previous:
dp[5]=3
Therefore:
dp[6]=4

DP: [1,1,1,2,2,3,4,1]

Answer: max(dp)=4


Code:

class Solution {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}


Time Complexity: O(n^2) - We have two nested loops, each running up to n.

Space Complexity: O(n) - The dp array takes O(n) space.


Optimal Approach: Dp + Binary Search.

Key Observation

We don't actually need the entire subsequence.

We only need to maintain:
Smallest possible tail for all lengths.

Example: nums = [10,9,2,5,3,7,101,18]
Maintain: tails[]

Step 1:
10
tails = [10]

Step 2:
9
Replace 10.
tails = [9]
Because: 9 gives better future opportunities.

Step 3:
2
tails = [2]

Step 4:
5
Append.
tails = [2,5]

Step 5:
3
Replace first number >=3
tails = [2,3]

Step 6:
7
Append.
tails = [2,3,7]

Step 7:
101
Append.
tails = [2,3,7,101]

Step 8:
18
Replace.
tails = [2,3,7,18]
Length remains: 4

Answer: 4

Why Replacement Works?
Suppose: [2,5]
and new number: 3

If we keep: [2,3]
instead of: [2,5]
future numbers have a better chance to extend.
Smaller tail is always better.

Binary Search Usage:
Find:
first element >= current
and replace it.
This is called: Lower Bound

Detailed Dry Run:
nums = [4,10,4,3,8,9]
4
[4]
10
[4,10]
4
Replace first >=4
[4,10]
No change.
3
Replace first >=3
[3,10]
8
Replace first >=8
[3,8]
9
Append
[3,8,9]
Length: 3

Answer: 3


Time Complexity: O(n log n) - We iterate through the array once (O(n)) and for each element, we perform a binary search on the tails array (O(log n)).

Space Complexity: O(n) - In the worst case, the tails array can grow to the size of n if all elements are increasing.


Important Interview Question:

Does tails[] store the actual LIS?
No.

Example:
nums = [0,8,4,12,2]

Eventually:
tails = [0,2,12]

This is not necessarily a valid subsequence built during traversal.
It only helps compute the length.

Interview Explanation (What You Should Say)

I can solve LIS using four approaches.

The brute-force solution uses recursion where at every index 'i' either take or skip the current element while maintaining the previously chosen element. This gives O(2ⁿ).

Since many states repeat, I can memoize (index, prevIndex) resulting in O(n²) time and O(n²) space.

A more common DP formulation defines dp[i] as the length of the LIS ending at index i. 
For every previous index j < i, if nums[j] < nums[i], I can extend that subsequence. 
This gives O(n²) time and O(n) space.

The optimal solution uses a tails array and binary search. 
tails[k] stores the smallest possible tail value of an increasing subsequence of length k+1. 
For each number, I find the lower bound and replace it, or append if it's larger than all tails. 
This achieves O(n log n) time and O(n) space.

*/


// Code:

import java.util.ArrayList;
import java.util.List;

class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        List<Integer> tails = new ArrayList<>();

        for(int num : nums) {

            int left = 0;
            int right = tails.size();

            while(left < right) {

                int mid = left + (right - left) / 2;

                if(tails.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if(left == tails.size()) {
                tails.add(num);
            } else {
                tails.set(left, num);
            }
        }

        return tails.size();
    }
}