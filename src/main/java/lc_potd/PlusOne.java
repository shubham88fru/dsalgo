package lc_potd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://leetcode.com/problems/plus-one/description/?
public class PlusOne {
    public int[] plusOne(int[] digits) {
        // return brute(digits);
        // return pass2(digits);
        return optimal(digits);
    }

    private int[] optimal(int[] digits) {
        int n = digits.length;

        int c = 1;
        for (int i=n-1; i>=0; i--) {
            int sum = digits[i] + c;
            if (sum > 9) {
                digits[i] = sum%10;
                c = 1;
            } else {
                digits[i] = sum;
                c = 0;
                break;
            }
        }

        /**
         If carry is one at the end,
         means certainly all the digits
         in the array were 9. Therefore,
         after addition, the end result
         will be more like - 10000...
         */
        if (c == 1) {
            int[] ans = new int[n+1];
            ans[0] = 1;
            return ans;
        }

        return digits;
    }

    private int[] pass2(int[] digits) {
        int n = digits.length;

        int c = 1;
        for (int i=n-1; i>=0; i--) {
            int sum = digits[i] + c;
            if (sum > 9) {
                digits[i] = sum%10;
                c = 1;
            } else {
                digits[i] = sum;
                c = 0;
                break;
            }
        }

        if (c == 1) {
            int[] ans = new int[n+1];
            ans[0] = 1;
            for (int i=1; i<n; i++) {
                ans[i] = digits[i];
            }

            return ans;
        }

        return digits;
    }

    private int[] brute(int[] digits) {
        int n = digits.length;
        List<Integer> lst = new ArrayList<>();
        for (int d: digits) lst.add(d);

        Collections.reverse(lst);
        int c = 1;
        for (int i=0; i<n; i++) {
            int sum = lst.get(i) + c;
            if (sum > 9) {
                sum %= 10;
                c = 1;
            } else c = 0;
            lst.set(i, sum);
        }

        if (c != 0) lst.add(1);
        Collections.reverse(lst);
        return lst.stream().mapToInt(i->i).toArray();
    }
}
