package ptrn.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/word-break-ii/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5878204501590016
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> all = new ArrayList<>();
        wb(s, new HashSet<>(wordDict), 0, all, new ArrayList<>());
        return all;
    }

    //1) My recursive backtracking solution.
    private void wb(String s, Set<String> dict, int curr, List<String> all, List<String> currList) {
        if ("".equals(s)) {
            all.add(String.join(" ", currList));
            return;
        }

        //standard backtracking.
        for (int i=0; i<s.length(); i++) {
            String sub = s.substring(0, i+1);
            if (dict.contains(sub)) {
                currList.add(sub);
                wb(s.substring(i+1), dict, i+1, all, currList);
                currList.remove(currList.size()-1);
            }
        }
    }

    //2) Check edctv for bottom-up dp approach.
}
