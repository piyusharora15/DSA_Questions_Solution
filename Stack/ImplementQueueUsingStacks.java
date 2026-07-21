
// Problem Link: https://leetcode.com/problems/implement-queue-using-stacks?envType=problem-list-v2&envId=wra3kcft


/*

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 

Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false


Approach: Using two stacks.

1. First, we will create two stacks, stack1 and stack2. The first stack (stack1) will be used for enqueue operations (push), and the second stack (stack2) will be used for dequeue operations (pop and peek).

2. When we push an element onto the queue, we will simply push it onto stack1.

3. When we need to pop or peek at the front of the queue, we will transfer all elements from stack1 to stack2 (if stack2 is empty), and then perform the operation on stack2.

4. If stack2 is not empty, we can directly pop or peek from stack2.

5. The empty operation will check if both stacks are empty.

6. This approach ensures that each element is moved at most twice (once to stack1 and once to stack2), resulting in an amortized O(1) time complexity for each operation.


Code:

class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}


Time Complexity: O(1) for push, O(n) for pop and peek in the worst case, but amortized O(1) for pop and peek.

Space Complexity: O(n), where n is the number of elements in the queue, as we are using two stacks to store the elements.

*/