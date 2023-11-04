package strvr.bitmanip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://practice.geeksforgeeks.org/problems/power-set4302/1#
//@strvr - https://takeuforward.org/data-structure/power-set-print-all-the-possible-subsequences-of-the-string/
public class AllSubsequencesUsingBitManip {
    public List<String> AllPossibleStrings(String s)
    {
        // Code here
        return allSubSeqUsingBitManip(s);
    }

    private List<String> allSubSeqUsingBitManip(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i=0; i < Math.pow(2, n); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<n; j++) {
                if ((i & (1<<j)) != 0) sb.append(s.charAt(j));
            }
            ans.add(sb.toString());
        }

        /* ******ONLY B/C ATQ.
        Collections.sort(ans);
        ans.remove(0);
         * ******************/
        return ans;
    }
}
