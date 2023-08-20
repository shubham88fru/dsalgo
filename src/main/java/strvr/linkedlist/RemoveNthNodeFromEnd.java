package strvr.linkedlist;

//@link - https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
//@strvr - https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/
public class RemoveNthNodeFromEnd {

    //1) Optimal approach
    //T: O(N), S: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
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

        //at this point slow pointer is pointing at the `nth` node
        //from the end. So delete it.
        ListNode nextNode = slowPointer.next;
        prevPointer.next = nextNode;

        //dummy next points to head of the linked list.
        return dummy.next;
    }

    //2) Brute force
    //Find the length of the list, and then do another iteration
    //to delete length-nth node from front.
    //T: O(N), S: O(1)
}
