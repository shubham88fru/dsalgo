package strvr.linkedlistandarray;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/copy-list-with-random-pointer/description/
//@strvr - https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
public class CopyListWithRandomPointers {
    public Node2 copyRandomList(Node2 head) {
        //return copyRandomListBrute(head);
        return copyRandomListOptimal(head);
    }

    //1) Optimal approach: T: O(3N), S: O(1)
    private Node2 copyRandomListOptimal(Node2 head) {
        Node2 iter = head;
        Node2 front = head;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            front = iter.next;

            Node2 copy = new Node2(iter.val);
            iter.next = copy;
            copy.next = front;

            iter = front;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        Node2 pseudoHead = new Node2(0);
        Node2 copy = pseudoHead;

        while (iter != null) {
            front = iter.next.next;

            // extract the copy
            copy.next = iter.next;
            copy = copy.next;

            // restore the original list
            iter.next = front;

            iter = front;
        }

        return pseudoHead.next;
    }

    //2) Brute force approach: T: O(2N), S: O(N)
    //Keep copy of every node in first iteration orig list
    //and in second iteration, start linking the copies based
    //on how they're linked in original.
    private Node2 copyRandomListBrute(Node2 head) {
        Map<Node2, Node2> origVsCopy = new HashMap<>();

        Node2 curr = head;
        //keep the copies.
        while (curr != null) {
            origVsCopy.put(curr, new Node2(curr.val));
            curr = curr.next;
        }

        //reset for second iteration.
        curr = head;
        Node2 newList = origVsCopy.get(curr); //head's copy
        while (curr != null) {
            //link the copies.
            //new next is copy of orig next.
            newList.next = origVsCopy.get(curr.next);
            //new random is copy of orig random.
            newList.random = origVsCopy.get(curr.random);
            curr = curr.next;
            newList = newList.next;
        }

        //new list's copy is nothing but
        //copy of orig head.
        return origVsCopy.get(head);
    }
}

class Node2 {
    int val;
    Node2 next;
    Node2 random;

    public Node2(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}