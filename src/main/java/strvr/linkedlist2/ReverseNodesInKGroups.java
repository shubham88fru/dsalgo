package strvr.linkedlist2;

//@link - https://leetcode.com/problems/reverse-nodes-in-k-group/description/
//@strvr - https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/
public class ReverseNodesInKGroups {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode curr = head;
        int start  = 1;
        int end = k;

        //As long as we have nodes to process..
        while (curr != null) {
            //get a batch of k nodes.
            ListNodeWrapper batchInfo = getBatch(curr, k);

            //if batch of k nodes exist,
            //reverse the nodes in curr batch (start to end)
            if (batchInfo.batchExists)
                head = reverseBetween(head, start, end);

            //then, move to next batch
            start += k;
            end += k;
            curr = batchInfo.next;
        }

        return head;
    }

    //Checks if we have a batch of k nodes.
    //if yes, returns batch exists and a pointer to next node.
    private ListNodeWrapper getBatch(ListNode startNode, int k) {
        if (startNode == null) return null;

        while (k != 0 && startNode != null) {
            startNode = startNode.next;
            k -= 1;
        }

        if (k == 0) return new ListNodeWrapper(true, startNode);

        return new ListNodeWrapper(false, null);
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

class ListNodeWrapper {
    boolean batchExists;
    ListNode next;

    public ListNodeWrapper(boolean batchExists, ListNode next) {
        this.batchExists = batchExists;
        this.next = next;
    }
}