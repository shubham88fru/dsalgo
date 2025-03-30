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
        //optimization - use array instead of map.
        int[] li = new int[26];
        for (int i=0; i<s.length(); i++) {
            li[s.charAt(i)-'a'] = i;
        }

        int j = 0;
        int max = -1;
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            max = Math.max(max, li[ch-'a']);
            if (max == i) {
                ans.add(i-j+1);
                max = -1;
                j = i + 1;
            }
        }

        return ans;
    }
}
