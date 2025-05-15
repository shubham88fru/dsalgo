package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/description/
public class LongestUnequalAdjacentGroupsSubsequenceI {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        // return pass1(words, groups);
        return pass2(words, groups);
    }

    //1) My greedy.
    //Intuition - since we are asked about the subsequence
    //(not subarray), no matter what the first element will
    //always be part of one of the valid answers.
    //Mik also had a soln on similar lines but slightly
    //different and more explainable for interview purposes.
    private List<String> pass2(String[] words, int[] groups) {
        int n = words.length;

        List<String> ans = new ArrayList<>();
        int prev = -1;
        for (int i=0; i<n; i++) {
            if (prev == -1 || prev == 1-groups[i]) {
                ans.add(words[i]);
            }
            prev = groups[i];
        }

        return ans;
    }

    //2) My dp/backtracking approach - TLE
    //DP is a overkill because, in the question we are
    //are just one of the longest. But DP approach will
    //try out all possible such alternating strings.
    private List<String> pass1(String[] words, int[] groups) {

        List<String> ans = new ArrayList<>();
        dp(words, groups, 0, -1, new ArrayList<>(), ans);

        return ans;
    }

    private void dp(String[] words, int[] groups, int i, int prev, List<String> curr, List<String> ans) {
        if (i >= words.length) {
            if (curr.size() > ans.size()) {
                ans.clear();
                ans.addAll(curr);

            }
            return;
        }

        if (prev == -1 || groups[i] == 1-prev) {
            curr.add(words[i]);
            dp(words, groups, i+1, groups[i], curr, ans);
            curr.remove(curr.size()-1);
        }

        dp(words, groups, i+1, prev, curr, ans);

    }
}
