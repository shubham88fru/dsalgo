package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/?
public class DeleteNodesFromLinkedListPresentInArray {
    public ListNode modifiedList(int[] nums, ListNode head) {
        return revise(nums, head);
    }

    private ListNode revise(int[] nums, ListNode head) {
        Set<Integer> st = new HashSet<>();
        for (int num: nums) st.add(num);

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev;
        prev = dummy;

        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            if (st.contains(curr.val)) {
                curr.next = null;
                prev.next = next;
            } else {
                prev = curr;
            }

            curr = next;
        }

        return dummy.next;
    }
}
