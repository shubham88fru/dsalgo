package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/maximize-active-section-with-trade-i/description/?
//@check - https://www.youtube.com/watch?v=Q7749lqBheA
public class MaximizeActiveSectionWithTradeI {
    public int maxActiveSectionsAfterTrade(String s) {
        return mikssol(s);
    }

    /*
        Coded by me completely based
        on mik's explanation.
    */
    private int mikssol(String s) {
        int n = s.length();
        int i=0;
        List<Integer> blocks = new ArrayList<>();
        int oc = 0;
        while (i < n) {
            int zc = 0;
            while (i < n && s.charAt(i) == '0') {
                zc += 1;
                i += 1;
            }

            if (zc > 0) blocks.add(zc);
            if (i < n) oc += 1;

            i += 1;
        }

        int maxPairSum = 0;
        for (int j=0; j<blocks.size()-1; j++) {
            maxPairSum = Math.max(maxPairSum, blocks.get(j) + blocks.get(j+1));
        }

        return oc + maxPairSum;
    }
}
