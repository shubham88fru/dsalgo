package lc_potd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
//@check - https://www.youtube.com/watch?v=pQ_BQ9J9p-c&ab_channel=codestorywithMIK
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        // return usingHeap(n, k);

        /*
        int[] ans = {Integer.MIN_VALUE};
        int[] kay = {k};
        dfs(n, kay, 0, ans);
        return ans[0];
        */

        return optimal(n, k);
    }

    //0) Mik's soln.
    /**
     The only difficult thing about this question are the
     constraints. Constraints are so large that even the
     O(N) soln won't work. And so, the large constraints
     slightly hinted to a O(logN) type of solution, meaning
     a solution involving some sort of skipping etc.
     */
    private int optimal(int n, int k) {
        int curr  = 1;
        k -= 1; //since we start from the first number (1), we need k-1 more nums.

        while (k > 0) {
            int cnt = count(curr, curr+1, n);
            if (cnt <= k) {
                curr += 1;
                k -= cnt; //skipping the elements under current prefix tree.
            } else {
                curr *= 10;
                k -= 1; //go just one level deep in the same prefix tree.
            }
        }

        return curr;
    }

    private int count (long curr, long next, long n) {
        int countNum = 0;
        while (curr <= n) {
            countNum += (next-curr);
            curr *= 10; //try a deeper prefix subtree of curr prefix.
            next = Math.min(next*10, n+1); //try a deeper prefix subtree of next prefix. Ensure, next is in bounds.
        }
        return countNum;
    }

    //2) My soln using DFS. Works for more cases but again TLE.
    private void dfs(int n, int[] kay, int curr, int[] ans) {
        if (curr > n) return;
        if (curr != 0) kay[0] -= 1;

        if (kay[0] < 0) return ;
        if (kay[0]==0) {
            ans[0] = curr;
            return;
        }

        for (int i=0; i<=9; i++) {
            int num = 10*curr + i;
            if (num == 0) continue;

            dfs(n, kay, num, ans);
        }
    }

    //1) My soln. TC: O(nlogn) so TLE.
    private int usingHeap(int n, int k) {
        Comparator<Integer> cmp = (i1, i2) -> {
            String s1 = String.valueOf(i1);
            String s2 = String.valueOf(i2);

            return compare(s1, s2);
        };

        PriorityQueue<Integer> pq = new PriorityQueue<>(cmp);
        List<Integer> lst = new ArrayList<>();

        for (int i=1; i<=n; i++) {
            lst.add(i);
            if (pq.size() < k) {
                pq.add(i);
            } else {
                if (cmp.compare(i, pq.peek()) > 0) {
                    pq.remove();
                    pq.add(i);
                }
            }
        }

        return pq.peek();
    }

    private int compare(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (Character.getNumericValue(s1.charAt(i)) > Character.getNumericValue(s2.charAt(j))) {
                return -1;
            } else if (Character.getNumericValue(s1.charAt(i)) < Character.getNumericValue(s2.charAt(j))) {
                return 1;
            }
            i += 1;
            j += 1;
        }

        if (i < s1.length()) return -1;
        return +1;
    }
}
