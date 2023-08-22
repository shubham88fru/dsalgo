package strvr.linkedlist1;

//@link - https://leetcode.com/problems/delete-node-in-a-linked-list/description/
//@strvr - https://takeuforward.org/data-structure/delete-given-node-in-a-linked-list-o1-approach/
public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        //deleteNode1(node);
        deleteNode2(node);
    }

    //1) TC: O(N)
    private void deleteNode1(ListNode node) {
        if (node == null) return;

        ListNode curr = node;

        //continously replace curr node's
        //val with its next nodes node
        //and finally set the last node to null.
        while (node != null) {
            ListNode nextNode = node.next;
            node.val = nextNode.val;

            //node.next will never be null
            //as per question `node` is never going
            //to be null.
            if (node.next.next == null) {
                node.next = null;
            }
            node = node.next;
        }
    }

    //2) TC: O(1)
    private void deleteNode2(ListNode node) {
        if (node == null) return;

        /*
            list = 4 -> 5 -> 1 -> 9
            node = 5
        */

        //next node of the node to be deleted.
        ListNode nextNode = node.next;  //  nextNode = 1

        //next to next node of the node to be deleted.
        ListNode nextNextNode = nextNode.next; // nextNextNode = 9

        //exchagne node to be deleted's value with next node's value.
        node.val = nextNode.val; // node.val = 1 [list = 4 -> 1 -> 1 -> 9]

        //set its value to next to next node.
        node.next = nextNextNode; // node.next = 9 [list = 4 -> 1 -> 9 -> 9]

        //remove the next node.
        nextNode = null; //nextNode = null [list = 4 -> 1 -> 9]
    }
}
