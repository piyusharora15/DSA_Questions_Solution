// Problem Link: https://leetcode.com/problems/evaluate-reverse-polish-notation?envType=problem-list-v2&envId=wra3kcft

/*

You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22


Approach: Using Stack.

1. First, we will create a stack to store the operands.
2. We will iterate through the tokens array and for each token, we will check if it is an operator or an operand.
3. If the token is an operand, we will push it onto the stack.
4. If the token is an operator, we will pop the top two operands from the stack, perform the operation, and push the result back onto the stack.
5. After processing all the tokens, the final result will be the only element left in the stack, which we will return.


Dry Run:
Input: tokens = ["2","1","+","3","*"]
Stack: []
Token: "2" -> Push 2
Stack: [2]
Token: "1" -> Push 1
Stack: [2, 1]
Token: "+" -> Pop 1, Pop 2, Compute 2 + 1 = 3, Push 3
Stack: [3]
Token: "3" -> Push 3
Stack: [3, 3]
Token: "*" -> Pop 3, Pop 3, Compute 3 * 3 = 9, Push 9
Stack: [9]
Final Result: 9


Code:

import java.util.Stack;

class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();
                int result = performOperation(a, b, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b; // Integer division truncates towards zero
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}


Time Complexity: O(n), where n is the number of tokens in the input array. We are iterating through the tokens array once and performing constant time operations for each token.

Space Complexity: O(n), where n is the number of tokens in the input array. In the worst case, we may need to store all the operands in the stack.



*/