package ptrn.linkedlistreversal;

//@link - https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5480851682099200
public class SwapNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        return swapNodes2(head, k);
        //return swapNodes1(head, k);
    }

    private ListNode swapNodes2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;

        //to find kth node from front.
        while (k > 0) {
            p2 = p2.next;
            k -= 1;
        }
        ListNode kthFromFront = p2;

        //two pointer approach to
        //find kth node from the end.
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode kthFromEnd = p1;

        //swap the values.
        int temp = kthFromEnd.val;
        kthFromEnd.val = kthFromFront.val;
        kthFromFront.val = temp;

        return head;
    }

    private ListNode swapNodes1(ListNode head, int k) {
        //get nth node from end.
        ListNode nthFromEnd = getNthFromEnd(head, k);

        //initialize a start pointer
        //to kth location from start.
        ListNode nthFromStart = head;
        for (int i=1; i<k; i++) {
            nthFromStart = nthFromStart.next;
        }

        //swap the values.
        int temp = nthFromEnd.val;
        nthFromEnd.val = nthFromStart.val;
        nthFromStart.val = temp;
        return head;
    }

    //Algorithm to get, nth node from the end.
    public ListNode getNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //two pointers approach.
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        //slow pointer starts at head.
        //fast pointer starts at `nth` node from start.
        for (int i=1; i<=n; i++) {
            fastPointer = fastPointer.next;
        }

        //prev pointer
        ListNode prevPointer = dummy;

        //as long as fast pointer doesn't cross
        //the last node, keep moving all the pointers
        while (fastPointer != null) {
            fastPointer = fastPointer.next;
            prevPointer = slowPointer; //save ref to prev node, which is needed for deleting the curr node.
            slowPointer = slowPointer.next;
        }

        return slowPointer;
    }
}
