package basics.linkedlist;

public class SimpleLinkedList {

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
        }

        public Node<T> getNextNode() {
            return this.nextNode;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(10);
        Node<Integer> node2 = new Node<>(20);
        Node<Integer> node3 = new Node<>(30);

        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(null);
    }
}
