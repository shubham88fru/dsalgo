package misc;

import java.util.LinkedList;

//Design a DS that supports -
//1) add(int x)
//2) removeAndPrint(int x) --> Removes all occurrences of x and prints the remaining in order of insertion
public class DSForRemoveAndPrint {
    private final LinkedList<Integer> lst
            = new LinkedList<>();

    public void add(int x) {
        lst.add(x);
    }

    public void removeAndPrint(int x) {
        lst.removeIf(item -> x == item);
        System.out.println(lst);
    }

    public static void main(String[] args) {
        DSForRemoveAndPrint ds = new DSForRemoveAndPrint();
        ds.add(10);
        ds.add(20);
        ds.add(10);
        ds.add(30);
        ds.removeAndPrint(10);
        ds.add(30);
        ds.add(40);
        ds.add(60);
        ds.removeAndPrint(30);
    }
}
