package swd.linkedlist;

//@link - https://leetcode.com/problems/remove-linked-list-elements/description/
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode curr = head;
        ListNode prev = dummyNode;

        //while not exhausted the list..
        while (curr != null) {
            ListNode currNext = curr.next;

            //..if curr node's val is equal to
            //target - delete it.
            if (curr.val == val) {
                prev.next = currNext;
                //NOTE: Don't update prev in this case. Because we're deleting this node.
            } else prev = curr; //else nothing to delete, so move need to move next. Therefore, update prev to curr node..
            curr = currNext; //..and move current to next node.
        }
        return dummyNode.next;
    }
}
