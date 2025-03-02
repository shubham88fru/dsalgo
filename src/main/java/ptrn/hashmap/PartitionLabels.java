package ptrn.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/partition-labels/
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        return pass1(s);
    }

    /*
    * My soln :)
    * Nc soln was exact same.
    * */
    private List<Integer> pass1(String s) {
        int n = s.length();
        Map<Character, Integer> mp = new HashMap<>();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            mp.put(ch, i);
        }

        int si = 0; //start of current partition.
        int ei = 0; //end of current partition.
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            ei = Math.max(ei, mp.get(ch)); //keep track of the farthest occurence.
            if (i == ei) {
                ans.add(ei-si+1);
                si = i + 1;
                ei = 0;
            }
        }

        return ans;
    }
}
