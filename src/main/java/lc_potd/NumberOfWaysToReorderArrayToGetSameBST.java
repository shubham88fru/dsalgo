package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@lnk - https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/description/?
//@check - https://www.youtube.com/watch?v=YMe9Q2yZvBo
public class NumberOfWaysToReorderArrayToGetSameBST {
    public int numOfWays(int[] nums) {
        return mikssol(nums);
    }

    /*
        Coded by me completely based on mik's
        explanation. Not too difficult but
        still not 100% comfortable around the
        math.
    */
    private int mikssol(int[] nums) {
        int n = nums.length;
        List<List<Long>> PT = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            List<Long> row = new ArrayList<>();
            for (int j=0; j<=i; j++) {
                if (j==0 || j==i) row.add(1L);
                else {
                    long prevSum = (PT.get(i-1).get(j) + PT.get(i-1).get(j-1))%1000000007;
                    row.add(prevSum);
                }
            }

            PT.add(row);
        }

        List<Integer> ns = new ArrayList<>();
        for (int i=0; i<n; i++) ns.add(nums[i]);
        return (int)(rec(ns, PT)-1);
    }

    private long rec(List<Integer> nums, List<List<Long>> PT) {
        int n = nums.size();

        if (n < 3) return 1;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int root = nums.get(0);

        for (int i=1; i<n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else right.add(nums.get(i));
        }

        long x = rec(left, PT)%1000000007;
        long y = rec(right, PT)%1000000007;
        long z = PT.get(n-1).get(left.size());

        return (((x*y)%1000000007)*z)%1000000007;
    }
}
