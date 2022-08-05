package basics.linkedlist;

public class SearchInLinkedList {

    int search(SimpleLinkedList.Node<Integer> head, int key) {
        SimpleLinkedList.Node<Integer> curr = head;
        int pos = 0;
        while(curr!=null) {
            if (curr.getData() == key) {
                return pos;
            }
            curr = curr.getNextNode();
            pos++;
        }
        return -1;
    }

    int searchRecursively(SimpleLinkedList.Node<Integer> node, int key, int pos) {
        SimpleLinkedList.Node<Integer> curr = node;
        if (curr==null) return -1;
        if (curr.getData() == key) return pos;
        pos++;
        curr = curr.getNextNode();
        return searchRecursively(curr, key, pos);
    }

    public static void main(String[] args) {
        SearchInLinkedList linkedList = new SearchInLinkedList();

        SimpleLinkedList.Node<Integer> head = new SimpleLinkedList.Node<>(10);
        SimpleLinkedList.Node<Integer> node1 = new SimpleLinkedList.Node<>(20);
        SimpleLinkedList.Node<Integer> node2 = new SimpleLinkedList.Node<>(30);
        SimpleLinkedList.Node<Integer> node3 = new SimpleLinkedList.Node<>(40);

        head.setNextNode(node1);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);

        System.out.println(linkedList.search(head, 10));
        System.out.println(linkedList.search(head, 20));
        System.out.println(linkedList.search(head, 40));
        System.out.println(linkedList.search(head, 4));

        System.out.println("-----------------------");

        System.out.println(linkedList.searchRecursively(head, 10, 0));
        System.out.println(linkedList.searchRecursively(head, 20, 0));
        System.out.println(linkedList.searchRecursively(head, 40, 0));
        System.out.println(linkedList.searchRecursively(head, 4, 0));
    }
}
