package strvr.stacksandqueues;

import java.util.Stack;

/*
NOTE: For an optimal approach checkout strvr
* */

//@link - https://leetcode.com/problems/min-stack/
//@strvr - https://takeuforward.org/data-structure/implement-min-stack-o2n-and-on-space-complexity/
class MinStack {
    private final Stack<Pair> _stack;

    public MinStack() {
        _stack = new Stack<>();
    }

    //We use the idea of prefix min.
    //For every new element being pushed to stack,
    //we keep a track of min till now. We push both the element
    //and the min as a pair on the stack. We could also have used a
    //second stack that only stores the min values after each push.
    public void push(int val) {
        if (!_stack.isEmpty()) {
            if (val < _stack.peek().minTillNow) {
                _stack.push(new Pair(val, val));
            } else {
                _stack.push(new Pair(val, _stack.peek().minTillNow));
            }
        } else {
            _stack.push(new Pair(val, val));
        }

    }

    public void pop() {
        _stack.pop();
    }

    public int top() {
        return _stack.peek().val;
    }

    public int getMin() {
        return _stack.peek().minTillNow;
    }
}

class Pair {
    int val;
    int minTillNow;

    public Pair(int val, int minTillNow) {
        this.val = val;
        this.minTillNow = minTillNow;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */