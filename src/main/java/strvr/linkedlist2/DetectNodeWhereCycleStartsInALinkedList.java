package strvr.linkedlist2;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/linked-list-cycle-ii/description/
//@strvr - https://takeuforward.org/data-structure/starting-point-of-loop-in-a-linked-list/
public class DetectNodeWhereCycleStartsInALinkedList {

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            //return findCycleStartBrute(head);
            return findCycleStartOptimal(head);
        }

        //1) Optimal Soln - T: O(N), S: (1)
        //Using 3 pointers.
        private ListNode findCycleStartOptimal(ListNode head) {
            if (head == null) return null;

            ListNode start = head;
            ListNode slow = head;
            ListNode fast = head;

            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                //means there is certainly a cycle.
                //when cycle detected, start moving
                //slow and start together untill they meet.
                if (fast == slow) return findStart(slow, start);
            }

            //no cycle.
            return null;
        }

        private ListNode findStart(ListNode slow, ListNode start) {

            //when there is certainly a cycle,
            //a if pointer starting from head and another
            //starting from the juncture of fast and slow pointer
            //start moving together, they are boung to meet at the
            //node where the cycle started -- kinda memorize this :D
            while (slow != start) {
                slow = slow.next;
                start = start.next;
            }

            return slow;
        }

        //2) Brute force - T: O(N), S: O(N)
        //Using a hash/set
        private ListNode findCycleStartBrute(ListNode head) {
            Set<ListNode> st = new HashSet<>();
            ListNode curr = head;

            while (curr != null) {
                if (st.contains(curr)) return curr;

                st.add(curr);
                curr = curr.next;
            }

            return null;
        }
    }
}
