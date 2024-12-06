package lc_potd;

//@link - https://leetcode.com/problems/move-pieces-to-obtain-a-string/
//@check - https://www.youtube.com/watch?v=OlfIyTpCzvM&t=1080s&ab_channel=codestorywithMIK
/*
    The following soln is written by me but is completely
    based on Mik's explanation.
 */
public class MovePiecesToObtainAString {
    public boolean canChange(String start, String target) {
        int i=0;
        int j=0;

        int l1 = start.length();
        int l2 = target.length();

        while (j < l2) {
            while ((j < l2) && (target.charAt(j) == '_')) j += 1;
            if (j >= l2) break;
            char ch2 = target.charAt(j);

            while ((i < l1) && (start.charAt(i) == '_')) i += 1;

            if (i >= l1) return false;
            char ch1 = start.charAt(i);


            if (ch1 != ch2) return false;

            if (ch1 == 'L' && (i < j)) return false;
            if (ch1 == 'R' && (i > j)) return false;

            i += 1;
            j += 1;
        }

        while ((i < l1) && (start.charAt(i) == '_')) i += 1;
        return i >= l1;
    }
}
