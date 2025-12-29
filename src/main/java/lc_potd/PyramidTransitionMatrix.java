package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/pyramid-transition-matrix/?
//@check - https://www.youtube.com/watch?v=dWlpBUZDQc0
public class PyramidTransitionMatrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return mikssol(bottom, allowed);
    }

    /**
     I knew how to solve this but the recursion was a
     bit tricky. So took hint from mik to write the recursion.
     Coded completely by me based on mik's explanation.

     The recursion is slightly tricky and I couldn't have come
     up with this myself.
     */
    private boolean mikssol(String bottom, List<String> allowed) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String a: allowed) {
            String key = a.substring(0, 2);
            String val = a.substring(2);
            if (!mp.containsKey(key)) mp.put(key, new ArrayList<>());
            mp.get(key).add(val);
        }

        return backtrack(0, bottom, "", mp, new HashMap<>());
    }

    private boolean backtrack(int i, String bottom, String nextRow, Map<String, List<String>> mp, Map<String, Boolean> memo) {
        if (bottom.length() == 1) return true;

        String mKey = i + "_" + bottom + "_" + nextRow;
        if (memo.containsKey(mKey)) return memo.get(mKey);

        if (i == bottom.length()-1) {
            //this was the tricky part.
            //keep collecting the nextrow
            return backtrack(0, nextRow, "", mp, memo); //start processing the next row.
        }

        String key = bottom.substring(i, i+2);
        if (!mp.containsKey(key)) {
            memo.put(mKey, false);
            return false;
        }

        for (String top: mp.get(key)) {
            //string concatenation like this `nextRow+top`
            //will automatically do the backtracking.
            //no need to do append and then pop
            if (backtrack(i+1, bottom, nextRow+top, mp, memo)) {
                memo.put(mKey, true);
                return true;
            }
        }

        memo.put(mKey, false);
        return false;
    }
}
