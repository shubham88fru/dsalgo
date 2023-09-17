package strvr.stacksandqueues;

import java.util.Stack;

//@link - https://leetcode.com/problems/implement-queue-using-stacks/
//@strvr - https://takeuforward.org/data-structure/implement-queue-using-stack/
public class ImplementQueueUsingStandardStack {
    private final Stack<Integer> st_push; //will only be used for push.
    private final Stack<Integer> st_pop_peek; //for pop and peek.

    public ImplementQueueUsingStandardStack() {
        st_push = new Stack<Integer>();
        st_pop_peek = new Stack<Integer>();
    }

    public void push(int x) {
        st_push.push(x);

    }

    public int pop() {
        //if peek/pop stack empty, fill
        //it with the push stack. This will ensure
        //that the elements from push stack are pushed to
        //peek/pop stack in reverse order (which is what we need for a queue.)
        if (st_pop_peek.isEmpty()) {
            int size = st_push.size();
            for (int i=0; i<size; i++) {
                st_pop_peek.push(st_push.pop());
            }
        }
        return st_pop_peek.pop();
    }

    public int peek() {
        //if peek/pop stack empty, fill
        //it with the push stack. This will ensure
        //that the elements from push stack are pushed to
        //peek/pop stack in reverse order (which is what we need for a queue.)
        if (st_pop_peek.isEmpty()) {
            int size = st_push.size();
            for (int i=0; i<size; i++) {
                st_pop_peek.push(st_push.pop());
            }
        }
        return st_pop_peek.peek();
    }

    public boolean empty() {
        //only if both stacks empty.
        return st_pop_peek.isEmpty() && st_push.isEmpty();
    }
}
