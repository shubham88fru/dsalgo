package strvr.linkedlist2;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/intersection-of-two-linked-lists/description/
//@strvr - https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //return findIntersectionBrute(headA, headB);
        //return findIntersectionOptimal2(headA, headB);
        return findIntersectionOptimal1(headA, headB);
    }

    //1) Optimal solution 1
    //T: O(N+M), S: O(1)
    private ListNode findIntersectionOptimal1(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;

        while (currA != null || currB != null) {
            if (currA == currB) return currA;

            if (currA == null) {
                currA = headB;
                continue;
            } else if (currB == null) {
                currB = headA;
                continue;
            }

            currA = currA.next;
            currB = currB.next;
        }

        return null;
    }

    //2) Optimal solution 2
    //T: O(N+M), S: O(1)
    private ListNode findIntersectionOptimal2(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;

        //get lengths of each list
        int lenA = 0;
        int lenB = 0;
        while (currA != null) {
            currA = currA.next;
            lenA += 1;
        }
        while (currB != null) {
            currB = currB.next;
            lenB += 1;
        }

        //reset the pointers back to head.
        currA = headA;
        currB = headB;

        //find the diff in length
        int lenDiff = Math.abs(lenA-lenB);

        //if listA is bigger,
        //move its pointer, by difference of
        //the length ahead. Otherwise, if listB is bigger
        //move B's pointer by lenDiff ahead.
        if (lenA > lenB) {
            while (lenDiff != 0) {
                currA = currA.next;
                lenDiff -= 1;
            }
        } else if (lenB > lenA) {
            while (lenDiff != 0) {
                currB = currB.next;
                lenDiff -= 1;
            }
        }

        //once the two pointers are at same level,
        //start moving them together, if there is an
        //intersection, the pointers will point to the same
        //node at some time.
        while (currA != null && currB != null) {
            if (currA == currB) return currA;
            currA = currA.next;
            currB = currB.next;
        }

        //no intersection
        return null;
    }

    //3) Better soln
    //T: O(N+M), S: O(N)
    private ListNode findIntersectionBetter(ListNode headA, ListNode headB) {
        Set<ListNode> seen = new HashSet<>();

        ListNode currA = headA;
        while (currA != null) {
            seen.add(currA);
            currA = currA.next;
        }

        ListNode currB = headB;
        while (currB != null) {
            if (seen.contains(currB)) return currB;
            currB = currB.next;
        }

        return null;
    }

    //4) Brute force
    //T: O(M*N), S: (1)
    //compare each node of listB with every node of listA
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
