// Problem Link: https://leetcode.com/problems/min-stack?envType=problem-list-v2&envId=wra3kcft

/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int value) pushes the element value onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Approach 1: Using Two Stacks.

1. First, we will create two stacks: one for storing the elements and another for storing the minimum elements.
2. When we push an element onto the stack, we will also check if it is smaller than or equal to the current minimum element (the top of the min stack). If it is, we will push it onto the min stack as well.
3. When we pop an element from the stack, we will also check if it is equal to the current minimum element (the top of the min stack). If it is, we will pop it from the min stack as well.
4. The top() function will return the top element of the main stack.
5. The getMin() function will return the top element of the min stack, which is the current minimum element in the stack.
6. This way, we can retrieve the minimum element in constant time.


Dry Run:
Input:
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
1. Initialize two empty stacks: mainStack and minStack.
2. Perform the operations:
    - push(-2): Push -2 onto mainStack. Since minStack is empty, also push -2 onto minStack. 
      mainStack: [-2], minStack: [-2]
    - push(0): Push 0 onto mainStack. Since 0 is greater than the top of minStack (-2), do not push onto minStack.
      mainStack: [-2, 0], minStack: [-2]
    - push(-3): Push -3 onto mainStack. Since -3 is less than the top of minStack (-2), also push -3 onto minStack.
      mainStack: [-2, 0, -3], minStack: [-2, -3]
    - getMin(): Return the top of minStack, which is -3.
    - pop(): Pop the top element from mainStack (-3). Since it is equal to the top of minStack (-3), also pop from minStack.
      mainStack: [-2, 0], minStack: [-2]
    - top(): Return the top of mainStack, which is 0.
    - getMin(): Return the top of minStack, which is -2.
Time Complexity: O(1) for each operation (push, pop, top, getMin).
Space Complexity: O(n), where n is the number of elements in the stack. In the worst case, we may have to store all elements in both stacks.

Code:

class MinStack {
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        mainStack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        if (!mainStack.isEmpty()) {
            int poppedValue = mainStack.pop();
            if (poppedValue == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        if (!mainStack.isEmpty()) {
            return mainStack.peek();
        }
        return 0;
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return 0;
    }
}



Approach 2: Using One Stack.

1. First, we will create a single stack to store the elements and a variable to keep track of the current minimum element.
2. When we push an element onto the stack, we will check if it is smaller than or equal to the current minimum element. 
If it is, we will push the current minimum element onto the stack before pushing the new element and update the current minimum element to the new element.
3. When we pop an element from the stack, we will check if it is equal to the current minimum element. 
If it is, we will pop the next element from the stack (which is the previous minimum) and update the current minimum element to that value.
4. The top() function will return the top element of the stack.
5. The getMin() function will return the current minimum element.
6. This way, we can retrieve the minimum element in constant time.


Dry Run:
Input:
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
1. Initialize an empty stack and set currentMin to Integer.MAX_VALUE.
2. Perform the operations:
    - push(-2): Since -2 is less than currentMin (Integer.MAX_VALUE), push currentMin onto the stack (which is Integer.MAX_VALUE) and then push -2. Update currentMin to -2.
      stack: [Integer.MAX_VALUE, -2], currentMin: -2
    - push(0): Since 0 is greater than currentMin (-2), just push 0 onto the stack.
      stack: [Integer.MAX_VALUE, -2, 0], currentMin: -2
    - push(-3): Since -3 is less than currentMin (-2), push currentMin onto the stack (which is -2) and then push -3. Update currentMin to -3.
      stack: [Integer.MAX_VALUE, -2, 0, -2, -3], currentMin: -3
    - getMin(): Return currentMin, which is -3.
    - pop(): Pop the top element from the stack (-3). Since it is equal to currentMin (-3), pop the next element from the stack (which is -2) and update currentMin to that value.
      stack: [Integer.MAX_VALUE, -2, 0], currentMin: -2
    - top(): Return the top of the stack, which is 0.
    - getMin(): Return currentMin, which is -2.


Time Complexity: O(1) for each operation (push, pop, top, getMin).

Space Complexity: O(n), where n is the number of elements in the stack. In the worst case, we may have to store all elements in the stack along with the previous minimums.


*/


// Code:

import java.util.Stack;

class MinStack {
    private final Stack<Integer> stack;
    private int currentMin;

    public MinStack() {
        stack = new Stack<>();
        currentMin = Integer.MAX_VALUE;
    }

    public void push(int value) {
        if (value <= currentMin) {
            stack.push(currentMin);
            currentMin = value;
        }
        stack.push(value);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int poppedValue = stack.pop();
            if (poppedValue == currentMin) {
                currentMin = stack.pop();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return 0;
    }

    public int getMin() {
        return currentMin;
    }
}