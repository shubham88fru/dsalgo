package lc_potd;

//@link - https://leetcode.com/problems/string-to-integer-atoi/
public class StringToInteger {
    public int myAtoi(String s) {
        return revise(s);
    }

    /*
        My soln.
    */
    private int revise(String s) {
        String tm = s.trim();
        int n = tm.length();

        int i=0;
        boolean neg = false, overflow = false;
        int num = 0;
        int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
        while (i < n) {
            char ch = tm.charAt(i);

            if (i == 0 && (ch == '-' || ch == '+')) {
                if (ch == '-') neg = true;
                i += 1;
                continue;
            }

            if (!Character.isDigit(ch)) break;
            int dig = ch - '0';
            if (num > MAX/10 || (num == MAX/10 && dig > MAX%10)) {
                overflow = true;
                break;
            }

            num = num*10 + dig;

            i += 1;
        }

        if (neg) {
            if (overflow) return MIN;
            return -1*num;
        }

        if (overflow) return MAX;
        return num;
    }
}
