package lc_potd;

//@link - https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
//@check - https://www.youtube.com/watch?v=_tAArozuTwA&ab_channel=codestorywithMIK
public class NumberOfSubarraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        /*
            I reached till the deduction that
            we'll need to use prefix sum somehow
            but couldn't solve it. Following is
            based on mik's explanation.

            Core idea:
            Odd + even = odd;
            even + odd = odd;
        */
        int n = arr.length;
        int evenCnt = 1;
        int oddCnt = 0;

        int[] parr = new int[n];
        parr[0] = arr[0];
        for (int i=1; i<n; i++) {
            parr[i] = (parr[i-1] + arr[i]);
        }

        int cnt = 0;
        for (int i=0; i<n; i++) {
            if (parr[i]%2==0) {
                cnt = (cnt + oddCnt)%1000000007;
                evenCnt += 1;
            } else {
                cnt = (cnt + evenCnt)%1000000007;
                oddCnt += 1;
            }
        }

        return cnt;
    }

    //2) Brute force - keep generating subarray and checking sum.
}
