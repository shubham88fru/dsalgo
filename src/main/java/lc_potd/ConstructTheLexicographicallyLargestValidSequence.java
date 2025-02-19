package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
//@check - https://www.youtube.com/watch?v=wNOZM1Ki4DY&ab_channel=codestorywithMIK
public class ConstructTheLexicographicallyLargestValidSequence {
    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2*n-1];
        // backtrack(n, n, ans);
        boolean[] found = {false};
        backtrack2(n, 0, n, found ,ans, new HashSet<>());

        return ans;
    }

    /*
    * Following is some nasty backtrack code I wrote, and later was
    * so frustrated by it that didn't attempt it again :)
    * Attempt is again later or check mik's approach.
    * */
    private void backtrack2(int n, int idx, int max, boolean[] found, int[] ans, Set<Integer> used) {
        // if (n == 0) {
        //     return;
        // }
        if (idx >= (ans.length)) {
            // System.out.println(Arrays.toString(ans));
            found[0] = true;
            return;
        }
        // if (used.size() == max) {
        //     // System.out.println(Arrays.toString(ans));
        //     found[0] = true;
        //     return;
        // }

        if (ans[idx] != 0) {
            backtrack2(n-1, idx+1, max, found, ans, used);
        } else {
            for (int i=max; i >= 1; i--) {
                // if (idx != 0 && i==max) break;
                if (used.contains(i)) continue;
                // System.out.println(i + " " + idx);
                if (i==1) {
                    used.add(i);
                    ans[idx] = i;
                    backtrack2(n-1, idx+1, max, found, ans, used);
                    if (found[0]) return;
                    used.remove(i);
                    ans[idx] = 0;
                } else {
                    if ((idx +i) >= ans.length) continue;
                    if (ans[idx+i] != 0) continue;

                    used.add(i);
                    ans[idx] = i;
                    ans[idx+i] = i;
                    backtrack2(n-1, idx+1, max, found, ans, used);
                    // if (i == max) break;
                    if (found[0]) return;
                    used.remove(i);
                    ans[idx] = 0;
                    ans[idx+i] = 0;
                }

            }
        }

    }

    private void backtrack(int n, int max, int[] ans) {
        if (n == 0) {
            // System.out.println(Arrays.toString(ans));
            return;
        }

        for (int i=0; i<ans.length; i++) {
            if (i != 0 && n==max) break;
            if (ans[i] != 0) continue;
            if (n==1) {
                ans[i] = 1;
                backtrack(n-1, max, ans);
                ans[i] = 0;
            } else {
                int next = i + n;
                if (next < ans.length && ans[next] == 0) {
                    ans[i] = n;
                    ans[next] = n;
                    backtrack(n-1, max, ans);
                    ans[i] = 0;
                    ans[next] = 0;
                }
            }
        }
    }
}
