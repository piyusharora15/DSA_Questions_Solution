// Problem Link: https://leetcode.com/problems/rank-transform-of-an-array?envType=daily-question&envId=2026-07-12

/*

Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

Example 1:
Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.

Example 2:
Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.

Example 3:
Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]


Approach: Using HashMap and Sorting.

1. First, we create a copy of the original array and sort it. This will help us determine the rank of each unique element.
2. Next, we create a HashMap to store the rank of each unique element. 
We iterate through the sorted array and assign ranks starting from 1. 
If an element is already in the HashMap, we skip it to ensure that equal elements receive the same rank.
3. Finally, we iterate through the original array and replace each element with its corresponding rank from the HashMap.
4. The final result is returned as an array of ranks.


Dry Run:
Input: arr = [40,10,20,30]
1. Create a copy of the original array and sort it: sortedArr = [10, 20, 30, 40]
2. Create a HashMap to store ranks:
    - Initialize rank = 1
    - Iterate through sortedArr:
      - For 10: not in HashMap, assign rank 1, increment rank to 2
      - For 20: not in HashMap, assign rank 2, increment rank to 3
      - For 30: not in HashMap, assign rank 3, increment rank to 4
      - For 40: not in HashMap, assign rank 4, increment rank to 5
    - HashMap now contains: {10=1, 20=2, 30=3, 40=4}
3. Iterate through the original array and replace each element with its rank:
    - For 40: rank is 4
    - For 10: rank is 1
    - For 20: rank is 2
    - For 30: rank is 3
4. Final result: [4, 1, 2, 3]


Time Complexity: O(n log n) - Sorting the array takes O(n log n) time, and iterating through the array to assign ranks takes O(n) time.

Space Complexity: O(n) - We use a HashMap to store the ranks of unique elements, which can take up to O(n) space in the worst case.

*/


// Code:

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class RankTransformOfAnArray {
    public int[] arrayRankTransform(int[] arr) {
        // Create a copy of the original array and sort it
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Create a HashMap to store the rank of each unique element
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }
        
        // Replace each element in the original array with its corresponding rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}