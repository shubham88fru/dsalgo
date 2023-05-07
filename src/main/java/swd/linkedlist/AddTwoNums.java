package swd.linkedlist;

//@link - https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNums {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return sol1(l1, l2);
    }

    //1) Solution 1: Using dummy node concept
    private ListNode sol1(ListNode l1, ListNode l2) {
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

    //2) Solution 2: Without using dummy node.
    private ListNode sol2(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode currL1 = l1;
        ListNode currL2 = l2;
        ListNode currAns = ans;
        int carry = 0;
        while (currL1 != null && currL2 != null) {
            int sum = carry + currL1.val + currL2.val;
            if (sum>=10) {
                currAns.val = sum%10;
                carry = 1;
            } else {
                carry = 0;
                currAns.val = sum;
            }
            currL1 = currL1.next;
            currL2 = currL2.next;

            if (currL1 !=null || currL2 != null) {
                currAns.next = new ListNode();
                currAns = currAns.next;
            }
        }

        while (currL1 != null) {
            carry = addRemaining(currAns, currL1, carry);
            currL1 = currL1.next;

            if (currL1 != null) {
                currAns.next = new ListNode();
                currAns = currAns.next;
            }
        }

        while (currL2 != null) {
            carry = addRemaining(currAns, currL2, carry);
            currL2 = currL2.next;

            if (currL2 != null) {
                currAns.next = new ListNode();
                currAns = currAns.next;
            }
        }

        if (carry != 0) {
            currAns.next = new ListNode();
            currAns = currAns.next;
            currAns.val = carry;
        }

        return ans;
    }

    private int addRemaining(ListNode currAns, ListNode currNode, int carry) {

        int sum = carry + currNode.val;
        if (sum>=10) {
            currAns.val = sum%10;
            carry = 1;
        } else {
            carry = 0;
            currAns.val = sum;
        }

        return carry;
    }
}
