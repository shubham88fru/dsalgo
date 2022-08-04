package basics.linkedlist;

public class TraverseLinkedList {

    void traverse(SimpleLinkedList.Node<Integer> head) {
        SimpleLinkedList.Node<Integer> curr = head;

        while(curr!=null) {
            System.out.println(curr.getData());
            curr = curr.getNextNode();
        }
    }

    public static void main(String[] args) {
        TraverseLinkedList traverseLinkedList = new TraverseLinkedList();
        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node1 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(30);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(40);

        head.setNextNode(node1);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);

        traverseLinkedList.traverse(head);
    }
}
