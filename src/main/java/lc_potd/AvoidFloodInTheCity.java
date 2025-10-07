package lc_potd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//@link - https://leetcode.com/problems/avoid-flood-in-the-city/
//@check - https://leetcode.com/problems/avoid-flood-in-the-city/editorial/
public class AvoidFloodInTheCity {
    public int[] avoidFlood(int[] rains) {
        return hintedSol(rains);
    }

    /**
     The problem description is a bit confusing.
     The idea is that at every 0 we need to check
     if a lake had already rained, if so, clear it.
     Because if the same lake rains again, it will flood.

     I had a good enough understanding of the problem and
     a potential solution, but couldn't conceive a concrete soln.
     Following soln is coded by me based on LC's official soln.
     There is aparently a priority queue soln as well.
     */
    private int[] hintedSol(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];

        /**
         This is BS.
         Even if there wasn't any lake really that needed to
         be cleared on a 0 day, the problem needs to return some
         lake value there, can't return 0.

         For e.g. for input [69,0,0,0,69]
         Answer should be [-1,69,1,1,-1]
         and not [-1,69,0,0,-1]

         For input [0,0,0,0]
         Answer should be [1, 1, 1, 1] 🤯
         not [0, 0, 0, 0]
         */
        Arrays.fill(ans, 1);

        TreeSet<Integer> zeros = new TreeSet<>();
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i=0; i<n; i++) {
            if (rains[i] == 0) {
                zeros.add(i);
            } else {
                ans[i] = -1;
                if (mp.containsKey(rains[i])) {
                    /**
                     * Handy treeset method.
                     * Ceiling returns the smallest num
                     * larger than the input.
                     * Actually does' binary search.
                     */
                    Integer idx = zeros.ceiling(mp.get(rains[i]));
                    if (idx == null) return new int[0]; //zero not available to clean before flooding.
                    ans[idx] = rains[i];
                    zeros.remove(idx);
                }
                mp.put(rains[i], i);
            }
        }
        return ans;
    }
}
