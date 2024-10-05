package ptrn.linkedlistreversal;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-list/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5565611402067968
public class SortList {
    public ListNode sortList(ListNode head) {
        return revise(head);
    }

    //////////////////////////sol 1.
    private ListNode revise(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;


        ListNode rightHalf = half(head);
        ListNode leftHalf = head;

        ListNode left = mergeSort(leftHalf);
        ListNode right = mergeSort(rightHalf);
        return mergeSortedLists(left, right);
    }

    private ListNode half(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        slow.next = null;
        return right;
    }

    //Merge two sorted lists without using a third list.
    private ListNode mergeSortedLists(ListNode a, ListNode b) {
        ListNode currPA = a;
        ListNode currPB = b;
        while (currPA != null) {
            currPA = currPA.next;
        }


        while (currPB != null) {
            currPB = currPB.next;
        }


        ListNode dummy = new ListNode(-1);
        dummy.next = b;
        ListNode prev = dummy;
        ListNode currA = a;
        ListNode currB = b;

        while (currA != null && currB != null) {
            if (currA.val < currB.val) {
                ListNode nextA = currA.next;
                prev.next = currA;
                currA.next = currB;
                prev = currA;
                currA = nextA;
            } else {
                prev.next = currB;
                prev = currB;
                ListNode nextB = currB.next;
                currB.next = currA;
                currB = nextB;
            }
        }
        return dummy.next;
    }
    /////////////////////////sol1.

    //////////////////////////sol2.
    //uses extra space for the heap.
    private ListNode revise2(ListNode head) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        ListNode dummy = new ListNode(-1);
        ListNode curr = head;

        while (curr != null) {
            minHeap.add(curr);
            ListNode prev = curr;
            curr = curr.next;
            prev.next = null;
        }

        curr = dummy;
        while (!minHeap.isEmpty()) {
            curr.next = minHeap.remove();
            curr = curr.next;
        }

        return dummy.next;

    }
    //////////////////////////sol2.


    ///////////////////////////sol3.
    //Merge sort algorithm for linked list.
    private ListNode mergeSort2(ListNode head) {
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
    //////////////////////////////sol3.
}
