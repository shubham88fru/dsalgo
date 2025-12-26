package lc_potd;

//@link - https://leetcode.com/problems/minimum-penalty-for-a-shop/description/?
public class MinimumPenaltyForAShop {
    public int bestClosingTime(String customers) {
        return pass1(customers);
    }

    /**
     * My prefix sum soln.
     * Mik showed a O(1) space soln
     * too, check if needed.
     *
     * @param customer
     * @return
     */
    private int pass1(String customer) {
        int n = customer.length();

        int[] sy = new int[n+1]; //suffix 'Y'
        sy[n] = 0;

        int[] pn = new int[n+1]; //prefix 'N'

        for (int i=n-1; i>=0; i--) {
            char ch = customer.charAt(i);
            if (ch == 'Y') sy[i] = sy[i+1] + 1;
            else sy[i] = sy[i+1];
        }

        for (int i=0; i<n; i++) {
            char ch = customer.charAt(i);
            if (ch == 'N') pn[i+1] = pn[i] + 1;
            else pn[i+1] = pn[i];
        }

        int minL = Integer.MAX_VALUE; //minimum loss
        int minH = -1; //optimal close hour.
        for (int i=0; i<=n; i++) {
            if (sy[i]+pn[i] < minL) {
                minL = sy[i] + pn[i];
                minH = i;
            }
        }
        return minH;
    }
}
