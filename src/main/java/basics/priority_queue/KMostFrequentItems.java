package basics.priority_queue;

import java.util.*;

//Given an array and k, need to
//find top k frequent items. If frequency
//is same then give priority to smaller items.
public class KMostFrequentItems {

    //using hash map and array list.
    //O(nlogn)
    void printFrequentItems1(int[] arr, int k) {
        Map<Integer, Integer> map
                = new HashMap<>();

        //Theta(N)
        for (int j : arr) {
            map.put(j, map.getOrDefault(j,
                    0) + 1);
        }

        //Theta(N)
        List<Map.Entry<Integer, Integer>> list
                = new ArrayList<>(map.entrySet());

        //O(nlogn)
        list.sort(new CustomComparator());

        //O(K)
        for (int i=0; i<k;i++) {
            System.out.print(list.get(i).getKey()+" ");
        }
    }

    //using priority queue and hashmap
    public void printFrequentElements2(int[] arr,
                                       int k) {
        Map<Integer, Integer> map
                = new HashMap<>();
        for (int j : arr)
            map.put(j, map.
                    getOrDefault(j,
                            0) + 1);

        //because of comparator we have given,
        //this creates a max heap below. Instead of
        //a default min heap.
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue
                = new PriorityQueue<>(new CustomComparator());

        priorityQueue.addAll(map.entrySet());

        for(int i=0; i<k;i++)
            System.out.print(priorityQueue
                    .poll().getKey() + " ");
    }

    public static void main(String[] args) {
        KMostFrequentItems kMostFrequentItems
                = new KMostFrequentItems();
        int[] arr = {10, 20, 10, 30, 10, 20, 30};
        kMostFrequentItems.
                printFrequentItems1(arr, 2);

        System.out.println();
        kMostFrequentItems
                .printFrequentElements2(arr, 2);
    }
}

class CustomComparator implements
        Comparator<Map.Entry<Integer, Integer>> {

    @Override
    public int compare(Map.Entry<Integer,
            Integer> o1, Map.Entry<Integer, Integer> o2) {
        if (Objects.equals(o1.getValue(), o2.getValue()))
            return o1.getKey() - o2.getKey();
        else
            return o2.getValue() - o1.getValue();
    }
}
