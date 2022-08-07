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

    //T:O(1)
    public static Node<Integer> insertAtStart(Node<Integer> head, int val) {
        Node<Integer> newNode = new Node<>(val);
        newNode.setNextNode(head);
        return newNode;
    }

    public static Node<Integer> insertAtEnd(Node<Integer> head, int key) {
        Node<Integer> curr = head;
        Node<Integer> newNode = new Node<>(key);
        if (curr == null) {
            head = newNode;
            return head;
        }
        while (curr.getNextNode()!=null) {
            curr = curr.getNextNode();
        }
        curr.setNextNode(newNode);
        return head;
    }

    //T: O(1)
    public static Node<Integer> deleteHead(Node<Integer> head) {
        if (head == null) return null;
        return head.getNextNode();
    }

    //T: O(N)
    public static Node<Integer> deleteLast(Node<Integer> head) {
        if (head==null || head.getNextNode()==null) return null;
        Node<Integer> curr = head;
        while (curr.getNextNode().getNextNode() != null) {
            curr = curr.getNextNode();
        }
        curr.setNextNode(null);
        return head;
    }

    static Node<Integer> insertAtIndex(Node<Integer> head, int idx, int data) {
        if (idx>size()) return head;
        if (idx == 0) return insertAtStart(head, data);
        Node<Integer> curr = head;
        int i =0;
        while (i<idx-1) {
            curr = curr.getNextNode();
            i++;
        }
        Node<Integer> newNode = new Node<>(data);
        newNode.setNextNode(curr.getNextNode());
        curr.setNextNode(newNode);
        return head;
    }

    static Node<Integer> sortedInsert(Node<Integer> head, int data) {
        if (head==null) return new Node<>(data);
        Node<Integer> curr = head;
        int i=0;
        while (data > curr.getData()) {
            curr = curr.getNextNode();
            i++;
            if (curr == null) return insertAtEnd(head, data);
        }
        return insertAtIndex(head, i, data);
    }

    public static int size() {
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

        System.out.println("````````````````````");
        head = insertAtEnd(head, 80);
        traverseLinkedList.traverse(head);

        System.out.println("````````````````````");
        head = deleteHead(head);
        traverseLinkedList.traverse(head);

        System.out.println("````````````````````");
        head = deleteLast(head);
        traverseLinkedList.traverse(head);
        System.out.println("````````````````````");
        head = deleteLast(head);
        traverseLinkedList.traverse(head);

        System.out.println("--------------------");
        head = insertAtIndex(head, 2, 58);
        traverseLinkedList.traverse(head);
        System.out.println("--------------------");
        head = insertAtIndex(head, 1, 67);
        traverseLinkedList.traverse(head);
        System.out.println("--------------------");
        head = insertAtIndex(head, 0, 123);
        traverseLinkedList.traverse(head);

        Node<Integer> head2 = new Node<>(10);
        Node<Integer> node22 = new Node<>(20);
        Node<Integer> node23 = new Node<>(30);
        head2.setNextNode(node22);
        node22.setNextNode(node23);
        node23.setNextNode(null);
        System.out.println("--------------------");
        head2 = sortedInsert(head2, 15);
        traverseLinkedList.traverse(head2);
        System.out.println("--------------------");
        head2 = sortedInsert(head2, 155);
        traverseLinkedList.traverse(head2);
    }
}
