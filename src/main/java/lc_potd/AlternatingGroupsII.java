package lc_potd;

//@link - https://leetcode.com/problems/alternating-groups-ii/
//@check - https://www.youtube.com/watch?v=EZVLzXvaQ2A&t=1120s&ab_channel=codestorywithMIK
public class AlternatingGroupsII {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        return pass1(colors, k);
    }

    /*
    * My sliding window approach.
    * Mik had the similar intuition but showed
    * a good trick to handle questions that have circular arrays.
    * @check that trick during revision. Can be useful and same a lot
    * of headache.
    * */
    private int pass1(int[] colors, int k) {
        int n = colors.length;

        int l = 0;
        int r = 0;
        int count = 0;
        while (l < n) {
            int modr = r%n;
            int prev = (((r-1)%n)+n)%n; //trick to handle -ve. `r-1` can go -ve.
            if (r == l) {
                r += 1;
            } else if (r-l+1 <= k && colors[prev] == 1-colors[modr]) {
                if (r-l+1 == k) {
                    count += 1;
                }
                r += 1;
            } else {
                // l += 1;

                /*
                    Optimization.
                */
                if (colors[prev] != 1-colors[modr]) l = r;
                else l += 1;
            }
        }

        return count;
    }
}
