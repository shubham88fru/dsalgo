package swd.linkedlist;

//@link - https://leetcode.com/problems/sort-list/description/
public class SortList {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    //Merge sort algorithm for linked list.
    private ListNode mergeSort(ListNode head) {
        //if head null or has just has one node, then it
        //is already sorted and we don't need to do anything.
        if (head == null || head.next == null) return head;

        //Divide the list in two halves.
        //Below method, modified head to point to left half
        //and returns a pointer to the right half.
        ListNode rightHalf = getRightHalf(head);

        //sort the left half.
        ListNode left = mergeSort(head);
        //sort the right half.
        ListNode right = mergeSort(rightHalf);

        //merge the two sorted halves.
        return mergeTwoSortedLists(left, right);
    }

    //Find the mid of the passed linkedlist,
    //break it into two halves and return the right half.
    private ListNode getRightHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode right = slow.next;
        slow.next = null;

        return right;
    }

    //Algorithms to merge two sorted lists l1 and l2 in descending order.
    private ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode ans = new ListNode(-1);
        ListNode dummy = ans;

        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                ans.next = new ListNode(curr1.val);
                curr1 = curr1.next;
            } else {
                ans.next = new ListNode(curr2.val);
                curr2 = curr2.next;
            }

            ans = ans.next;
        }

        if (curr1 != null) {
            ans.next = curr1;
        } else {
            ans.next = curr2;
        }

        return dummy.next;
    }
}
