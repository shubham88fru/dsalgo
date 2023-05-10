package swd.linkedlist;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/linked-list-cycle/description/
public class DetectCycleInALinkedList {
    public boolean hasCycle(ListNode head) {
        //return hasCycleBruteForce(head);
        return hasCycleOptimized(head);
    }

    //1) Optimized approach - T: O(1), S: O(1)
    //Using fast and slow pointer approach.
    private boolean hasCycleOptimized(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        //till fast pointer can hop..
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next; //..hop 2 nodes.
            slow = slow.next; //..hop 1 node.

            //if there's a cycle, the pointer
            //running twice the speed, will see
            //the slow pointer again.
            if (fast == slow) return true;
        }

        return false;
    }

    //2) Brute force approach - T: O(1), S: O(N)
    private boolean hasCycleBruteForce(ListNode head) {
        if (head == null) return false;

        Map<ListNode, Integer> seen = new HashMap<>();

        ListNode curr = head;
        while (curr != null) {
            if (seen.containsKey(curr)) return true;
            //Note we hash the entire node object
            //and not just its value. The list may
            //contains multiple nodes with same value.
            //so can't just store the value in map and see
            //if it exists.
            seen.put(curr, curr.val);
            curr = curr.next;
        }

        return false;
    }
}
