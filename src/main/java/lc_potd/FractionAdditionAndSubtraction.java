package lc_potd;

//@link - https://leetcode.com/problems/fraction-addition-and-subtraction/description/
public class FractionAdditionAndSubtraction {
    public String fractionAddition(String expression) {
        StringBuffer sb = new StringBuffer(expression);
        String n1n = null;
        String n1d = null;
        String n2n = null;
        String n2d = null;

        while (!sb.isEmpty()) {
            int i = 0;
            if (n1n == null) {
                while (i < sb.length() && sb.charAt(i) != '/') i += 1;
                n1n = sb.substring(0, i);
            }

            int j = i+1;
            if (n1d == null) {
                while (j < sb.length() && (sb.charAt(j) != '+' && sb.charAt(j) != '-')) j += 1;
                n1d = sb.substring(i+1, j);
            }

            int k = j;
            if (n2n == null) {
                while (k < sb.length() && sb.charAt(k) != '/') k += 1;
                n2n = sb.substring(j, k);
            }

            int l = k+1;
            if (l >= sb.length()) return sb.toString();
            if (n2d == null) {
                while (l < sb.length() && (sb.charAt(l) != '+' && sb.charAt(l) != '-')) l += 1;
                n2d = sb.substring(k+1, l);
            }

            sb.replace(0, l, add(n1n, n1d, n2n, n2d));
            n1n = null;
            n1d = null;
            n2n = null;
            n2d = null;
        }

        return sb.toString();
    }

    private String add(String n1n, String n1d, String n2n, String n2d) {
        int n1ni = Integer.parseInt(n1n);
        int n1di = Integer.parseInt(n1d);
        int n2ni = Integer.parseInt(n2n);
        int n2di = Integer.parseInt(n2d);

        int resni = (n1ni*n2di) + (n2ni*n1di);
        if (resni == 0) return "0/1";

        int resdi = (n1di*n2di);

        int div = 2;
        while ((div <= Math.abs(resni)) && (div <= Math.abs(resdi))) {
            while (resni%div == 0 && resdi%div == 0){
                resni /= div;
                resdi /= div;
            }
            div += 1;
        }


        return String.valueOf(resni) + "/" + String.valueOf(resdi);
    }
}
