package basics.linkedlist;

import java.util.Objects;

public class RemoveDuplicatesFromLinkedList {

    //assume linked list is sorted.
    static void removeDuplicates(SimpleLinkedList.Node<Integer>
                                  head) {
        SimpleLinkedList.Node<Integer> curr = head;
        while (curr != null && curr.getNextNode() != null) {
            if (Objects.equals(curr.getData(), curr.getNextNode().getData())) {
                curr.setNextNode(curr.getNextNode().getNextNode());
            } else curr = curr.getNextNode();
        }
        TraverseLinkedList traverseLinkedList = new TraverseLinkedList();
        traverseLinkedList.traverse(head);
    }

    public static void main(String[] args) {
        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(30);
        SimpleLinkedList.Node<Integer> node4 = new SimpleLinkedList.Node<>(30);
        SimpleLinkedList.Node<Integer> node5 = new SimpleLinkedList.Node<>(40);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(node4);
        node4.setNextNode(node5);
        node5.setNextNode(null);

        removeDuplicates(head);
    }
}
