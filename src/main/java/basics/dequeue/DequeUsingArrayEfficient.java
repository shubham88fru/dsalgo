package basics.dequeue;

import java.util.Arrays;

//All ops using this impl are O(1)
public class DequeUsingArrayEfficient {

    int[] dq;
    int cap, size;

    //In a circular arr, `rear=(front+size-1)%cap`;
    int front;

    DequeUsingArrayEfficient(int cap) {
        this.cap = cap;
        dq = new int[cap];
    }

    //O(N)
    void insertFront(int x) {
        if (isFull()) return;
        front = (front+cap-1)%cap;
        dq[front] = x;
        size++;
    }

    void insertRear(int x) {
        if (isFull()) return;
        int new_rear = (front+size)%cap;
        dq[new_rear] = x;
        size++;
    }

    //O(N)
    void deleteFront() {
        if (isEmpty()) return;
        front = (front+1)%cap;
        size--;
    }

    void deleteRear() {
        if (isEmpty()) return;
        size--;
    }

    int getRear() {
        if (isEmpty()) return -1;
        return (front+size-1)%cap;
    }

    int getFront() {
        if (isEmpty()) return -1;
        return front;
    }

    int size() {
        return size;
    }
    boolean isFull() {
        return size==cap;
    }
    boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString() {
        return Arrays.toString(dq);
    }


    public static void main(String[] args) {
        DequeUsingArrayEfficient dq =
                new DequeUsingArrayEfficient(4);
        dq.insertFront(10);
        System.out.println(dq);
        dq.insertRear(20);
        System.out.println(dq);
        dq.insertRear(30);
        System.out.println(dq);
    }
}
