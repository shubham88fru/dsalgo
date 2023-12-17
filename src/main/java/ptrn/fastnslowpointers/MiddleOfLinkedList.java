package ptrn.fastnslowpointers;

//@link - https://leetcode.com/problems/middle-of-the-linked-list/description/
//@strvr - https://takeuforward.org/data-structure/find-middle-element-in-a-linked-list/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5688723200278528
public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        return findViaFastAndSlowPointer(head);
    }

    //1) Optimal approach
    //T: O(N/2), S: (1)
    //Fast and slow pointer approach.
    private ListNode findViaFastAndSlowPointer(ListNode head) {
        if (head == null) return null;

        //moves at x speed
        ListNode slowPointer = head;

        //moves at 2x speed
        ListNode fastPointer = head;

        //as long as fastpointer is able to hop 2 nodes.
        while (fastPointer.next != null && fastPointer.next.next != null) {
            //hop 2 nodes as fastpointer.
            fastPointer = fastPointer.next.next;

            //hop 1 node as slow pointer.
            slowPointer = slowPointer.next;
        }


        //at this point, fastpointer is either at the last node
        //in which case, slowpointer would have covered half
        //the distance and hence would be pointing to the middle node.
        //Or, the fastpointer would be at second last node, from where it can't
        //hop 2 nodes. So, in this case, we'll have two middle nodes. As per
        //question, we have to return the second middle node, so return slowpoiter's next.
        if (fastPointer.next == null) return slowPointer;
        else return slowPointer.next;
    }

    //2) Brute force approach
    //count the num of nodes first and divide by to 2 and then iterate that
    //many times to reach to middle node.
    //T: O(N) + O(N/2), S: O(1)
}
