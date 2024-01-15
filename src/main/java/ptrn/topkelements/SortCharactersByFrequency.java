package ptrn.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-characters-by-frequency/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4813582962524160
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> freqs = new HashMap<>();

        //frequency of all characters.
        for (char ch: s.toCharArray()) {
            freqs.put(ch, freqs.getOrDefault(ch, 0)+1);
        }

        //max heap to keep track of char with max frequency.
        PriorityQueue<Wrapper> maxHeap =
                new PriorityQueue<>((w1, w2) -> w2.freq - w1.freq);

        //push the chars and their freqs (as wrapper object) into maxHeap.
        for (Map.Entry<Character, Integer> entry: freqs.entrySet()) {
            maxHeap.add(new Wrapper(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        //Till we've exhausted the heap,
        //repeatedly keep pulling the chars
        //with max frequency and appending them
        //to a string the num of times of their
        //frequencies.
        while (!maxHeap.isEmpty()) {
            Wrapper item = maxHeap.remove();
            int times = item.freq;
            while (times > 0) {
                sb.append(item.ch);
                times -= 1;
            }
        }

        return sb.toString();
    }
}

//Wraps a char and its frequency.
class Wrapper {
    char ch;
    int freq;
    public Wrapper(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
