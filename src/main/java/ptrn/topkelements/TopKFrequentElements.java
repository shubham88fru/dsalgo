package ptrn.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/top-k-frequent-elements/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5418214499811328
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        /**
         Standard top k solution using heaps.
         */
        //Min heaps based on frequency.
        PriorityQueue<ElementWrapper> minHeap =
                new PriorityQueue<>((ew1, ew2) -> ew1.freq - ew2.freq);
        Map<Integer, Integer> mp = new HashMap<>();

        //record the frequencies.
        for (int num: nums) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            //keep adding to heap untill
            //we have atleast k elements.
            if (minHeap.size() < k) {
                minHeap.add(new ElementWrapper(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > minHeap.peek().freq) {
                //if freq of root of heap is less than
                //the current element, remove the root and
                //add current element.
                minHeap.remove();
                minHeap.add(new ElementWrapper(entry.getKey(), entry.getValue()));
            }
        }

        int i = 0;
        int[] ans = new int[k];
        while (!minHeap.isEmpty()) {
            ans[i] = minHeap.remove().element;
            i += 1;
        }

        return ans;
    }
}

class ElementWrapper {
    int element;
    int freq;
    public ElementWrapper(int element, int freq) {
        this.element = element;
        this.freq = freq;
    }
}
