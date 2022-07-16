package basics.hashing;

import java.util.Arrays;
import java.util.LinkedList;

public class ChainingImplNaive {
    private static LinkedList<Integer>[] arr;
    private static int mod;

    public ChainingImplNaive(int size) {
        mod = size;
        // This is bad, java doesn't
        // allow basics.arrays of  generics.
        arr = new LinkedList[size];
        Arrays.fill(arr, null);
    }

    static void insert(int key) {
        int idx = getIdx(key);
        if (arr[idx]==null) {
            arr[idx] = new LinkedList<>();
        }
        arr[idx].add(key);
    }

    static boolean search(int key) {
        int idx = getIdx(key);
        return arr[idx].contains(key);
    }

    static int delete(int key) {
        int idx = getIdx(key);
        if (arr[idx] != null) {
            if (arr[idx].contains(key))
                arr[idx].remove(Integer.valueOf(key));
            return idx;
        }
        return -1;
    }

    static int getIdx(int key) {
        return key%mod;
    }

    public static void main(String[] args) {
        ChainingImplNaive chaining = new ChainingImplNaive(7);
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
