package misc;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//Assume that prices are distinct
public class DSForItemPrices {

    private final TreeMap<Integer, String> itemsMap;

    public DSForItemPrices() {
        itemsMap = new TreeMap<>();
    }

    public void add(int price, String name) {
        itemsMap.put(price, name);
    }

    String find(int price) {
        String res = itemsMap.get(price);
        if (res==null) return "";
        return res;
    }

    void printSorted() {
        for (Map.Entry<Integer, String> e: itemsMap.entrySet()) {
            System.out.println(e.getValue()+" "+e.getKey());
        }
    }

    void printGreaterSorted(int price) {
        SortedMap<Integer, String> g //greater
                        = itemsMap.tailMap(price);
        for (Map.Entry<Integer, String> e: g.entrySet()) {
            System.out.println(e.getValue()+ " "+ e.getKey());
        }
    }

    void printSmallerSorted(int price) {
        SortedMap<Integer, String> s //smaller
                        = itemsMap.headMap(price);
        for (Map.Entry<Integer, String> e: s.entrySet()) {
            System.out.println(e.getValue()+" "+e.getKey());
        }
    }
}
