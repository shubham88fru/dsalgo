package lc_potd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/find-missing-elements/?
public class FindMissingElements {
    public List<Integer> findMissingElements(int[] nums) {
        return pass1(nums);
    }

    private List<Integer> pass1(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> st = new HashSet<>();

        for (int n: nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
            st.add(n);
        }

        List<Integer> ans = new ArrayList<>();
        for (int j=min; j<=max; j++) {
            if (!st.contains(j)) ans.add(j);
        }

        return ans;
    }
}
