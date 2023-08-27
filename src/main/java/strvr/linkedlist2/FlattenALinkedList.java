package strvr.linkedlist2;

//@link - https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
//@strvr - https://takeuforward.org/data-structure/flattening-a-linked-list/
public class FlattenALinkedList {
    Node flatten(Node root) {
        return flattenList(root);
    }

    //T: O(N), S: O(1)
    //keep merging sorted lists.
    private Node flattenList(Node root) {
        if (root == null) return null;

        //return current root merged with the flattened rest.
        return mergeSortedLists(root, flattenList(root.next));
    }

    private Node mergeSortedLists(Node root, Node next) {
        if (next == null) return root;

        Node dummy = new Node(-1);
        Node curr = dummy;
        Node p1 = root;
        Node p2 = next;

        while (p1 != null && p2 != null) {
            if (p1.data > p2.data) {
                //could do curr.bottom = new Node(p2.data) also
                //but that's un-necessary and adds extra space complexity.
                curr.bottom = p2;
                p2 = p2.bottom;
            } else {
                curr.bottom = p1;
                p1 = p1.bottom;
            }

            curr = curr.bottom;

        }

        if (p1 != null) {
            curr.bottom = p1;
        } else if (p2 != null) {
            curr.bottom = p2;
        }

        return dummy.bottom;
    }
}

class Node {
    int data;
    Node next;
    Node bottom;

    Node(int d)
    {
        data = d;
        next = null;
        bottom = null;
    }
}
