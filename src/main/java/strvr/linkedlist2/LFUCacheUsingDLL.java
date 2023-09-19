package strvr.linkedlist2;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/lfu-cache/
//@strvr - https://www.youtube.com/watch?v=0PSB9y8ehbk&t=1s&ab_channel=takeUforward
public class LFUCacheUsingDLL {
    /**
     The major change in LFU cache (when compared to LRU cache) is
     that, in LFU basically we have multiple LRU in a frequency map.
     where the frequency represent the frequency of access.
     */
    final int capacity;
    int currSize;
    int minFreq;

    Map<Integer, DLLNode> _cache; //the LFU cache.

    //frequency v/s a wrapper that mimicks LRU cache.
    //keeps track of how frequently nodes
    //are touched/accessed.
    Map<Integer, DoublyLinkedList> _frequencyMap;

    public LFUCacheUsingDLL(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
        this.minFreq = 0;

        this._cache = new HashMap<>();
        this._frequencyMap = new HashMap<>();

    }

    public int get(int key) {
        if (!_cache.containsKey(key)) return -1;
        DLLNode currNode = _cache.get(key);

        //This node is now recently touched,
        //So, increase its frequency and put
        //the node in the right bucket before returning.
        updateNodeAndFreq(currNode);

        return currNode.val;
    }

    /**
     Add a new node into the LFU cache, as well as the doubly linked list.
     condition 1: if LFU cache has input key, update the node value and node position in the list.
     condition 1: if LFU cache doesn't have input key -
     --> if LFU cache does not have enough space, remove LRU
     node  from the min frequency and add the new node.
     --> if LFU has enough space, add the new node.
     */
    public void put(int key, int value) {
        if (capacity == 0) return;

        if (_cache.containsKey(key)) {
            DLLNode currNode = _cache.get(key);
            currNode.val = value;
            updateNodeAndFreq(currNode);
        } else {
            currSize += 1;
            if (currSize > capacity) {
                //get min frequency list
                DoublyLinkedList minFreqList = _frequencyMap.get(minFreq);
                DLLNode lruNode = minFreqList.tail.prev;
                int lruKey = lruNode.key;
                _cache.remove(lruKey); //LRU node will the one just before the tail.
                minFreqList.removeNode(lruNode);
                currSize -= 1;
            }

            //reset min freq to 1 because of adding a new node.
            minFreq = 1;
            DLLNode newNode = new DLLNode(key, value);

            //get the list with frequency 1, and then add new node into the list, as well as LFU cache.
            DoublyLinkedList currList = _frequencyMap.getOrDefault(1, new DoublyLinkedList());
            currList.addNode(newNode);
            _frequencyMap.put(1, currList);
            _cache.put(key, newNode);
        }

    }

    public void updateNodeAndFreq(DLLNode currNode) {
        int currFreq = currNode.freq;
        DoublyLinkedList currList = _frequencyMap.get(currFreq);
        currList.removeNode(currNode); //remove currNode from current frequency, since its freq has increased.

        //if current list is the last list which has lowest freq and curr node
        //is the only node in that list, we need to remove the entire list and then
        //increase min freq value by 1.
        if (currFreq == minFreq && currList.listSize == 0) minFreq += 1;

        currNode.freq += 1;

        //add current node to another list that has one more frequency that curr nodes prev freq.
        //if we do not have the list with this frequency, initialize it
        DoublyLinkedList newList = _frequencyMap.getOrDefault(currNode.freq, new DoublyLinkedList());
        newList.addNode(currNode);
        _frequencyMap.put(currNode.freq, newList);
    }
}

class DLLNode {
    int key;
    int val;
    DLLNode next;
    DLLNode prev;
    int freq;


    public DLLNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1; //when a node is created, its freq is one.
    }
}

//Mimicks the LRU cache behavior.
class DoublyLinkedList {
    int listSize;
    DLLNode head;
    DLLNode tail;

    public DoublyLinkedList() {
        this.listSize = 0;
        this.head = new DLLNode(0, 0);
        this.tail = new DLLNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    //add new node into head of list and increase list size by 1;
    public void addNode(DLLNode currNode) {
        DLLNode nextNode = head.next;
        currNode.next = nextNode;
        currNode.prev = head;
        head.next = currNode;
        nextNode.prev = currNode;
        listSize += 1;
    }

    //remove input node and decrease list size by 1;
    public void removeNode(DLLNode currNode) {
        DLLNode prevNode = currNode.prev;
        DLLNode nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        listSize -= 1;
    }
}