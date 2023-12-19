package ptrn.fastnslowpointers;

//@link - https://leetcode.com/problems/palindrome-linked-list/description/
//@strvr - https://takeuforward.org/data-structure/check-if-given-linked-list-is-plaindrome/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4806672477585408
public class PalindromeLinkedList {
    //T: O(N), S: O(1)
    public boolean isPalindrome(ListNode head) {
        //Find middle node, returning the first middle node
        //in case there are multiple middle nodes.
        ListNode middle = findMidViaFastAndSlowPointer(head);

        //Reverse the right half.
        ListNode rightHalfReversed = reverse(middle.next);

        //setting middle.next to null breaks
        //it away from right half so we can get
        //hold of left half exclusively.
        middle.next = null;
        ListNode leftHalf = head;

        //In case of a linkedlist with odd no. of elements,
        //the left half will have the middle node, while the right half wont.
        //but it doesn't matter, because our compare algorithm will compare only
        //till one of the list is exhausted.
        //When the input linkedlist is with even no. of elements, then anyways our
        //spli (left and right) will have equal lengths.
        return compare(leftHalf, rightHalfReversed);
    }

    private boolean compare(ListNode l1, ListNode l2) {
        ListNode currL1 = l1;
        ListNode currL2 = l2;

        //keep iterating till one list is exhausted.
        //in case of even length original list, both halfs
        //will have same length. While in case of odd length
        //list, left half will have the middle node.
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;

            l1 = l1.next;
            l2 = l2.next;
        }

        return true;
    }


    //Find middle of node.
    private ListNode findMidViaFastAndSlowPointer(ListNode head) {
        if (head == null) return null;

        //moves at x speed
        ListNode slowPointer = head;

        //moves at 2x speed
        ListNode fastPointer = head;

        //as long as fastpointer is able to hop 2 nodes.
        while (fastPointer.next != null && fastPointer.next.next != null) {
            //hop 2 nodes as fastpointer.
            fastPointer = fastPointer.next.next;

            //hop 1 node as slow pointer.
            slowPointer = slowPointer.next;
        }


        //here, we want to return first middle node for this question
        return slowPointer;
    }

    //reverse a linked list.
    private ListNode reverse(ListNode head) {
        if (head == null) return null;

        //start with 3 pointers
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;

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

        return prev;
    }
}
