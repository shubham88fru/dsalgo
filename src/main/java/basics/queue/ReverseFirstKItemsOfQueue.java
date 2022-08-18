package basics.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//k<=n
public class ReverseFirstKItemsOfQueue {

    //T: Theta(N)
    void reverseFirstK(Queue<Integer> queue, int k) {
        if (queue.size()<k||
            k<=0) return;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=0; i<k; i++) {
            stack.push(queue.poll());
        }

        //the required elements will
        //be reversed and added but
        //toward the end of queue, which
        //is not we want.
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }

        //Therefore, for remaining n-k els of queue,
        //we remove and add them to back.
        for (int i=0; i<queue.size()-k;i++) {
            queue.offer(queue.poll());
        }
    }

    public static void main(String[] args) {
        ReverseFirstKItemsOfQueue reverseFirstKItemsOfQueue
                = new ReverseFirstKItemsOfQueue();
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(10);
        queue1.add(20);
        queue1.add(30);
        queue1.add(40);
        queue1.add(50);

        Queue<Integer> queue2 = new LinkedList<>();
        queue2.add(40);
        queue2.add(50);

        reverseFirstKItemsOfQueue.reverseFirstK(queue1, 3);
        System.out.println(queue1);

        reverseFirstKItemsOfQueue.reverseFirstK(queue2, 2);
        System.out.println(queue2);
    }
}
