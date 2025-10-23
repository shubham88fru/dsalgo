package lc_potd;

//@link - https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/description/?
public class CheckIfDigitsAreEqualInStringAfterOperationsI {
    public boolean hasSameDigits(String s) {
        return pass1(s);
    }

    private boolean pass1(String s) {
        StringBuilder sb = new StringBuilder(s);

        while (sb.length() > 2) {
            int i = 0;
            int n = sb.length();
            for (; i<n-1; i++) {
                int n1 = Character.getNumericValue(sb.charAt(i));
                int n2 = Character.getNumericValue(sb.charAt(i+1));

                sb.append(String.valueOf((n1+n2)%10));
            }
            sb.delete(0, i+1);
        }

        return sb.charAt(0) == sb.charAt(1);
    }
}
