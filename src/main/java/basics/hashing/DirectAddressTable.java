package basics.hashing;

import java.util.Arrays;

//Imagine a situation where you have a 1000 keys
//with values from 0 to 999, implement the following
//in O(1)
//1) Search
//2) Insert
//3) Delete
public class DirectAddressTable {
    private static int[] table = new int[1000];

    // 0 - not present, 1 - present
    DirectAddressTable() {
        Arrays.fill(table, 0);
    }

    static void delete(int i) {
        table[i] = 0;
    }

    static int search(int i) {
        return table[i];
    }

    static void insert(int i) {
        table[i] = 1;
    }

    public static void main(String[] args) {
        DirectAddressTable d = new DirectAddressTable();
        d.insert(1);
        d.search(1);
    }
}
