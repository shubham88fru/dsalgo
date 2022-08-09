package basics.linkedlist;

import java.util.ArrayList;

public class ReverseSinglyLinkedList {

    //Requires 2 traversals
    //extra space
    static SimpleLinkedList.Node<Integer> reverseNaive(SimpleLinkedList.Node<Integer> head) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (SimpleLinkedList.Node<Integer> curr = head;
             curr != null; curr = curr.getNextNode()) {
            arr.add(curr.getData());
        }
        for (SimpleLinkedList.Node<Integer> curr = head;
             curr != null; curr = curr.getNextNode()) {
            curr.setData(arr.remove(arr.size() - 1));
        }
        return head;
    }

    //T: O(N), S:O(1)
    static SimpleLinkedList.Node<Integer> reverse(SimpleLinkedList.Node<Integer> head) {
        SimpleLinkedList.Node<Integer> curr = head;
        SimpleLinkedList.Node<Integer> prev = null;

        while (curr!=null) {
            SimpleLinkedList.Node<Integer> next;
            next = curr.getNextNode();
            curr.setNextNode(prev);
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static SimpleLinkedList.Node<Integer>
            recursiveReverse1(SimpleLinkedList.Node<Integer> head) {
        if (head == null || head.getNextNode() == null) return head;
        SimpleLinkedList.Node<Integer> rest_head = recursiveReverse1(head.getNextNode());
        SimpleLinkedList.Node<Integer> rest_tail = head.getNextNode();
        rest_tail.setNextNode(head);
        head.setNextNode(null);
        return rest_head;
    }

    public static void main(String[] args) {
        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(30);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);

        head = reverseNaive(head);
        TraverseLinkedList traverseLinkedList = new TraverseLinkedList();
        traverseLinkedList.traverse(head);
        System.out.println("--------------------");
        head = reverse(head);
        traverseLinkedList.traverse(head);
        System.out.println("--------------------");
        head = recursiveReverse1(head);
        traverseLinkedList.traverse(head);
    }
}
