package swd.linkedlist;

//@link - https://practice.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class ReverseADLL {
    public static Node reverseDLL(Node  head) {
        return reverse(head);
    }

    private static Node reverse(Node head) {
        if (head == null) return null;

        Node curr = head;
        Node prev = null;
        Node next = null;
        Node finalHead = null;

        while (curr != null) {

            //curr's prev
            prev = curr.prev;

            //curr's next
            next = curr.next;

            //curr's next becomes prev
            curr.next = prev;

            //curr's prev becomes next
            curr.prev = next;

            //keep track to return head at the end.
            finalHead = curr;

            //curr moves to next
            curr = next;
        }

        return finalHead;
    }
}

class Node {
    int data;
    Node next, prev;
    Node(int data)
    {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
