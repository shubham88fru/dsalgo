package basics.linkedlist;

public class NthNodeFromEnd {

    static void printNthNodeFromEnd(SimpleLinkedList.Node<Integer> head, int n) {
        int i =0;
        SimpleLinkedList.Node<Integer> curr = head;
        while (curr != null) {
            curr = curr.getNextNode();
            i++;
        }
        if (i<n) return;
        int posFromHead = i - n + 1;
        curr = head;
        for (int j=1; j<posFromHead; j++) {
            curr = curr.getNextNode();
        }
        System.out.println(curr.getData());
    }

    static void printNthNode2Pointer(SimpleLinkedList.Node<Integer> head, int n) {
        if (head == null) return;
        SimpleLinkedList.Node<Integer> first = head;
        SimpleLinkedList.Node<Integer> second = head; //second starts at head.
        for (int i =0;i<n;i++) {
            if (first == null) return;
            first = first.getNextNode();
        }
        //first and second move 1 each.
        while (first!=null) {
            first = first.getNextNode();
            second = second.getNextNode();
        }
        System.out.println(second.getData());
    }

    public static void main(String[] args) {
        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(30);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);
        printNthNodeFromEnd(head, 4);
    }
}
