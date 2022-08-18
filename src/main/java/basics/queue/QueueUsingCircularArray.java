package basics.queue;

import java.util.Arrays;

//Better than plain array.
//All ops are O(1)
//Even dequeue op is O(1)
public class QueueUsingCircularArray {
    private final int[] queue;
    private final int cap;
    private int size;
    private int front;

    public QueueUsingCircularArray(int cap) {
        this.cap = cap;
        this.size = 0;
        queue = new int[cap];
    }

    public void enqueue(int num) {
        if (isFull()) return;
        int rear = getRear();
        rear =  (rear+1)%cap;
        queue[rear] = num;
        size++;
    }

    public void dequeue() {
        if (isEmpty()) return;
        front = (front+1)%cap;
        size--;
    }

    public boolean isFull() {
        return size==cap;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return front;
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return (front+size-1)%cap;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}

class Test2 {
    public static void main(String[] args) {
        QueueUsingCircularArray queue = new QueueUsingCircularArray(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        System.out.println(queue);
        System.out.println(queue.getFront());
        System.out.println(queue.getRear());
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.getFront());
        System.out.println(queue.getRear());
    }
}
