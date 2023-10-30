package ptrn.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //till end of window
        while (j < s.length()) {
            //take substring of len 10.
            String sequence = s.substring(i, j+1);
            //put in freq map and increase count.
            freq.put(sequence, freq.getOrDefault(sequence, 0)+1);
            //slide the window.
            i += 1;
            j += 1;
        }

        //loop through map and collate
        //all sequences that which occured more than once.
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: freq.entrySet()) {
            if (entry.getValue() > 1) ans.add(entry.getKey());
        }
        return ans;
    }
}
