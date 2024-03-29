package basics.queue;

import java.util.Arrays;

//Using plain array.
//dequeue operation is
//O(N). Rest all are O(1).
public class QueueUsingArray {

    private final int[] queue;
    private final int cap;
    private int size;
    private int front = -1;
    private int rear = -1;

    public QueueUsingArray(int cap) {
        this.cap = cap;
        this.size = 0;
        queue = new int[cap];
    }

    public void enqueue(int num) {
        if (!isFull())
            queue[size++] = num;
        front = 0;
        rear = size-1;
    }

    public void dequeue() {
        if (isEmpty()) return;
        if (size >= 0)
            System.arraycopy(queue,
                    1, queue, 0, size);
        size--;
        rear = size - 1;
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
        return rear;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}

class Test {
    public static void main(String[] args) {
        QueueUsingArray queue = new QueueUsingArray(5);
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
