package lc_potd;

//@link - https://leetcode.com/problems/next-greater-numerically-balanced-number/description/?
//@link - https://www.youtube.com/watch?v=MLXm_hhOTmc
public class NextGreaterNumericallyBalancedNumber {
    public int nextBeautifulNumber(int n) {
        // return brute(n);
        // return approach2(n);
        return approach3(n);
    }

    /**
      My Backtracking approach.
     */
    private int approach3(int n) {
        int[] freqs = new int[10];
        for (int i=0; i<freqs.length; i++) freqs[i] = i;

        int a1 = backtracking(n, 0, len(n), freqs);
        if (a1 != -1) return a1;

        for (int i=0; i<freqs.length; i++) freqs[i] = i;
        return backtracking(n, 0, len(n)+1, freqs);
    }

    private int backtracking(int n, int m, int len, int[] freqs) {
        if (len == 0) {
            if (m > n && balanced(m)) return m;
            return -1;
        }

        for (int i=1; i<freqs.length; i++) {
            if (freqs[i] == 0) continue;
            freqs[i] -= 1;
            int ans = backtracking(n, (m*10)+i, len-1, freqs);
            if (ans != -1) return ans;
            freqs[i] += 1;
        }

        return -1;
    }

    private int len(int num) {
        int len = 0;
        while (num > 0) {
            len += 1;
            num /= 10;
        }

        return len;
    }

    /**
     There's a fixed list of 7 digit
     balanced numbers possible.
     Binary search over it.
     */
    private int approach2(int n) {
        int[] balanced = new int[] {1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233, 23323, 23332,     32233, 32323, 32332, 33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555, 122333, 123233, 123323, 123332, 132233, 132323, 132332, 133223, 133232, 133322, 155555, 212333, 213233, 213323, 213332, 221333, 223133, 223313, 223331, 224444, 231233, 231323, 231332, 232133, 232313, 232331, 233123, 233132, 233213, 233231, 233312, 233321, 242444, 244244, 244424, 244442, 312233, 312323, 312332, 313223, 313232, 313322, 321233, 321323, 321332, 322133, 322313, 322331, 323123, 323132, 323213, 323231, 323312, 323321, 331223, 331232, 331322, 332123, 332132, 332213, 332231, 332312, 332321, 333122, 333212, 333221, 422444, 424244, 424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555, 555155, 555515, 555551, 666666, 1224444};

        int l = 0;
        int r = balanced.length-1;

        int num = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (balanced[mid] > n) {
                num = balanced[mid];
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return num;
    }

    private int brute(int n) {
        for (int i=n+1; i<=1224444; i++) {
            if (balanced(i)) return i;
        }

        return -1;
    }

    private boolean balanced(int num) {
        int[] freq = new int[10];
        while (num>0) {
            freq[num%10] += 1;
            num /= 10;
        }

        for (int i=0; i<freq.length; i++) {
            if (freq[i] == 0) continue;
            if (freq[i] != i) return false;
        }

        return true;
    }
}
