package basics.hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChainingImplBetter {
    private int mod;
    private ArrayList<LinkedList<Integer>> hashTable;

    ChainingImplBetter(int modSize) {
        mod = modSize;
        hashTable = new ArrayList<LinkedList<Integer>>();
        for (int i=0; i<mod; i++) {
            hashTable.add(new LinkedList<>());
        }
    }

    void insert(int key) {
        int idx = getIdx(key);
        hashTable.get(idx).add(key);
    }

    boolean search(int key) {
        int idx = getIdx(key);
        return hashTable.get(idx).contains(key);
    }

    int delete(int key) {
        int idx = getIdx(key);
        if (hashTable.get(idx)!=null) {
            if (hashTable.get(idx).contains(key))
                hashTable.get(idx).remove(Integer.valueOf(key));
            return idx;
        }
        return -1;
    }

    private int getIdx(int key) {
        return key%mod;
    }

    public static void main(String[] args) {
        ChainingImplBetter chaining = new ChainingImplBetter(7);
        chaining.insert(70);
        chaining.insert(71);
        chaining.insert(9);
        chaining.insert(56);
        chaining.insert(72);
        chaining.delete(56);

        System.out.println(chaining.search(56));
        System.out.println(chaining.search(51));
    }
}
