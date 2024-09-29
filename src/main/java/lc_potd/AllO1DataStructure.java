package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/all-oone-data-structure/

/*
    Following is my soln. Mik used a slightly different approach that might
    be better. Watch on next encounter.
 */
public class AllO1DataStructure {
    Map<String, DLL> mp = new HashMap<>();
    DLL head = new DLL(-1, ""); //min
    DLL tail = new DLL(-1, ""); //max

    public AllO1DataStructure() {
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {

        if (!mp.containsKey(key)) {
            DLL node = new DLL(1, key);
            mp.put(key, node);

            DLL currHead = head.next;
            head.next = node;
            node.prev = head;
            node.next = currHead;
            currHead.prev = node;
        } else {
            DLL node = mp.get(key);
            node.val += 1;

            DLL curr = node.next;
            while (curr.val != -1) { //untill we reach the tail.
                if (curr.val < node.val) {
                    curr = curr.next;
                } else break;
            }
            if (curr == node.next) return;
            DLL nodePrev = node.prev;
            DLL nodeNext = node.next;
            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;

            if (curr.val == -1) {
                DLL prev = tail.prev;
                prev.next = node;
                node.prev = prev;
                node.next = tail;
                tail.prev = node;
            } else {
                DLL prev = curr.prev;
                DLL next = curr;

                prev.next = node;
                node.prev = prev;
                node.next = next;
                next.prev = node;
            }
        }

    }

    public void dec(String key) {
        DLL node = mp.get(key);
        node.val -= 1;

        if (node.val == 0) {
            mp.remove(key);
            DLL prev= node.prev;
            DLL next = node.next;
            prev.next = next;
            next.prev = prev;

            return;
        }

        DLL curr = node.prev;
        while (curr.val != -1) { //unitll we reach the head.
            if (curr.val > node.val) {
                curr = curr.prev;
            } else break;
        }
        if (curr == node.prev) return;

        DLL nodePrev = node.prev;
        DLL nodeNext = node.next;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;

        DLL currPrev = curr.prev;
        currPrev.next = node;
        node.prev = currPrev;
        node.next = curr;
        curr.prev = node;


    }

    public String getMaxKey() {
        DLL node = tail.prev;
        return node.key;
    }

    public String getMinKey() {
        DLL node = head.next;
        return node.key;
    }
}

class DLL {
    DLL next;
    DLL prev;
    int val;
    String key;

    public DLL(int val, String key) {
        this.val = val;
        this.key = key;
    }
}