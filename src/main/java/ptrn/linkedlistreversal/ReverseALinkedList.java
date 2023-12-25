package ptrn.linkedlistreversal;

//@link - https://leetcode.com/problems/reverse-linked-list/description/
//@strvr - https://takeuforward.org/data-structure/reverse-a-linked-list/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4776723687407616
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
        ListNode next = curr.next; //next initialized with curr's next.

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

        //prev is the new head.
        return prev;
    }

    //2) No extra code to handle rearring of last 2 nodes.
    //T: O(N), S: O(1)
    private ListNode sol2(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null; //next initialized to null.

        //while curr not null..
        while (curr != null) {
            //store next, so we can break cur-next link.
            next = curr.next;
            //break curr-next link and reverse it.
            curr.next = prev;
            //move prev to curr
            prev = curr;
            //move curr to next.
            curr = next;
        }

        //prev is the new head.
        return prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
