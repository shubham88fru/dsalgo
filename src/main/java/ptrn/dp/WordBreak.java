package ptrn.dp;

import java.util.*;

//@link - https://leetcode.com/problems/word-break/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6092586093379584
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wb(s, new HashSet<>(wordDict), new HashMap<>(), 0);
    }

    //1) My topdown dp solution.
    private boolean wb(String s, Set<String> dict, Map<Integer, Boolean> cache, int curr) {
        if ("".equals(s)) return true;

        if (cache.containsKey(curr)) return cache.get(curr);
        for (int i=0; i<s.length(); i++) {
            if (dict.contains(s.substring(0, i+1))) {
                String rest = s.substring(i+1);
                if (dict.contains(rest) || wb(rest, dict, cache, i+1)) {
                    cache.put(curr, true);
                    return true;
                }
                //WORKS_HERE
                else cache.put(curr, false);
            }
        }

        /**
         Note: putting false in cache here,
         fails some test cases. And if I move it
         to the else case above (marked `WORKS_HERE`),
         the solution works for all cases. Ponder why.
         */
        //cache.put(curr, false);
        return false;
    }

    //2) Check edctv for bottom up dp approach
}
