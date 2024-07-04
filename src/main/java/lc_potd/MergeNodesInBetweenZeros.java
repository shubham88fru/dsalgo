package lc_potd;

//@link - https://leetcode.com/problems/merge-nodes-in-between-zeros/description/
public class MergeNodesInBetweenZeros {
    public ListNode mergeNodes(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode next = head.next;
        ListNode prev0 = head;
        int gsum = 0;
        while (next != null) {

            if (next.val == 0) {
                prev0.val = gsum;

                if (next.next == null) prev0.next = null;
                else prev0.next = next;

                prev0 = next;

                gsum = 0;
            } else {
                gsum += next.val;

            }

            next = next.next;
        }

        return dummy.next;

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
