package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
//@check - https://www.youtube.com/watch?v=Ut9cr6Ut4wY&t=2000s&ab_channel=codestorywithMIK
public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
        // return pass1(arr);
        return mikssol(arr);
    }

    /*
       1) Mik's dp approach.
       Way clever than my simple approach below.
       This passes the TC as is but mik also
       showed memoization and bottom-up
       approach for this. check if necessary.
    */
    private int mikssol(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i=0; i<n; i++) {
            mp.put(arr[i], i); //given in q that all elements are unique.
        }

        int maxLen = 0;
        for (int j=1; j<n; j++) {
            for (int k=j+1; k<n; k++) {
                int length = solve(j, k, arr, mp);
                if (length >= 3) {
                    maxLen = Math.max(maxLen, length);
                }
            }
        }

        return maxLen;
    }

    private int solve(int j, int k, int[] arr, Map<Integer, Integer> mp) {
        int target = arr[k] - arr[j];
        if (mp.containsKey(target) && mp.get(target) < j) {
            int i = mp.get(target);
            return solve(i, j, arr, mp) + 1; // +1 for adding kth element
        }

        return 2; //we have got j and k atleast.
    }

    /*
       2) My top-down dp appraoch based on 01 knap-sack pattern.
        Gives TLE.
    */
    private int pass1(int[] arr) {
        int n = arr.length;

        int[] len = {0};
        dp(arr, 0, -1, -1, 0, len);

        if (len[0] == 0) return 0;
        return len[0]+2;
    }

    private void dp(int[] arr, int curr, int p1, int p2, int currLen, int[] len) {
        if (curr >= arr.length) {
            len[0] = Math.max(len[0], currLen);
            return;
        }

        if (p1 == -1) {
            dp(arr, curr+1, curr, p2, currLen, len);
        } else if (p2 == -1) {
            dp(arr, curr+1, p1, curr, currLen, len);
        } else if (arr[curr] == arr[p1] + arr[p2]) {
            dp(arr, curr+1, p2, curr, currLen+1, len);
        }

        dp(arr, curr+1, p1, p2, currLen, len);
    }
}
