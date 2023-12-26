package ptrn.linkedlistreversal;

//@link - https://leetcode.com/problems/reorder-list/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6702333267542016
public class ReorderList {
    public void reorderList(ListNode head) {
        //Find middle of the given LL.
        //If there are multiple middle nodes,
        //we need the second one.
        ListNode tail = middleOfLL(head);

        //other half of the LL.
        ListNode otherHalf = tail.next;

        //Break the first and second half.
        tail.next = null;

        //Reverse the second half.
        ListNode otherHalfReversed = reverseLL(otherHalf);

        //Insert each node of the reversed second half
        //into the first half at alternate positions.
        mergeLL(otherHalfReversed, head);
    }

    //Returns the middle of a given LL.
    //If there are multiple middle nodes, returns
    //the second one.
    private ListNode middleOfLL(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return fast.next == null ? slow : slow.next;
    }

    //Standard algo to reverse a given LL
    private ListNode reverseLL(ListNode head) {
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

    //Merges each node of src at alternate positions
    //of destination list.
    private void mergeLL(ListNode src, ListNode dest) {
        /**
         dest: A -> B -> C -> D
         src: E -> F
         */
        ListNode currd = dest; //A
        ListNode next = currd.next; //B
        ListNode currs = src; //E

        while (src != null) {
            //A-->E
            currd.next = currs;
            //F
            src = src.next;
            //A-->E-->B
            currs.next = next;
            //F
            currs = src;
            //B
            currd = next;
            //C
            next = next.next;
        }
    }
}
