package lc_potd;

//@link - https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/description/
public class MaxDifferenceYouCanGetFromChangingAnInteger {
    public int maxDiff(int num) {
        return pass1(num);
    }

    /*
        My soln. Mik had a similar approach.
        This is not a difficult problem but a
        nasty one.
    */
    private int pass1(int num) {
        String str = String.valueOf(num);
        int n = str.length();

        StringBuilder a = new StringBuilder();
        char ra = 'a';

        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (ch != '9') {
                if (ra == 'a') {
                    ra = ch;
                }
            }

            if (ch == ra) a.append('9');
            else a.append(ch);

        }

        StringBuilder b = new StringBuilder();
        char bf = str.charAt(0);
        char rb = 'b';
        if (bf == '1') {
            b.append('1');
            for (int i=1; i<n; i++) {
                char ch = str.charAt(i);
                if (ch != '1' && ch != '0') {
                    if (rb == 'b') {
                        rb = ch;
                    }
                }
                if (ch == rb) b.append('0');
                else b.append(ch);

            }
        } else {
            for (int i=0; i<n; i++) {
                char ch = str.charAt(i);
                if (ch != '1') {
                    if (rb == 'b') {
                        rb = ch;
                    }
                }

                if (ch == rb) b.append('1');
                else b.append(ch);

            }
        }

        return Integer.valueOf(a.toString()) - Integer.valueOf(b.toString());

    }
}
