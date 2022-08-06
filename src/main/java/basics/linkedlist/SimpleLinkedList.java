package basics.linkedlist;

public class SimpleLinkedList {

    static int size = 0;
    public static class Node<T> {
        private T data;
        private Node<T> nextNode;

        public Node(T data) {
            this.data = data;
            this.nextNode = null;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
            SimpleLinkedList.size++;
        }

        public Node<T> getNextNode() {
            return this.nextNode;
        }

    }

    public static Node<Integer> insertAtStart(Node<Integer> head, int val) {
        Node<Integer> newNode = new Node<>(val);
        newNode.setNextNode(head);
        return newNode;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(10);
        Node<Integer> node2 = new Node<>(20);
        Node<Integer> node3 = new Node<>(30);

        head.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);

        System.out.println("====================");
        head = insertAtStart(head, 5);
        TraverseLinkedList traverseLinkedList = new TraverseLinkedList();
        traverseLinkedList.traverse(head);
        System.out.println("====================");
        head = insertAtStart(head, 23);
        traverseLinkedList.traverse(head);
        System.out.println("====================");
        head = insertAtStart(head, 50);
        System.out.println("====================");
        traverseLinkedList.traverse(head);


    }
}
