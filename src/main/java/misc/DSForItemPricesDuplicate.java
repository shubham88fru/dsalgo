package misc;

import java.util.*;

//prices can be duplicate.
public class DSForItemPricesDuplicate {

    private final TreeMap<Integer, List<String>> m;

    public DSForItemPricesDuplicate() {
        m = new TreeMap<>();
    }

    void add(int price, String item) {
        m.computeIfAbsent(price,
                k -> new ArrayList<>());
        m.get(price).add(item);
    }

    List<String> find(int price) {
        return m.get(price);
    }

    void printSorted() {
        for (Map.Entry<Integer, List<String>> e:
                m.entrySet()) {
            int p = e.getKey();
            for (String s: e.getValue())
                System.out.println(s+" "+p);
        }
    }

    void printGreaterSorted(int price) {
        SortedMap<Integer, List<String>> g
                = m.tailMap(price);
        for (Map.Entry<Integer, List<String>>e: g.entrySet()) {
            int p = e.getKey();
            for (String s: e.getValue())
                System.out.println(s+" "+p);
        }
    }

    void printSmallerSorted(int price) {
        SortedMap<Integer, List<String>> s
                = m.headMap(price);
        for (Map.Entry<Integer, List<String>>e: s.entrySet()) {
            int p = e.getKey();
            for (String st: e.getValue())
                System.out.println(st+" "+p);
        }
    }


}
