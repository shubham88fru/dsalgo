package strvr.stacksandqueues;

import java.util.LinkedList;
import java.util.Queue;

//@link - https://leetcode.com/problems/implement-stack-using-queues/
//@strvr - https://takeuforward.org/data-structure/implement-stack-using-single-queue/

public class ImplementStackUsingStandardQueue {
    private final Queue<Integer> q;

    public ImplementStackUsingStandardQueue() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        for (int i=0; i<q.size()-1;i++) {
            q.add(q.remove());
        }
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
