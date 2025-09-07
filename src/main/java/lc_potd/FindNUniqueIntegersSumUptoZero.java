package lc_potd;

//@link - https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
public class FindNUniqueIntegersSumUptoZero {
    public int[] sumZero(int n) {
        return revise(n);
    }

    //approach 1
    private int[] revise(int n) {
        int[] ans = new int[n];

        int fill = 1;
        for (int i=n%2; i<n; i+=2) {
            ans[i] = fill;
            ans[i+1] = -1*fill;
            fill += 1;
        }

        return ans;
    }

    //approach 2
    public int[] og(int n) {
        int sum = 0;
        int[] ans = new int[n];
        int i = 0;
        while (n > 1) {
            ans[i] = n;
            sum += n;
            n -= 1;
            i += 1;
        }
        ans[i] = -1*sum;
        return ans;

    }
}
