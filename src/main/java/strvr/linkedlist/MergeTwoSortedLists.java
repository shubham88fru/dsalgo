package strvr.linkedlist;

//@link - https://leetcode.com/problems/merge-two-sorted-lists/description/
//@strvr - https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //return mergeTwoSortedListIterative1(list1, list2);
        //return mergeTwoSortedListIterative2(list1, list2);
        return mergeTwoSortedListInPlace(list1, list2);
    }

    //1) Optimal approach - constant space solution.
    //T: O(n1+n2), S: O(1)
    private ListNode mergeTwoSortedListInPlace(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        //we'll ensure that l1 always points to that node
        //which is smaller (i.e. l1.val smaller than l2.val)
        if (l1.val > l2.val) {
            ListNode temp = l2;
            l2 = l1;
            l1 = temp;
        }

        ListNode ans = l1;
        while (l1 != null && l2 != null) {
            ListNode smaller = null;
            //keep moving l1 and updating smaller
            //till it is smaller than l2
            while (l1 != null && l1.val <= l2.val) {
                smaller = l1;
                l1 = l1.next;
            }

            //l2 is bigger or l1 exhausted
            //update the link
            smaller.next = l2;

            //swap l1 so it becomes smaller again
            ListNode temp = l2;
            l2 = l1;
            l1 = temp;
        }

        return ans;
    }

    //2) Better approach, with dummy node.
    // T: O(n1 + n2), S: O(n1+n2)
    private ListNode mergeTwoSortedListIterative2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        //pointers to each list, starting from
        //each of their heads.
        ListNode currList1 = list1;
        ListNode currList2 = list2;

        //Uses a dummy node, this eliminates the need
        //of adding a check if head is null (because no matter what, we'll always have atleast the dummy node).
        //Adding the null check and creating a new node and pointing head
        //to it also breaks the binding and gives erroneous result
        //if we dont update our original head with the new head.
        //So, dummy node saves us from this hassel.
        ListNode dummyNode = new ListNode(-1);
        ListNode currFinalList = dummyNode;

        while (currList1 != null && currList2 != null) {
            if (currList1.val <= currList2.val) {
                currFinalList.next = new ListNode(currList1.val);
                currList1 = currList1.next;
            } else {
                currFinalList.next = new ListNode(currList2.val);
                currList2 = currList2.next;
            }
            currFinalList = currFinalList.next;
        }

        if (currList1 == null && currList2 != null) {
            currFinalList.next = currList2;
        } else currFinalList.next = currList1;

        //When using a dummy node, actual head of the list is
        //always dummy.next, since our actual list starts building
        //right after the dummy node.
        return dummyNode.next;
    }

    //2) Better approach, Without using dummy node.
    private ListNode mergeTwoSortedListIterative1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        ListNode currList1 = list1;
        ListNode currList2 = list2;
        ListNode finalList = null;

        //iterate simoultaneously over the two lists
        //untill atleast one of them is exhausted.
        while (currList1 != null && currList2 != null) {
            //if value in list one is greater, insert a new
            //node of curr value to end of the final list. And then
            //move to next node in list one.
            if (currList1.val <= currList2.val) {
                finalList = insertNodeAtEnd(finalList, currList1.val);
                currList1 = currList1.next;
            } else { //else do the same in list two.
                finalList = insertNodeAtEnd(finalList, currList2.val);
                currList2 = currList2.next;
            }
        }

        //At this point, it is possible that one of the list
        //might have exhausted but in other list may still
        //be pending to be processed. So, we which list has
        //pending nodes and we directly add the entire pending list
        //to end of the our final list.
        if (currList1 == null && currList2 != null) {
            finalList = insertListAtEnd(finalList, currList2);
        } else finalList = insertListAtEnd(finalList, currList1);

        return finalList;
    }

    private ListNode insertNodeAtEnd(ListNode head, int nodeToBeInserted) {
        if (head == null) {
            head = new ListNode(nodeToBeInserted);
            return head;
        }

        ListNode curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new ListNode(nodeToBeInserted);

        return head;
    }

    private ListNode insertListAtEnd(ListNode mainHead, ListNode nextHead) {
        if (mainHead == null) {
            if (nextHead != null) {
                mainHead = nextHead;
            }
            return mainHead;
        }

        ListNode curr = mainHead;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = nextHead;

        return mainHead;
    }
}
