package ptrn.knowingwhattotrack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-frequency-stack/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5490411893424128

/**
 * Soln heavily borrowed from edctv.
 * Do check their illustration for better understanding.
 */
public class MaximumFrequencyStack {
    //Will store frequency as a key and a stack
    //containing all values with that frequency (in order of insertion).
    Map<Integer, Deque<Integer>> group;

    //Will store element as a key and its frequency as value.
    Map<Integer, Integer> frequency;

    //At a point in time, what's the maximum frequency.
    int maxFrequency;

    public MaximumFrequencyStack() {
        group = new HashMap<>();
        frequency = new HashMap<>();
        maxFrequency = 0;
    }

    public void push(int val) {
        frequency.put(val, frequency.getOrDefault(val, 0)+1);
        int currElsFreq = frequency.get(val);

        maxFrequency = Math.max(maxFrequency, currElsFreq);

        if (!group.containsKey(currElsFreq)) {
            group.put(currElsFreq, new ArrayDeque<>());
        }

        group.get(currElsFreq).addFirst(val);
    }

    public int pop() {
        int maxFreqAndMostRecentEl = group.get(maxFrequency).removeFirst();
        //decrease the frequency of the element popped.
        frequency.put(maxFreqAndMostRecentEl, frequency.get(maxFreqAndMostRecentEl)-1);

        if (group.get(maxFrequency).isEmpty()) {
            group.remove(maxFrequency);
            //maxFrequency - 1 will certainly exist. Coz we create a new entry, for every higher occurence.
            //Basically our group map will have keys from 1 to maxFrequency.
            //Refer the edctv soln illustration to refresh and see it visually.
            maxFrequency -= 1;
        }
        return maxFreqAndMostRecentEl;
    }
}
