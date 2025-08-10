package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/reordered-power-of-2/
public class ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int n) {
        return pass1(n);
    }

    //3. Using sort
    private boolean pass1(int n) {
        char[] carr = String.valueOf(n)
                .toCharArray();
        Arrays.sort(carr);
        String strN = new String(carr);

        for (int i=0; i<=31; i++) {
            char[] arr = String.valueOf((int)Math.pow(2.0, i)).toCharArray();
            Arrays.sort(arr);
            String powStr = new String(arr);
            if (powStr.equals(strN)) return true;
        }

        return false;
    }

    //4. Brute force
    //Generate all perms and keep checking
    //if current perm is a power of 2 (which can
    //be done in constant time).
}
