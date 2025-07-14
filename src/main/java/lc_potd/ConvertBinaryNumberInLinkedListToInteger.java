package lc_potd;

//@link - https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class ConvertBinaryNumberInLinkedListToInteger {
    public int getDecimalValue(ListNode head) {
        return revise(head);
    }

    /*
    * Naive approach.
    * There a simple bit manip approach, revise it for interview.
    * */
    private int revise(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        int pow = 0;
        int dec = 0;
        while (head != null) {
            dec += (int) (head.val * Math.pow(2.0, pow));
            pow += 1;
            head = head.next;
        }

        return dec;
    }
}
