package swd.linkedlist;

//@link - https://leetcode.com/problems/add-two-numbers-ii/description/
public class AddTwoNumsII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //reverse given input lists, so that digits are stored
        //from less significant to more significant. This makes
        //the problem easier because addition is performed from
        //LSD end to MSD end.
        l1 = reverse(l1);
        l2 = reverse(l2);

        //Sum the two lists.
        ListNode summed = sum(l1, l2);

        //reverse the summed list, since it is stored
        //as LSD to MSD, while the question requires the nums
        //to be MSD to LSD.
        return reverse(summed);
    }

    // Sum two given lists l1 and l2.
    private ListNode sum(ListNode l1, ListNode l2) {
        ListNode currL1 = l1;
        ListNode currL2 = l2;

        //Dummy node to represent resultant list.
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int carry = 0;

        //while both lists have elements.
        while(currL1 != null && currL2 != null) {
            //sum the elements respecting carry if any from prev element sum.
            int sum = carry + currL1.val + currL2.val;

            //carry can be at max 1 (9+9) = 18 (1 carry)
            if (sum >= 10) {
                sum = sum%10;
                carry = 1;
            } else {
                carry = 0; //No carry if sum is single digit.
            }

            //next elements for addition
            currL1 = currL1.next;
            currL2 = currL2.next;

            //add a node with sum
            curr.next = new ListNode(sum);

            //next for adding next sum.
            curr = curr.next;
        }

        //if any nodes pending in list 1,
        //add them with prev carry if any.
        while (currL1 != null) {
            int sum = carry + currL1.val;
            if (sum >= 10) {
                sum = sum%10;
                carry = 1;
            } else {
                carry = 0;
            }

            currL1 = currL1.next;
            curr.next = new ListNode(sum);
            curr = curr.next;
        }

        //if any nodes pending in list 2,
        //add them with prev carry if any.
        while (currL2 != null) {
            int sum = carry + currL2.val;
            if (sum >= 10) {
                sum = sum%10;
                carry = 1;
            } else {
                carry = 0;
            }

            currL2 = currL2.next;
            curr.next = new ListNode(sum);
            curr = curr.next;
        }

        //Finally, if still a carry is left,
        //add it in a new node.
        if (carry != 0) {
            curr.next = new ListNode(1);
            curr = curr.next;
        }

        return dummy.next;
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
