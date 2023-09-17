package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/implement-stack-using-queues/
//@strvr - https://takeuforward.org/data-structure/implement-stack-using-single-queue/
public class ImplementStackUsingDeque {
    //Using java's Deque (ArrayDeque) as queue.
    private final Deque<Integer> q;

    public ImplementStackUsingDeque() {
        q = new ArrayDeque<>();
    }

    public void push(int x) {
        //add to last of queue.
        //NOTE: Since Deque is a doubly ended queue,
        //we can remove elements from last also and hence
        //unlike other solutions on LC, we don't need to copy
        //all but first element to top of the queue.
        //However, if using some other java queue, after we
        //add an element to last, we'll run a loop for currsize-1 times
        //and basically move all below elements to front of queue in reverse order.
        q.addLast(x);
    }

    public int pop() {
        //since last element of queue is
        //the most recent added element. Remove
        //from the last. This works straight forward only because
        //we are using a doubly ended queue here which allows
        //to to pull elements from last (even in a queue)
        return q.removeLast();
    }

    public int top() {
        return q.getLast();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
