package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/delete-columns-to-make-sorted-iii/description/?
//@check - https://www.youtube.com/watch?v=mKFqKaD7g34
public class DeleteColumnsToMakeSortedIII {
    public int minDeletionSize(String[] strs) {
        return mikssol(strs);
    }

    /*
        To make each string sorted,
        by deleting the min num of chars,
        we basically need to find LIS
        if of length of increasing subsequence
        is max, it means we are deleting
        min num of chars -> `W-LIS`

    */
    private int mikssol(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        //Following is standard LIS
        //bottom up code, with the modification
        //that we check the condition for each
        //string in the array.
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        int lis = 1;
        for (int i=0; i<m; i++) {
            for (int j=0; j<i; j++) {
                boolean valid = true;
                for (String str: strs) {
                    if (str.charAt(j) > str.charAt(i)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    lis = Math.max(lis, dp[i]);
                }
            }
        }

        return m-lis;
    }
}
