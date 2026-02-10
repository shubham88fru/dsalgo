package lc_potd;

//@link - https://leetcode.com/problems/count-binary-substrings/description/?
//@check - https://www.youtube.com/watch?v=rhotywtQ1KE
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        return hintedSol(s);
    }

    /**
     Ain't no way I could have come up
     this is all by myself in an interview.

     Idea is to keep tracking of consecutive
     one's or zeros. A one-zero consecutive
     pair will add Math.min(cons_one, cons_zeros)
     valid substrings to ans.

     This is basically sliding window, and
     if I feel like, I should try modifying
     it to my standard sliding window template.
     */
    private int hintedSol(String s) {
        int n = s.length();
        int prevCount = -1, ans = 0;
        int i = 0;
        while (i < n) {
            int count = 1;
            while (i < n-1 && s.charAt(i) == s.charAt(i+1)) {
                i += 1;
                count += 1;
            }

            if (prevCount != -1) {
                ans += Math.min(prevCount, count);
            }
            prevCount = count;
            i += 1;
        }

        return ans;
    }
}
