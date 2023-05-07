package swd.linkedlist;

//@link - https://leetcode.com/problems/reverse-linked-list/description/
public class ReverseALinkedList {
    public ListNode reverseList(ListNode head) {
        return sol1(head);
        //return sol2(head);
    }

    //1) With prev, curr and next pointer.
    //Needs extra code to rearrange last 2 nodes.
    private ListNode sol1(ListNode head) {
        if (head == null) return null;

        //start with 3 pointers
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;

        //while next not null..
        while (next != null) {
            curr.next = prev; //curr node points backward.
            prev = curr; //prev moves to curr
            curr = next; //curr moves to next
            next = curr.next; //and next to curr's next.
        }

        //at this point, curr is pointing to last element
        //and prev to second last. So, do the last move.
        curr.next = prev;
        prev = curr;

        return prev;
    }

    //2) No extra code to handle rearring of last 2 nodes.
    private ListNode sol2(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
