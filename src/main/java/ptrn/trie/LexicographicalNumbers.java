package ptrn.trie;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/lexicographical-numbers/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4909688291590144
public class LexicographicalNumbers {

    //1) My Soln - simple DFS.
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        lo(n, 0, ans);
        return ans;
    }

    private void lo(int n, int num, List<Integer> ans) {
        if (num > n) return;
        if (num != 0) ans.add(num);

        for (int i=0; i<=9; i++) {
            int curr = (10*num)+i;
            if (curr == 0) continue;
            lo(n, curr, ans);
        }
    }

    //2) Edctv soln using Tries.
}
