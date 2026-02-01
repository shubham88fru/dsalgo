package ptrn.linkedlistreversal;

//@link - https://leetcode.com/problems/reverse-linked-list/description/
//@strvr - https://takeuforward.org/data-structure/reverse-a-linked-list/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4776723687407616
public class ReverseALinkedList {
    public ListNode reverseList(ListNode head) {
        // return iterative(head);
        // return sol2(head);
        return sol3(head);
    }

    private ListNode sol3(ListNode head) {
        return recursive(head);
    }

    private ListNode recursive(ListNode curr) {
        if (curr == null || curr.next == null) { //og tail.
            return curr;
        }

        ListNode head = recursive(curr.next);
        ListNode next = curr.next;
        next.next = curr;
        curr.next = null;

        return head;
    }

    /**
     My recursive soln.
     Not very clean.
     */
    private ListNode sol2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode ogHead = recursive2(head, dummy);
        if (ogHead != null) ogHead.next = null;
        return dummy.next;
    }

    private ListNode recursive2(ListNode curr, ListNode dummy) {

        if (curr == null || curr.next == null) {
            dummy.next = curr;
            return curr;
        }


        ListNode ogNext = recursive2(curr.next, dummy);
        ogNext.next = curr;

        return curr;
    }

    private ListNode iterative(ListNode head) {
        if (head == null) return null;

        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

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
