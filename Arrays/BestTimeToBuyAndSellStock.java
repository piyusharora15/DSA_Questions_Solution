// Problem Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock?envType=problem-list-v2&envId=dynamic-programming

/*

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Brute Force Approach:
1. We can use two nested loops to iterate through the array and calculate the profit for every possible pair of buy and sell days.
2. We will keep track of the maximum profit encountered during the iterations and return it at the end.

Code:

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}

Time Complexity: O(n^2) - We have two nested loops, each iterating through the array.
Space Complexity: O(1) - We are using a constant amount of extra space to store the maximum profit.


Optimal Approach: Using a Single Pass:

1.First, we initialize two variables: minPrice to store the minimum price encountered so far (initialized to Integer.MAX_VALUE) and maxProfit to store the maximum profit (initialized to 0).
2.We iterate through the prices array once. For each price:
   a. We update minPrice if the current price is lower than minPrice.
   b. We calculate the potential profit by subtracting minPrice from the current price.
   c. If the calculated profit is greater than maxProfit, we update maxProfit.
3. After iterating through the array, we return maxProfit.


Time Complexity: O(n) - We traverse the array once.

Space Complexity: O(1) - We are using a constant amount of extra space to store minPrice and maxProfit.


*/


// Code:

class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update minPrice if current price is lower
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice; // Update maxProfit if current profit is higher
            }
        }

        return maxProfit;
    }
}
