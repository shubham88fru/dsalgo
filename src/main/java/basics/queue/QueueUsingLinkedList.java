package basics.queue;

//Implement queue using a singly linked list.
//head of ll -> front of queue.
//tail of ll -> rear of queue.
//In a queue(not dequeue), items are always added to rear
//and removed from front.
public class QueueUsingLinkedList {
    Node front, rear;
    int size;
    QueueUsingLinkedList() {
        front = rear = null;
    }

    void enqueue(int x) {
        Node newNode = new Node(x);
        //if ll is empty,
        //this particular enqueue
        //we need to change both front
        //and rear. otherwise enqueue needs
        //change only to rear.
        if (front == null) {
            front = rear = newNode;
            return;
        }
        rear.nextNode = newNode; //curr rear's next is newNode
        rear = newNode; //rear points to newNode.
        size++;
    }

    void dequeue() {
        if (front==null) return;
        front = front.nextNode;
        if (front==null) rear = null;
        size--;
    }

    int getSize() {
        return size;
    }

    public void print() {
        Node curr = front;
        while (curr!=null) {
            System.out.print(curr.data+" ");
            curr = curr.nextNode;
        }
    }
}

//Singly linked node
class Node {
    int data;
    Node nextNode;

    public Node(int data) {
        this.data = data;
    }
}

class Test3 {
    public static void main(String[] args) {
        QueueUsingLinkedList queue
                = new QueueUsingLinkedList();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.print();
        System.out.println("--------");
        queue.enqueue(30);
        queue.dequeue();
        queue.print();
        System.out.println("-------------");
        queue.dequeue();
        queue.print();
    }
}