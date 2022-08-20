package misc;

import java.util.ArrayDeque;
import java.util.Deque;

/*
*Design a DS that supports these operations in O(1)-
* 1) insertMin(x) //assume that inserted item is always smaller than existing item.
* 2) insertMax(x) //assume that inserted item is always greater than existing item.
* 3) getMin() //get current min
* 4) getMax() //get current max.
* 5) extractMin() //remove the current min
* 6) extractMax() //remove the current max.
*/
public class DesignADS {

    Deque<Integer> dq;

    DesignADS() {
        dq = new ArrayDeque<>();
    }

    void insertMin(int x) {
        dq.offerFirst(x); //add min el to last of deq.
    }

    void insertMax(int x) {
        dq.offerLast(x); //add max to start of deq.
    }

    int getMin() {
        return dq.peekFirst();
    }

    int getMax() {
        return dq.peekLast();
    }

    int extractMax() {
        return dq.pollLast();
    }

    int extractMin() {
        return dq.pollFirst();
    }

    @Override
    public String toString() {
        return dq.toString();
    }

    public static void main(String[] args) {
        DesignADS ds = new DesignADS();
        ds.insertMin(10);
        ds.insertMax(15);
        ds.insertMin(5);
        System.out.println(ds.extractMin());
        System.out.println(ds.extractMax());
        ds.insertMin(8);
        System.out.println(ds);
    }
}
