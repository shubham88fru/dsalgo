package basics.linkedlist;

public class MiddleOfSinglyLinkedList {

    static Integer findMiddle(SimpleLinkedList.Node<Integer> head) {
        int middle = SimpleLinkedList.size() / 2;
        SimpleLinkedList.Node<Integer> curr = head;
        for (int i=0;i<middle;i++) curr = curr.getNextNode();
        return curr.getData();
    }

    void findMiddleEfficient(SimpleLinkedList.Node<Integer> head) {
        if (head == null) return;
        SimpleLinkedList.Node<Integer> slow = head, fast= head;
        while (fast!=null && fast.getNextNode()!=null) {
            slow = slow.getNextNode(); //slow moves one node at a time
            fast = fast.getNextNode().getNextNode(); //fast moves 2 nodes at a time.
        }
        System.out.println(slow.getData());
    }

    public static void main(String[] args) {
        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(30);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);
        System.out.println(findMiddle(head));

        head = new SimpleLinkedList.Node<>(10);
        node2 = new SimpleLinkedList.Node<>(5);
        node3 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node4 = new SimpleLinkedList.Node<>(15);
        SimpleLinkedList.Node<Integer> node5 = new SimpleLinkedList.Node<>(25);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(node4);
        node4.setNextNode(node5);
        node5.setNextNode(null);
        System.out.println(SimpleLinkedList.size());

    }
}
