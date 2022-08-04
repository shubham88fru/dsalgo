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
    }
}
