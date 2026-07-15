// Problem Link: https://leetcode.com/problems/insert-interval?envType=problem-list-v2&envId=wr2p5de7

/*

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. 
You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Approach:

1. First, we will create a new list to store the merged intervals.
2. We will iterate through the existing intervals and check if the current interval overlaps with the new interval.
3. If the current interval ends before the new interval starts, we can safely add the current interval to the merged list.
4. If the current interval starts after the new interval ends, we can add the new interval to the merged list and then add the current interval.
5. If the current interval overlaps with the new interval, we will merge them by updating the start and end of the new interval to be the minimum start and maximum end of both intervals.  
6. After iterating through all the intervals, if the new interval has not been added to the merged list, we will add it at the end.
7. Finally, we will return the merged list as the result.


Dry Run:
Let's dry run the code with the example intervals = [[1,3],[6,9]], newInterval = [2,5]:
1. Initialize merged = [] and newInterval = [2,5].
2. Iterate through intervals:
    - For the first interval [1,3]:
      - It overlaps with newInterval [2,5] since 1 <= 5 and 3 >= 2.
      - Update newInterval to [min(1,2), max(3,5)] = [1,5].
    - For the second interval [6,9]:
      - It does not overlap with newInterval [1,5] since 6 > 5.
      - Add newInterval [1,5] to merged and then add [6,9].
3. After iterating through all intervals, merged = [[1,5],[6,9]].


Code:

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {  // Add all intervals that come before the new interval
            merged.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) { // Merge overlapping intervals with the new interval
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        merged.add(newInterval);

        while (i < n) {    // Add all remaining intervals
            merged.add(intervals[i]);
            i++;
        }

        return merged.toArray(new int[merged.size()][]);
    }
}


Time Complexity: O(n), where n is the number of intervals in the input list. We iterate through the list once.

Space Complexity: O(n), where n is the number of intervals in the input list. In the worst case, we may need to store all intervals in the merged list.


*/

