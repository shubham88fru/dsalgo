package lc_potd;

//@link - https://leetcode.com/problems/xor-queries-of-a-subarray/description/
public class XORQueriesOfASubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        return pxsx(arr, queries);
    }

    private int[] pxsx(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] px = new int[n+1];
        int[] sx = new int[n+1];

        px[0] = 0;
        sx[n] = 0;

        for (int i=1; i<=n; i++) {
            px[i] = px[i-1]^arr[i-1];
        }

        for (int i=n-1; i>=0; i--) {
            sx[i] = sx[i+1]^arr[i];
        }

        int[] ans = new int[queries.length];
        int allXor = sx[0]; //xor of entire array.
        for (int i=0; i<queries.length; i++) {
            int si = queries[i][0];
            int ei = queries[i][1];
            ans[i] = allXor^px[si]^sx[ei+1];
        }

        return ans;
    }
}
