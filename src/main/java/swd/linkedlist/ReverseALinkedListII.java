package swd.linkedlist;

//@link - https://leetcode.com/problems/reverse-linked-list-ii/description/
public class ReverseALinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //if left and right same, dont need to do anything.
        if (left == right) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //standard 3 pointers for reverse a linked list.
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = curr.next;

        //to handle the repointing
        //of left node and left's prev node
        //after reversing.
        ListNode leftNode = head;
        ListNode leftPrev = dummy;

        int pos = 1;

        //till we reach right.
        while (pos < right) {
            //if crossed left..
            if (pos >= left) {
                //store left and its prev pointer
                //needed at the last stage.
                if (pos == left) {
                    leftNode = curr;
                    leftPrev = prev;
                }
                //..keep reversing.
                curr.next = prev;
            }

            //keep moving regardless.
            prev = curr;
            curr = next;
            next = next.next;

            pos += 1;
        }

        //at this point curr is pointing to
        //the right node. So, we do the final reverse.
        //and point the left node's next to right's next
        //and left's prev node to right node.
        curr.next = prev;
        leftNode.next = next;
        leftPrev.next = curr;

        //dummy.next points to head of
        //original list.
        return dummy.next;

    }
}
