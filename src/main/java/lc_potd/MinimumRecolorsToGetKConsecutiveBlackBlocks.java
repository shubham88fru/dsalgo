package lc_potd;

//@link - https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {
    public int minimumRecolors(String blocks, int k) {
        return revise(blocks, k);
    }

    private int revise(String blocks, int k) {
        int n = blocks.length();

        int l = 0;
        int r = 0;

        int bc = 0;
        int wc = 0;
        int min = Integer.MAX_VALUE;

        while (r < n) {
            char ch = blocks.charAt(r);
            if (r-l+1<=k) {
                if (ch == 'W') wc += 1;
                else if (ch == 'B') bc += 1;

                if (r-l+1==k) {
                    min = Math.min(min, wc);

                }

                r += 1;
            } else {

                char toLeave = blocks.charAt(l);
                if (toLeave == 'W') wc -= 1;
                else if (toLeave == 'B') bc -= 1;

                l += 1;
            }
        }

        return min;
    }
}
