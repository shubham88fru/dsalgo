package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//@link - https://leetcode.com/problems/implement-queue-using-stacks/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4827768388583424
public class ImplementQueueUsingStacks { }

//1) Edctv soln.
class MyQueue1 {
    private final Deque<Integer> st1; //will only be used for push.
    private final Deque<Integer> st2; //for pop and peek.

    public MyQueue1() {
        st1 = new ArrayDeque<Integer>();
        st2 = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        //On every push, empty the entire stack1 in stack 2, first.
        //Then, add the latest element to stack 1.
        //Finally, empty out the entire stack2 into stack 1.
        //Note that by the three steps above, it is ensured
        //that the elements in stack 1 (from top to bottom)
        //are in order in which they should appear in a queue.
        while(!st1.isEmpty()) {
            st2.addFirst(st1.removeFirst());
        }
        st1.addFirst(x);
        while (!st2.isEmpty()) {
            st1.addFirst(st2.removeFirst());
        }

    }

    public int pop() {
        //since elements in stack1 are in order,
        //we are directly return top of stack1.
        return st1.removeFirst();
    }

    public int peek() {
        return st1.peekFirst();
    }

    public boolean empty() {
        return st1.isEmpty();
    }
}

//2) My soln
class MyQueue2 {
    private final Stack<Integer> st_push; //will only be used for push.
    private final Stack<Integer> st_pop_peek; //for pop and peek.

    public MyQueue2() {
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
