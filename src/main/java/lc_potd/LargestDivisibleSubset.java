package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/largest-divisible-subset/description/
//@check - https://www.youtube.com/watch?v=T-Fk9185a68&ab_channel=codestorywithMIK
public class LargestDivisibleSubset {
    /*
        This problem is nothing but LIS.
    */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // return pass1(nums);
        return pass2(nums);
    }

    //Bottom up soln based on mik's approach.
    private List<Integer> pass2(int[] nums) {
        //Following is completely based on bottom-up
        //for LIS. Will need to understand that first.

        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int[] pi = new int[n]; //prev index.
        Arrays.fill(pi, -1);

        int lastChosenIndex = 0;
        int maxL = 0;
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i]%nums[j] == 0) {
                    if (dp[i] < dp[j]+1) {
                        dp[i] = dp[j] + 1;
                        pi[i] = j;
                    }

                    if (dp[i] > maxL) {
                        maxL = dp[i];
                        lastChosenIndex = i;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (lastChosenIndex != -1) {
            ans.add(nums[lastChosenIndex]);
            lastChosenIndex = pi[lastChosenIndex];
        }

        return ans;
    }

    //My Top down - Gives TLE.
    //Mik also had the same top down soln.
    private List<Integer> pass1(int[] nums) {
        //sort so that numbers are increasing,
        //this will help because if a subsequent
        //number is divisible by prev, then it will
        //definitely be divisble by all the remaing
        //prev elements.
        Arrays.sort(nums);
        int n = nums.length;

        List<Integer> ans = new ArrayList<>();
        bactracking(nums, 0, -1, ans, new ArrayList<>());
        return ans;
    }

    private void bactracking(int[] nums, int i, int pi, List<Integer> ans, List<Integer> curr) {
        if (i >= nums.length) {
            if (curr.size() > ans.size()) {
                ans.clear();
                ans.addAll(new ArrayList<>(curr));
            }
            return;
        }

        if (pi == -1 || nums[i]%nums[pi] == 0) {
            curr.add(nums[i]);
            bactracking(nums, i+1, i, ans, curr);
            curr.remove(curr.size()-1);
        }

        bactracking(nums, i+1, pi, ans, curr);
    }
}
