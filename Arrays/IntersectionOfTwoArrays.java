// Problem Link: https://leetcode.com/problems/intersection-of-two-arrays?envType=problem-list-v2&envId=wh88bf73

/*

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Approach 1: Using HashSet.

1. First, we create a HashSet to store the unique elements of the first array (nums1).
2. We then iterate through the second array (nums2) and check if each element exists in the HashSet. 
If it does, we add it to another HashSet (resultSet) to ensure uniqueness of the intersection elements.
3. Finally, we convert the resultSet back to an array and return it.

Code:

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}


Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2.

Space Complexity: O(n), where n is the length of nums1, due to the HashSet storing the unique elements of nums1. 
The resultSet will at most store min(n, m) unique elements, but this is also bounded by O(n).


Approach 2: Using Sorting and Two Pointers.

1. First, we sort both arrays (nums1 and nums2).
2. We then use two pointers to traverse both sorted arrays simultaneously.
3. If the elements at both pointers are equal, we add that element to the result list and move both pointers forward.
4. If the element at the first pointer is smaller than the element at the second pointer, we move the first pointer forward.
5. If the element at the second pointer is smaller than the element at the first pointer, we move the second pointer forward.
6. We continue this process until we reach the end of either array.
7. Finally, we convert the result list to an array and return it.


Code:

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> resultSet = new HashSet<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}


Time Complexity: O(n log n + m log m), where n is the length of nums1 and m is the length of nums2, due to the sorting step. The two-pointer traversal takes O(n + m) time.
Overall, the time complexity is dominated by the sorting step.

Space Complexity: O(n + m) in the worst case, if all elements in both arrays are unique and present in the intersection. 
However, the space used for the resultSet is at most O(min(n, m)) since it can only contain unique elements from the smaller array. 
The space used for sorting is O(1) if we sort in place, or O(n + m) if we consider the space used by the sorting algorithm.

*/
