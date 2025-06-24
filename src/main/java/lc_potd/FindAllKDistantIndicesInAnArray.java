package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
public class FindAllKDistantIndicesInAnArray {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> st = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == key) {
                int j = i-k;
                while (j <= Math.min(i+k, nums.length-1)) {
                    if (j >=0 && !st.contains(j)) st.add(j);
                    j += 1;
                }
            }
        }

        List<Integer> ans = new ArrayList<>(st);
        Collections.sort(ans);
        return ans;
    }
}
