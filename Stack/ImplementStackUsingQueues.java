// Problem Link: https://leetcode.com/problems/implement-stack-using-queues?envType=problem-list-v2&envId=wra3kcft

/*

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

- void push(int x) Pushes element x to the top of the stack.
- int pop() Removes the element on the top of the stack and returns it.
- int top() Returns the element on the top of the stack.
- boolean empty() Returns true if the stack is empty, false otherwise.

Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.

Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

Example:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False


Approach: Using two queues.

1. First, we will create two queues, queue1 and queue2. The idea is to use one queue to hold the elements of the stack and the other queue to help with the push operation.

2. For the push operation, we will enqueue the new element into queue2. Then, we will dequeue all elements from queue1 and enqueue them into queue2. 
Finally, we will swap the names of queue1 and queue2. This way, the new element will be at the front of queue1, simulating the behavior of a stack.

3. For the pop operation, we will simply dequeue from queue1, which will give us the last pushed element.

4. For the top operation, we will peek at the front of queue1 to get the last pushed element without removing it.

5. For the empty operation, we will check if queue1 is empty.

6. The time complexity for push operation is O(n) because we need to move all elements from queue1 to queue2. The pop, top, and empty operations are O(1).


Code:

class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        
        Queue<Integer> temp = queue1; // Swap the names of the queues
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

*/