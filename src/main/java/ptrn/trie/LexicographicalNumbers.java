package ptrn.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@link - https://leetcode.com/problems/lexicographical-numbers/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4909688291590144
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        // return revise(n);
        List<Integer> ans = new ArrayList<>();
        lo(n, 0, ans);
        return ans;
    }

    //1) my soln, simple DFS - This one is smart.
    private void lo(int n, int num, List<Integer> ans) {
        if (num > n) return;
        if (num != 0) ans.add(num);

        for (int i=0; i<=9; i++) {
            if (((10*num)+i) == 0) continue;
            lo(n, (10*num)+i, ans);
        }
    }

    private void revise(int n, int curr, List<Integer> ans) {

        for (int i=0; i<=9; i++) {
            int num = curr*10 + i;
            if (num == 0) continue;

            if (num > n) return;
            ans.add(num);
            revise(n, num, ans);
        }
    }

    //2) my soln using comparators.
    private List<Integer> revise(int n) {
        List<Integer> ans = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        Comparator<Integer> cmp = (i1, i2) -> {
            String s1 = String.valueOf(i1);
            String s2 = String.valueOf(i2);

            return compare(s1, s2);
        };

        Collections.sort(ans, cmp);
        return ans;
    }

    private int compare(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (Character.getNumericValue(s1.charAt(i)) > Character.getNumericValue(s2.charAt(j))) {
                return 1;
            } else if (Character.getNumericValue(s1.charAt(i)) < Character.getNumericValue(s2.charAt(j))) {
                return -1;
            }
            i += 1;
            j += 1;
        }

        if (i < s1.length()) return 1;
        return -1;
    }


    //3) Edctv soln using Tries. Check if it is the most optimal soln.
}
