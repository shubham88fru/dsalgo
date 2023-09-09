package strvr.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/permutation-sequence/description/
//@strvr - https://takeuforward.org/data-structure/find-k-th-permutation-sequence/
public class PermutationSequences {
    public String getPermutation(int n, int k) {
        /*Brute - TLE*/
        // Map<Integer, String> mp = new HashMap<>();
        // getPerms(n, k, mp, new int[]{1}, new HashSet<>(), 0, "");
        // return mp.get(k);

        /*Optimal soln */
        return getPermsOptimal(n, k);
    }

    //Optimal. Striver's soln. Din't get it.
    private String getPermsOptimal(int n, int k) {
        int fact = 1;
        List<Integer> numbers = new ArrayList<>();
        for (int i=1; i<n; i++) {
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);
        String ans = "";
        k -= 1;
        while (true) {
            ans += numbers.get(k/fact);
            numbers.remove(k/fact);
            if (numbers.size() == 0) break;
            k %= fact;
            fact /= numbers.size();
        }
        return ans;
    }

    //2) Brute force.
    //Plain recursion. Time complexity is of the order n! (to generate all perms)
    //Gives TLE.
    private void getPerms(int n, int k, Map<Integer, String> mp, int[] counter, Set<String> st, int curr, String str) {
        if (curr >= n) {
            mp.put(counter[0], str);
            counter[0] += 1;
            return;
        }

        for (int i=1; i<=n; i++) {
            if (!st.contains(i+"")) {
                st.add(i+"");
                getPerms(n, k, mp, counter, st, curr+1, str+i+"");
                st.remove(i+"");
            }
        }
    }
}
