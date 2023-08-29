package strvr.linkedlist2;

//@link - https://leetcode.com/problems/rotate-list/description/
//@strvr - https://takeuforward.org/data-structure/rotate-a-linked-list/
public class RotateALinkedList {
    public ListNode rotateRight(ListNode head, int k) {
        // rotateBetter(head, k, size(head));
        // return head;
        return rotateOptimal(head, k);
    }

    //1) Optimal soln. T: O(N), S: O(1)
    private ListNode rotateOptimal(ListNode head, int k) {
        if (head == null || k == 0) return head;

        //find the size.
        ListNode curr = head;
        int size = 1; //hear means list has alrady one element
        while (curr.next != null) { //going only till last node (not beyond)
            curr = curr.next;
            size += 1;
        }
        //at the last node, point it to the head.
        curr.next = head;

        //rotating k times means, last k nodes will
        //come in front and n-kth node from the start (in oringial)
        //will become the new last node.
        int newLastNodePos = (size - (k%size)); //only need to rotate k%size
        ListNode newLastNode = null;
        while (newLastNodePos != 0) {
            curr = curr.next;
            newLastNodePos -= 1;
        }

        //store the new last node and
        //fix the last node's next to null.
        ListNode newHead = curr.next;
        curr.next = null;

        return newHead;
    }

    //2) Better soln.
    private void rotateBetter(ListNode head, int k, int n) {
        if (head == null || head.next == null) return;

        //no need to rotate k time.
        //if length of list is n, it will
        //only have n different shifts, beyond n,
        //it will simply be a repetition.
        k = k%n;
        while (k != 0) {
            int prev = -1;
            ListNode curr = head;
            int temp = -1;
            //keep changing curr node's
            //value to prev node.
            while (curr != null) {
                temp = curr.val;
                curr.val = prev;
                prev = temp;
                curr = curr.next;
            }
            //and finally at end of each rotation
            //change the value of head with the
            //values of last node.
            head.val = temp;

            k -= 1;
        }
    }


    //Find the size of list.
    private int size(ListNode head) {
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            curr = curr.next;
            size += 1;
        }
        return size;
    }
}
