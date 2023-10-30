package ptrn.slidingwindow;

import java.util.*;

/**
 * The optimal solution for this problem uses `Rabin-Karp` algorithm.
 * @check https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6411992975015936
 */
//@link - https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        //can't find any sequence of length 10
        if (s.length() < 10) return new ArrayList<>();

        //store the pattern v/s count of occurence
        Map<String, Integer> freq = new HashMap<>();

        //length 10 window.
        int i = 0;
        int j = 9;

        //final ans.
        Set<String> ans = new HashSet<>();

        //till end of window
        while (j < s.length()) {
            //take substring of len 10.
            String sequence = s.substring(i, j+1);
            //put in freq map and increase count.
            freq.put(sequence, freq.getOrDefault(sequence, 0)+1);

            //if frequence more than 1, add to set (set - so we don't store duplicates)
            if (freq.get(sequence) > 1) ans.add(sequence);

            //slide the window.
            i += 1;
            j += 1;
        }

        return new ArrayList<>(ans);
    }
}
