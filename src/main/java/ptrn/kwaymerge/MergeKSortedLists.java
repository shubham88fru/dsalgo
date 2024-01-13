package ptrn.kwaymerge;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/merge-k-sorted-lists/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4807159151067136
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        //return kindaBrute(lists);
        return better(lists);
    }

    //Using heaps.
    private ListNode better(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        //Put all the first nodes of each list
        //into the heap.
        for (ListNode list: lists) {
            if (list == null) continue;
            minHeap.add(list);
        }

        //untill all nodes processed, keep
        //picking the minimum and moving next
        //in the list which had the minimum.
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.remove();
            curr.next = min;
            curr = curr.next;

            //If the list that gave min in this iteration
            //has next, push it to the minHeap.
            if (min.next != null) minHeap.add(min.next);
            min.next = null;
        }

        return dummy.next;
    }

    private ListNode kindaBrute(ListNode[] lists) {
        int n = lists.length;
        //ATQ.
        if (n == 0) return null;

        //Keep merging in pairs successively.
        ListNode ans = lists[0];
        for (int i=1; i < n; i++) {
            ans = mergeBrute(ans, lists[i]);
        }
        return ans;
    }

    private ListNode mergeBrute(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);

        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode curr3 = dummy;
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                //If element of list1 is smallest,
                //add it to end of ans list and move
                //to next element.
                ListNode next = curr1.next;
                curr3.next = curr1;
                curr3 = curr3.next;

                curr1.next = null;
                curr1 = next;
            } else {
                //otherwise, if list2's element is
                //smaller, add it to and list and move
                //to the next element.
                ListNode next = curr2.next;
                curr3.next = curr2;
                curr3 = curr3.next;

                curr2.next = null;
                curr2 = next;
            }
        }

        //Finally, add any remaining
        //elements in any of the lists
        //to the ans list.
        if (curr1 != null) {
            curr3.next = curr1;
        } else {
            curr3.next = curr2;
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
