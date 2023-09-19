package strvr.stacksandqueues;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/lru-cache/description/
//@strvr - https://takeuforward.org/data-structure/implement-lru-cache/
public class LRUCache {
    int capacity = 0;
    int size = 0;

    //keep track of all available (seen) nodes
    //in the linked list.
    Map<Integer, DLL> memo = new HashMap<>();

    DLL head = new DLL(-1, -1);
    DLL tail = new DLL(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        //Using adoubly linked list to implement LRU cache.
        //Having 2 dummy nodes. Everything between
        //head and tail will be our actual list.
        //The more a node is towards head, the less
        //recently used it is. The more the node is towards
        //the tail, the more recently used it is.
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //if trying to read an already
        //existing key, move it from its
        //current position to front of the
        //list (i.e. most recently used)
        if (memo.containsKey(key)) {
            DLL node = memo.get(key);
            deleteNode(node);
            addNodeAtTail(node);
            return node.val;
        }

        //-1 if trying to get a not existant
        //key.
        return -1;
    }

    public void put(int key, int value) {
        //new node (or update the value if key already existant)
        DLL newNode = new DLL(key, value);

        //if not seen before, add it to seen map.
        //and try adding it to list. If cache is full,
        //we need to evict least recently used node (i.e. node after head)
        //and then add this new node to front.
        if (!memo.containsKey(key)) {
            if (isFull()) {
                deleteFromFront();
            }
            addNodeAtTail(newNode);
        } else {
            //otherwise, if seen, delete it from
            //curr position and add a node with updated value
            //at the front.
            deleteNode(memo.get(key));
            addNodeAtTail(newNode);
        }
    }

    //checks if cache is full.
    //cache is full when curr size is equal
    //to cache capacity.
    private boolean isFull() {
        return this.size == this.capacity;
    }

    //Deletes a node from front (head side)
    //of the DLL. When delete from list, also
    //remember to delete from map.
    private void deleteFromFront() {
        if (this.size > 0) {
            DLL node = head.next;
            memo.remove(node.key);

            head.next = head.next.next;
            head.next.prev = head;

            //once deleted, decrement the size.
            this.size -= 1;
        }
    }

    //Deletes a given node from DLL.
    //When delete from list, also
    //remember to delete from map.
    private void deleteNode(DLL node) {
        if (memo.containsKey(node.key)) {
            memo.remove(node.key);
            node.prev.next = node.next;
            node.next.prev = node.prev;

            //once deleted, decrement the size.
            this.size -= 1;
        }
    }

    //Adds a new node to end of the list.
    //When adding to the list, also remember to
    //add to the map.
    private void addNodeAtTail(DLL newNode) {
        /*
            Here, tail is a dummy node.
            So, tail.prev is our actual last
            element of the Circular doubly linked list.
        */
        newNode.next = tail;
        newNode.prev = tail.prev;
        newNode.prev.next = newNode;
        tail.prev = newNode;

        memo.put(newNode.key, newNode);
        this.size += 1;
    }

    private void printList() {
        System.out.println("--------------------");
        System.out.println("Size: " + this.size);
        DLL curr = head;
        while (curr.next != head) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println(curr.val);

        DLL curr2 = tail;
        while (curr2.prev != tail) {
            System.out.print(curr2.val + "->");
            curr2 = curr2.prev;
        }
        System.out.println(curr2.val);
        System.out.println("--------------------");
    }
}

class DLL {
    int key;
    int val;
    DLL next;
    DLL prev;

    public DLL(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */