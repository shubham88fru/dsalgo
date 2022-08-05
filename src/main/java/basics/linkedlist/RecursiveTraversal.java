package basics.linkedlist;

public class RecursiveTraversal {

    void recursivelyTraverse(SimpleLinkedList.Node<Integer> node) {
        SimpleLinkedList.Node<Integer> curr = node;
        if (curr == null) {
            return;
        }
        System.out.println(curr.getData());
        curr = node.getNextNode();
        recursivelyTraverse(curr);
    }

    public static void main(String[] args) {
        RecursiveTraversal recursiveTraversal = new RecursiveTraversal();

        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node1 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(30);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(40);

        head.setNextNode(node1);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);

        recursiveTraversal.recursivelyTraverse(head);
    }
}
