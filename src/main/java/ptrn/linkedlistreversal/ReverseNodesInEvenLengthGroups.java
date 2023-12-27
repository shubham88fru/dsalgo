package ptrn.linkedlistreversal;

//@link - https://leetcode.com/problems/reverse-nodes-in-even-length-groups/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4561068799295488
public class ReverseNodesInEvenLengthGroups {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null) return null;

        ListNode curr = head.next;

        //Node 1 belongs to first batch
        //and has just one node, and so it can
        //never be even length. So, we'll start
        //from second group (node 2 and 3) onwards.
        int groupNum = 2;
        int start  = 2;
        int end = 3;

        //As long as we have nodes to process..
        while (curr != null) {
            //get a batch of k nodes.
            ListNodeWrapper2 batchInfo = getBatch(curr, start, end);

            //if batch of k nodes exist,
            //reverse the nodes in curr batch (start to end)
            if (batchInfo.batchExists)
                head = reverseBetween(head, batchInfo.start, batchInfo.end);

            //then, move to next batch
            start += groupNum; //start of every next group is prevEnd + prevGroupNum (by observation)
            end += groupNum +1; //end of every next group is prevEnd + prevGroupNum + 1 (by observation)
            groupNum += 1; //next group
            curr = batchInfo.next; //curr node pointer for next batch/group.
        }

        return head;
    }

    //Checks if we have a batch of k nodes.
    //if yes, returns batch exists and a pointer to next node.
    private ListNodeWrapper2 getBatch(ListNode startNode, int start, int end) {
        if (startNode == null) return null;

        int k = start;
        while (k <= end && startNode != null) {
            startNode = startNode.next;
            k += 1;
        }

        //If the number of nodes in the group are even and entire range is available
        //(no matter if the group is odd numbered or even numbered)
        //return the start & end
        if ((k == end+1) && (k-start)%2==0) return new ListNodeWrapper2(true, startNode, start, end);

            //Otherwise, if not all nodes from stat to end are available (maybe because the)
            //list ends prematurely, then check if the available node count is even.
            //And if it is, return the range.
        else if ((k-start)%2 ==0) return new ListNodeWrapper2(true, startNode, start, k-1);

        return new ListNodeWrapper2(false, startNode, 0, 0);
    }

    //Algorithm to reverse all the nodes from position left to right.
    private ListNode reverseBetween(ListNode head, int left, int right) {
        //if left and right same, dont need to do anything.
        if (left == right) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //standard 3 pointers for reverse a linked list.
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = curr.next;

        //to handle the repointing
        //of left node and left's prev node
        //after reversing.
        ListNode leftNode = head;
        ListNode leftPrev = dummy;

        int pos = 1;

        //till we reach right.
        while (pos < right) {
            //if crossed left..
            if (pos >= left) {
                //store left and its prev pointer
                //needed at the last stage.
                if (pos == left) {
                    leftNode = curr;
                    leftPrev = prev;
                }
                //..keep reversing.
                curr.next = prev;
            }

            //keep moving regardless.
            prev = curr;
            curr = next;
            next = next.next;

            pos += 1;
        }

        //at this point curr is pointing to
        //the right node. So, we do the final reverse.
        //and point the left node's next to right's next
        //and left's prev node to right node.
        curr.next = prev;
        leftNode.next = next;
        leftPrev.next = curr;

        //dummy.next points to head of
        //original list.
        return dummy.next;

    }
}

class ListNodeWrapper2 {
    boolean batchExists;
    ListNode next;
    int start;
    int end;

    public ListNodeWrapper2(boolean batchExists, ListNode next, int start, int end) {
        this.batchExists = batchExists;
        this.next = next;
        this.start = start;
        this.end = end;
    }
}
