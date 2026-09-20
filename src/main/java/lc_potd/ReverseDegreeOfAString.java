package lc_potd;

//@link - https://leetcode.com/problems/reverse-degree-of-a-string/?
public class ReverseDegreeOfAString {
    public int reverseDegree(String s) {
        return pass1(s);
    }

    private int pass1(String s) {
        int n = s.length();

        int sum = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            int ri = 26 - (ch-'a');
            sum += (ri * (i+1));
        }

        return sum;
    }
}
