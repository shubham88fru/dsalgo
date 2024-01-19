package ptrn.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/reorganize-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4806778308263936
public class ReorganizeString {
    public String reorganizeString(String s) {
        //return sol2(s);
        return sol1(s);
    }

    //edctv soln.
    private String sol1(String s) {
        //freq of each char.
        Map<Character, Integer> mp = new HashMap<>();
        for (char ch: s.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0)+1);
        }

        //put each char and its freq in a max heap.
        //idea is that we'll keep picking the char with
        //max freq from the heap in each iteration, and appending to ans.
        PriorityQueue<FreqWrapper> maxHeap =
                new PriorityQueue<>((fw1, fw2) -> fw2.freq - fw1.freq);
        for (Map.Entry<Character, Integer> entry: mp.entrySet()) {
            maxHeap.add(new FreqWrapper(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        FreqWrapper prev = null;
        while (!maxHeap.isEmpty() || prev != null) {
            if (prev != null && maxHeap.isEmpty()) return "";

            //Take the char with max freq.
            FreqWrapper fw = maxHeap.remove();
            char ch = fw.ch;
            int freq = fw.freq;

            //Ensure that its the same char which was appended in last iteration.
            //This is possible when a char has a large frequency that others (even after decrementing)
            //In that case even if decrement the frequency before pushing to maxHeap, that char will
            //still be the char with highest frequency.
            if (prev != null) {
                maxHeap.add(prev);
                prev = null;
            }

            //append the char.
            sb.append(ch);
            //decreemnt the frequcency of whichever char
            //that was actually consumed.
            freq -= 1;
            //and if still has a frequency left,
            //push is back to heap.
            if (freq != 0) prev = new FreqWrapper(ch, freq);
        }

        return sb.toString();
    }

    //My soln. Differs from sol1 in how do we handle
    //the case when same chars are pulled from heap and ensure
    //we don't append repeating chars to the ans.
    private String sol2(String s) {
        //freq of each char.
        Map<Character, Integer> mp = new HashMap<>();
        for (char ch: s.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0)+1);
        }

        //put each char and its freq in a max heap.
        //idea is that we'll keep picking the char with
        //max freq from the heap in each iteration, and appending to ans.
        PriorityQueue<FreqWrapper> maxHeap =
                new PriorityQueue<>((fw1, fw2) -> fw2.freq - fw1.freq);
        for (Map.Entry<Character, Integer> entry: mp.entrySet()) {
            maxHeap.add(new FreqWrapper(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            //Take the char with max freq.
            FreqWrapper fw = maxHeap.remove();
            char ch = fw.ch;
            int freq = fw.freq;

            //Ensure that its the same char which was appended in last iteration.
            //This is possible when a char has a large frequency that others (even after decrementing)
            //In that case even if decrement the frequency before pushing to maxHeap, that char will
            //still be the char with highest frequency.
            if ((sb.length() != 0) && (ch == sb.charAt(sb.length()-1))) {
                //try to append another char with just lower frequency.
                if (!maxHeap.isEmpty()) {
                    FreqWrapper temp = fw;
                    fw = maxHeap.remove();
                    ch = fw.ch;
                    freq = fw.freq;

                    //and push back the original (repeating)
                    //char that was pulled as is, since we haven't
                    //used it, we won't decrement its frequency.
                    maxHeap.add(temp);
                } else return ""; //if no otehr different char is left, means arrangement is not possible.
            }

            //append the char.
            sb.append(ch);
            //decreemnt the frequcency of whichever char
            //that was actually consumed.
            freq -= 1;
            //and if still has a frequency left,
            //push is back to heap.
            if (freq != 0) maxHeap.add(new FreqWrapper(ch, freq));
        }

        return sb.toString();
    }
}

class FreqWrapper {
    char ch;
    int freq;
    public FreqWrapper(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
