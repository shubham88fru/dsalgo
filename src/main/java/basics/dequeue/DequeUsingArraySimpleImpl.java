package basics.dequeue;

import java.util.Arrays;

//All the ops here won't be O(1)
//simple impl, doesn't use front, rear vars.
public class DequeUsingArraySimpleImpl {
    int[] dq;
    int cap, size;

    DequeUsingArraySimpleImpl(int cap) {
        this.cap = cap;
        dq = new int[cap];
    }

    //O(N)
    void insertFront(int x) {
        if (isFull()) return;
        for (int i=size-1; i>=0; i--) {
            dq[i+1] = dq[i];
        }
        dq[0] = x;
        size++;
    }

    void insertRear(int x) {
        if (isFull()) return;
        dq[size] = x;
        size++;
    }

    //O(N)
    void deleteFront() {
        if (isEmpty()) return;
        if (size - 1 >= 0) System.arraycopy(dq, 1,
                dq, 0,
                size - 1);
        size--;
    }

    void deleteRear() {
        if (isEmpty()) return;
        size--;
    }

    int getRear() {
        if (isEmpty()) return -1;
        return size-1;
    }

    int getFront() {
        if (isEmpty()) return -1;
        return 0;
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
        DequeUsingArraySimpleImpl dq =
                new DequeUsingArraySimpleImpl(5);
        dq.insertRear(10);
        System.out.println(dq);
        dq.insertFront(20);
        System.out.println(dq);
        dq.insertRear(30);
        System.out.println(dq);
        dq.insertFront(40);
        System.out.println(dq);
        dq.deleteFront();
        System.out.println(dq);
        dq.deleteRear();
        System.out.println(dq);

    }
}
