package lc_potd;

//@link - https://leetcode.com/problems/maximum-69-number/
public class Maximum69Number {
    public int maximum69Number (int num) {
        return revise(num);
    }

    private int revise(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num));
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) != '9') {
                str.setCharAt(i, '9');
                return Integer.parseInt(str.toString());
            }
        }
        return num;

    }
}
