package ptrn.topkelements;

import java.util.*;

//@link - https://leetcode.com/problems/top-k-frequent-words/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6184234815062016
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Comparator<WordWrapper> cmp = (ww1, ww2) -> ww1.freq - ww2.freq;
        Comparator<WordWrapper> finalCmp = cmp.thenComparing((ww1, ww2) -> ww2.word.compareTo(ww1.word));
        PriorityQueue<WordWrapper> minHeap =
                new PriorityQueue<>(finalCmp);

        Map<String, Integer> mp = new HashMap<>();

        for (String word: words) {
            mp.put(word, mp.getOrDefault(word, 0)+1);
        }

        for (Map.Entry<String, Integer> entry: mp.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.add(new WordWrapper(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > minHeap.peek().freq ||
                    ((entry.getValue() == minHeap.peek().freq) && entry.getKey().compareTo(minHeap.peek().word) < 0)) {
                minHeap.remove();
                minHeap.add(new WordWrapper(entry.getKey(), entry.getValue()));
            }
        }

        List<String> ans = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ans.add(minHeap.remove().word);
        }

        Collections.reverse(ans);
        return ans;
    }
}

class WordWrapper {
    String word;
    int freq;
    public WordWrapper(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
}
