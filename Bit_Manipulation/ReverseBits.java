/*

Problem Link: https://leetcode.com/problems/reverse-bits?envType=problem-list-v2&envId=auswip1r

Reverse bits of a given 32 bits signed integer.

Example 1:
Input: n = 43261596
Output: 964176192
Explanation:
Integer	    Binary
43261596	00000010100101000001111010011100
964176192	00111001011110000010100101000000

Example 2:
Input: n = 2147483644
Output: 1073741822
Explanation:
Integer	   Binary
2147483644	01111111111111111111111111111100
1073741822	00111111111111111111111111111110

Approach: Using Bit Manipulation.

1. First, we will initialize a variable `result` to 0. This variable will hold the reversed bits of the input integer.
2. We will iterate through each of the 32 bits of the input integer `n`. For each bit, we will perform the following steps:
   a. Left shift the `result` by 1 to make space for the next bit.
   b. Use the bitwise AND operation to check if the least significant bit (LSB) of `n` is set (i.e., if it is 1). If it is set, we will set the LSB of `result` to 1 using the bitwise OR operation.
   c. Right shift `n` by 1 to process the next bit in the next iteration.
3. After processing all 32 bits, the `result` variable will contain the reversed bits of the input integer `n`.


Dry Run:
Input: n = 43261596
1. Initialize `result` to 0.
2. Iterate through each of the 32 bits of `n`:
   - Iteration 1: 
     - Left shift `result` (0) by 1 → `result` = 0
     - Check LSB of `n` (43261596): 43261596 & 1 = 0 → LSB is not set, so `result` remains 0
     - Right shift `n` by 1 → `n` = 21630798
   - Iteration 2:
     - Left shift `result` (0) by 1 → `result` = 0
     - Check LSB of `n` (21630798): 21630798 & 1 = 0 → LSB is not set, so `result` remains 0
     - Right shift `n` by 1 → `n` = 10815399
   - Iteration 3:
     - Left shift `result` (0) by 1 → `result` = 0
     - Check LSB of `n` (10815399): 10815399 & 1 = 1 → LSB is set, so set LSB of `result`: `result` = 1
     - Right shift `n` by 1 → `n` = 5407699
   - Continue this process for all remaining bits...

Output: After processing all 32 bits, the final value of `result` will be 964176192, which is the reversed bits of the input integer 43261596.

Time Complexity: O(1) - The number of bits is fixed (32 bits), so the time complexity is constant.

Space Complexity: O(1) - We are using a constant amount of space for the `result` variable.


*/

// Code:

class ReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; // Left shift result to make space for the next bit
            result |= (n & 1); // Set the LSB of result if LSB of n is set
            n >>= 1; // Right shift n to process the next bit
        }
        return result;
    }
}