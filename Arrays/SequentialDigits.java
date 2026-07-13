// Problem Link: https://leetcode.com/problems/sequential-digits?envType=daily-question&envId=2026-07-13

/*

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]


Approach:

1. First, we will create a list of all possible sequential digits numbers. The maximum sequential digit number is 123456789, so we can generate all sequential digit numbers from 1 to 9.
2. We will use a nested loop to generate sequential digit numbers. 
The outer loop will iterate through the starting digit (from 1 to 9), and the inner loop will build the sequential number by appending the next digit until we reach a number greater than 9.
3. After generating all sequential digit numbers, we will filter the list to include only those numbers that fall within the given range [low, high].
4. Finally, we will return the filtered list sorted in ascending order.


Dry Run:

Input: low = 100, high = 300
1. Generate sequential digit numbers:
    - Starting with 1: 12, 123, 1234, 12345, 123456, 1234567, 12345678, 123456789
    - Starting with 2: 23, 234, 2345, 23456, 234567, 2345678, 23456789
    - Starting with 3: 34, 345, 3456, 34567, 345678, 3456789
    - Starting with 4: 45, 456, 4567, 45678, 456789
    - Starting with 5: 56, 567, 5678, 56789
    - Starting with 6: 67, 678, 6789
    - Starting with 7: 78, 789
    - Starting with 8: 89
    - Starting with 9: (no valid sequential digits)
2. Filter the generated numbers to include only those within the range [100, 300]:
    - Valid sequential digits: 123, 234
3. Return the sorted list of valid sequential digits: [123, 234]


Time Complexity: O(1) - The number of sequential digit numbers is constant and does not depend on the input size.

Space Complexity: O(1) - The space used to store the sequential digit numbers is constant and does not depend on the input size.


*/


// Code:

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        
        // Generate all sequential digit numbers
        for (int start = 1; start <= 9; start++) {
            int num = start;
            for (int nextDigit = start + 1; nextDigit <= 9; nextDigit++) {
                num = num * 10 + nextDigit; // Append the next digit
                if (num >= low && num <= high) {
                    result.add(num);
                }
                if (num > high) {
                    break; // No need to continue if the number exceeds high
                }
            }
        }
        
        Collections.sort(result); // Sort the result list
        return result;
    }
}