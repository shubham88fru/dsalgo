package ptrn.kwaymerge;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/merge-k-sorted-lists/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4807159151067136
//       - https://www.youtube.com/watch?v=Q64u-W3l3mA&ab_channel=codestorywithMIK
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // return brute(lists);
        // return pq(lists);
        // return pq2(lists);
        return optimal(lists);
    }

    /**
     TC wise its the same as the optimized
     priority queue approach, however, the
     plus point is that it doesn't take
     extra space unlike the priority queue
     approach.
     */
    private ListNode optimal(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeSort(lists, 0, lists.length-1);
    }

    private ListNode mergeSort(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];

        int mid = l + (r-l)/2;
        ListNode l1 = mergeSort(lists, l, mid);
        ListNode l2 = mergeSort(lists, mid+1, r);

        /**
         This is the merge step of
         merge sort algo. Can be
         iterative or recursive.
         The recursive merge is very
         confusing, so using iterative
         merge.
         */
        return mergeTwoSortedLists(l1, l2);
    }

    private ListNode mergeRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        }

        l2.next = mergeRecursive(l1, l2.next);
        return l2;
    }

    /**
     Optimized pq approach. Instead of blindly
     putting all elements to pq, we only put
     the heads of each list to start, and then
     keep moving pointers. This way, there are
     at max only k elements in pq (where k is the)
     size of lists[]) at any time.
     Therefore, TC is nlog(k), where n is total num
     of nodes across all the lists.
     */
    private ListNode pq2(ListNode[] lists) {
        if (lists == null || lists.length==0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        int n = lists.length;
        for (int i=0; i<n; i++) {
            ListNode head = lists[i];
            if (head != null) pq.add(head);
        }

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.remove();
            p.next = node;
            p = node;
            if (node.next != null) pq.add(node.next);
        }

        return dummy.next;

    }

    /**
     A dumb pq approach. Blindly put all
     nodes in the pq and then poll.

     TC: nlog(n) where n is total number of
     elements across all lists[]
     */
    private ListNode pq(ListNode[] lists) {
        if (lists == null || lists.length==0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        int n = lists.length;
        for (int i=0; i<n; i++) {
            ListNode head = lists[i];
            while (head != null) {
                pq.add(head);
                ListNode next = head.next;
                head.next = null;
                head = next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            ListNode next = pq.remove();
            p.next = next;
            p = next;
        }

        return dummy.next;

    }

    /**
     Merge 2 lists successively.
     TC: O(k*n), where k is number of
     lists and n is the total number
     of nodes across all lists.
     */
    private ListNode brute(ListNode[] lists) {
        if (lists == null || lists.length==0) return null;

        int n = lists.length;
        ListNode merged = lists[0];
        for (int i=1; i<n; i++) {
            merged = mergeTwoSortedLists(merged, lists[i]);
        }

        return merged;
    }

    private ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                ListNode next = l1.next;
                l1.next = l2;
                p = l1;
                l1 = next;
            } else {
                p.next = l2;
                ListNode next = l2.next;
                l2.next = l1;
                p = l2;
                l2 = next;
            }
        }

        return dummy.next;
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
