package lc_potd;

//@link - https://leetcode.com/problems/design-circular-deque/

/*
Following is my soln, using DLL, but its kinda
Nasty. Mik solved using a simple array.
checkout on next encounter.
 */
public class DesignCircularDeque {
    Node head = new Node(-1);
    Node tail = new Node(-1);
    int cap;
    int sz;

    public DesignCircularDeque(int k) {
        this.cap = k;
        this.sz = 0;
        head.next = tail;
        tail.prev = head;
    }

    public boolean insertFront(int value) {
        if (!isFull()) {
            Node currHead = head.next;

            Node newNode = new Node(value);
            head.next = newNode;

            newNode.prev = tail.prev;
            newNode.next = currHead;

            if (currHead != null) currHead.prev = newNode;

            if (sz == 0) {
                tail.prev = head.next;
            }
            sz += 1;
            return true;
        }

        return false;
    }

    public boolean insertLast(int value) {
        if (!isFull()) {
            Node currTail = tail.prev;
            Node newNode = new Node(value);
            tail.prev = newNode;
            newNode.prev = currTail;
            if (currTail != null) {
                currTail.next = newNode;
            }
            if (sz == 0) {
                head.next = tail.prev;
            }
            sz += 1;
            return true;
        }

        return false;
    }

    public boolean deleteFront() {
        if (!isEmpty()) {
            Node currHead = head.next;
            head.next = currHead.next;
            currHead.next = null;

            if (sz == cap-1) {
                tail.prev = null;
            }

            sz -= 1;
            return true;
        }

        return false;
    }

    public boolean deleteLast() {
        if (!isEmpty()) {
            Node currTail = tail.prev;
            Node prev = currTail.prev;
            tail.prev = prev;
            if (prev != null) {
                prev.next = null;
            }

            if (sz == 1) {
                head.next = null;
            }

            sz -= 1;
            return true;
        }

        return false;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return head.next.val;
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return tail.prev.val;
    }

    public boolean isEmpty() {
        return (sz == 0);
    }

    public boolean isFull() {
        return (sz == cap);
    }
}

class Node {
    int val;
    Node next;
    Node prev;
    public Node(int val) {
        this.val = val;
    }
}
