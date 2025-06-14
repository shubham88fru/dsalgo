package lc_potd;

//@link - https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
public class MaximumDifferenceByRemappingADigit {
    public int minMaxDifference(int num) {
        return brute(num);
    }

    private int brute(int num) {
        StringBuilder max = new StringBuilder();
        StringBuilder min = new StringBuilder();

        char rmin = 'a';
        char rmax = 'a';

        String nm = String.valueOf(num);
        for (int i=0; i<nm.length(); i++) {
            char ch = nm.charAt(i);
            if (ch != '9') {
                if (rmax == 'a') rmax = ch;
                if (ch == rmax) max.append('9');
                else max.append(ch);
            } else max.append(ch);

            if (ch != '0') {
                if (rmin == 'a') rmin = ch;
                if (ch == rmin) min.append('0');
                else min.append(ch);
            } else min.append(ch);
        }

        return Integer.parseInt(max.toString()) - Integer.parseInt(min.toString());
    }
}
