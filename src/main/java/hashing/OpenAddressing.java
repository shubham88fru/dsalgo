package hashing;
import java.util.Arrays;


//Implementation of open addressing
//using `linear probing`.
//Assumption: -1 means empty, -2 means deleted
//i.e. they are not part of the array.
public class OpenAddressing {
    private final int mod; //capacity of table.
    private int size; //size of hashtable.
    private final int[] hashtable;

    OpenAddressing(int cap) {
        this.mod = cap;
        size = 0;
        hashtable = new int[7];
        Arrays.fill(hashtable, -1);
    }

    boolean insert(int key) {
        if (size==mod) return false;
        int i = hash(key);

        //next pos only if
        while (hashtable[i] != -1 && hashtable[i] != -2
                && hashtable[i] != key) {
            i = (i+1)%mod;
        }
        if (hashtable[i]==key) return false;
        else {
            hashtable[i] = key;
            size++;
            return true;
        }
    }

    boolean search(int key) {
        int idx = hash(key);
        int i = idx;
        while (hashtable[i] != -1) {
            if (hashtable[i] == key) return true;
            i = (i+1)%mod;
            if (i==idx) return false;
        }
        return false;
    }

    boolean delete(int key) {
        int idx = hash(key);
        int i = idx;
        while (hashtable[i] != -1) {
            if (hashtable[i] == key) {
                hashtable[i] = -2;
                size--;
                return true;
            }
            i = (i+1)%mod;
            if (i==idx) return false;
        }
        return false;
    }

    int hash(int key) {
        return key%mod;
    }

    public static void main(String[] args) {
        OpenAddressing hash = new OpenAddressing(7);
        System.out.println(hash.insert(49));
        System.out.println(hash.insert(56));
        System.out.println(hash.insert(72));

        System.out.println(hash.search(56));
        System.out.println(hash.delete(56));
        System.out.println(hash.search(56));
    }
}
